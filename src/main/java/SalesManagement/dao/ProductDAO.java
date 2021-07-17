package SalesManagement.dao;

import SalesManagement.dto.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Data
@Repository
public class ProductDAO {

    private JdbcTemplate template;

    @Autowired
    private PromotionsProductDAO promotionsProductDAO;

    // Huy
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM products";
        return template.queryForObject(
                sql, Integer.class);
    }

    // Huy
    public int countBySKU(String sku) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "SELECT COUNT(*) FROM products WHERE sku LIKE :sku";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("sku", "%" + sku + "%");
        return namedParameterJdbcTemplate.queryForObject(
                sql, namedParameters, Integer.class);
    }

    // Huy
    public int countBySKUOrName(String searchValue) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "SELECT COUNT(*) FROM products WHERE sku LIKE :sku OR name LIKE :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("sku", "%" + searchValue + "%")
                .addValue("name", "%" + searchValue + "%");
        return namedParameterJdbcTemplate.queryForObject(
                sql, namedParameters, Integer.class);
    }

    // Huy
    public List<Product> findAll(int offset, int limit) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "SELECT * FROM products LIMIT :offset, :limit";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("offset", offset)
                .addValue("limit", limit);

        return namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();

                product.setId(rs.getInt("Id"));
                product.setSKU(rs.getString("SKU"));
                product.setName(rs.getString("Name"));
                product.setSpecification(rs.getInt("Specification"));
                product.setHistoricalCost(rs.getInt("HistoricalCost"));
                product.setTradeDiscount(rs.getFloat("TradeDiscount"));
                product.setPromotionsId(rs.getInt("PromotionId"));
                product.setPromotions(product.getPromotionsId() > 0
                        ? promotionsProductDAO.findPromotionById(product.getPromotionsId()) : null);
                return product;
            }
        }
        );
    }

    // Huy
    public List<Product> findBySKUOrName(String searchValue) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "SELECT * FROM products WHERE sku LIKE :sku OR name LIKE :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("sku", "%" + searchValue + "%")
                .addValue("name", "%" + searchValue + "%");

        return namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("Id"));
                product.setSKU(rs.getString("SKU"));
                product.setName(rs.getString("Name"));
                product.setSpecification(rs.getInt("Specification"));
                product.setHistoricalCost(rs.getInt("HistoricalCost"));
                product.setTradeDiscount(rs.getFloat("TradeDiscount"));
                product.setPromotionsId(rs.getInt("PromotionId"));
                product.setPromotions(product.getPromotionsId() > 0
                        ? promotionsProductDAO.findPromotionById(product.getPromotionsId()) : null);
                return product;
            }
        });
    }

    // Huy
    public Integer add(Product product) {
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        return template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId());
    }

    // Huy
    public Integer update(Product product, Integer id) {
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? "
                + "WHERE Id = ?";

        return template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), id);
    }

    public Product findProductById(int id) {
        String sql = "select * from products where Id=?";
        return template.queryForObject(sql, new Object[]{id}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("Id"));
                product.setSKU(resultSet.getString("SKU"));
                product.setName(resultSet.getString("Name"));
                product.setSpecification(resultSet.getInt("Specification"));
                product.setHistoricalCost(resultSet.getInt("HistoricalCost"));
                product.setPromotionsId(resultSet.getInt("PromotionId"));
                product.setTradeDiscount(resultSet.getFloat("TradeDiscount"));
                product.setPromotions(product.getPromotionsId() > 0
                        ? promotionsProductDAO.findPromotionById(product.getPromotionsId()) : null);
                return product;
            }
        });
    }

    public List<Product> getProducts() {
        String sql = "SELECT * FROM PRODUCTS";
        return template.query(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("Id"));
                product.setSKU(resultSet.getString("SKU"));
                product.setName(resultSet.getString("Name"));
                product.setSpecification(resultSet.getInt("Specification"));
                product.setHistoricalCost(resultSet.getInt("HistoricalCost"));
                product.setPromotionsId(resultSet.getInt("PromotionId"));
                product.setTradeDiscount(resultSet.getFloat("TradeDiscount"));
                product.setPromotions(product.getPromotionsId() > 0
                        ? promotionsProductDAO.findPromotionById(product.getPromotionsId()) : null);
                return product;
            }
        });
    }

    public List<Product> getProductsPagination(int offset, int limit, List<Integer> excludeIds) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());
        String sql = "SELECT * FROM PRODUCTS LIMIT :offset, :limit";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("offset", offset);
        parameters.addValue("limit", limit);

        if (excludeIds.size() > 0) {
            parameters.addValue("ids", excludeIds);
            sql = "SELECT * FROM PRODUCTS WHERE ID NOT IN(:ids) LIMIT :offset, :limit";
        }

        return namedParameterJdbcTemplate.query(sql,
                parameters,
                new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("Id"));
                product.setName(resultSet.getString("Name"));
                product.setSpecification(resultSet.getInt("Specification"));
                product.setHistoricalCost(resultSet.getInt("HistoricalCost"));
                product.setPromotionsId(resultSet.getInt("PromotionId"));
                product.setTradeDiscount(resultSet.getFloat("TradeDiscount"));
                product.setPromotions(product.getPromotionsId() > 0
                        ? promotionsProductDAO.findPromotionById(product.getPromotionsId()) : null);
                return product;
            }
        });
    }

    // Đếm số sản phẩm còn lại kết hợp tìm kiếm
    public Integer getRemainProductsCount(List<Integer> excludeIds, String searchId) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());
        String sql = "SELECT COUNT(*) FROM PRODUCTS";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        if (excludeIds.size() > 0 && searchId != null & !searchId.isEmpty()) {
            parameters.addValue("ids", excludeIds);
            parameters.addValue("id", "%" + searchId + "%");

            sql = "SELECT COUNT(*) FROM PRODUCTS WHERE ID NOT IN(:ids) AND ID LIKE :id";
        } else if (excludeIds.size() > 0) {
            parameters.addValue("ids", excludeIds);
            sql = "SELECT COUNT(*) FROM PRODUCTS WHERE ID NOT IN(:ids)";
        } else if (searchId != null && !searchId.isEmpty()) {
            parameters.addValue("id", "%" + searchId + "%");
            sql = "SELECT COUNT(*) FROM PRODUCTS WHERE ID LIKE :id";
        }

        return namedParameterJdbcTemplate.queryForObject(sql, parameters, Integer.class);
    }

    public List<Product> getProductsPaginationWithSearch(int offset, int limit, List<Integer> excludeIds, String searchId) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template);
        String sql = "SELECT * FROM PRODUCTS LIMIT :offset, :limit";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("offset", offset);
        parameters.addValue("limit", limit);

        if (excludeIds.size() > 0 && searchId != null && !searchId.isEmpty()) {
            parameters.addValue("ids", excludeIds);
            parameters.addValue("id", "%" + searchId + "%");
            sql = "SELECT * FROM PRODUCTS WHERE ID NOT IN(:ids) AND ID LIKE :id LIMIT :offset, :limit";
        } else if (excludeIds.size() > 0) {
            parameters.addValue("ids", excludeIds);
            sql = "SELECT * FROM PRODUCTS WHERE ID NOT IN(:ids) LIMIT :offset, :limit";
        } else if (searchId != null && !searchId.isEmpty()) {
            parameters.addValue("id", "%" + searchId + "%");
            sql = "SELECT * FROM PRODUCTS WHERE ID LIKE :id LIMIT :offset, :limit";
        }

        return namedParameterJdbcTemplate.query(sql,
                parameters,
                new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {

                Product product = new Product();
                product.setId(resultSet.getInt("Id"));
                product.setName(resultSet.getString("Name"));
                product.setSpecification(resultSet.getInt("Specification"));
                product.setHistoricalCost(resultSet.getInt("HistoricalCost"));
                product.setPromotionsId(resultSet.getInt("PromotionId"));
                product.setTradeDiscount(resultSet.getFloat("TradeDiscount"));
                if (product.getPromotionsId() > 0) {
                    product.setPromotions(promotionsProductDAO.findPromotionById(product.getPromotionsId()).getStatus() == 1
                            ? promotionsProductDAO.findPromotionById(product.getPromotionsId()) : null);
                } else {
                    product.setPromotions(null);
                }

                return product;
            }
        });
    }
}
