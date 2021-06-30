package SalesManagement.dao;

import SalesManagement.dto.Product;
import SalesManagement.dto.PromotionsCustomer;
import SalesManagement.dto.PromotionsProduct;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@Repository
public class PromotionsProductDAO {
    private JdbcTemplate template;

    public PromotionsProduct findPromotionById(int id) {
        try {
            String sql = "select * from promotions_products where Id=?";
            return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PromotionsProduct.class));
        }catch (Exception e){
            return null;
        }
    }

    // Huy
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM promotions_products";
        return template.queryForObject(
                sql, Integer.class);
    }

    // Huy
    public int countByName(String name) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "SELECT COUNT(*) FROM promotions_products WHERE name LIKE :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", "%" + name + "%");
        return namedParameterJdbcTemplate.queryForObject(
                sql, namedParameters, Integer.class);
    }

    // Huy
    public List<PromotionsProduct> findAll(int offset, int limit) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "SELECT * FROM promotions_products LIMIT :offset, :limit";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("offset", offset)
                .addValue("limit", limit);

        return namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<PromotionsProduct>() {
            @Override
            public PromotionsProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                PromotionsProduct promotionsProduct = new PromotionsProduct();

                promotionsProduct.setId(rs.getInt("id"));
                promotionsProduct.setName(rs.getString("name"));
                promotionsProduct.setStartDate(rs.getDate("startdate"));
                promotionsProduct.setEndDate(rs.getDate("enddate"));
                promotionsProduct.setPercentDiscount(rs.getFloat("percentdiscount"));

                return promotionsProduct;
            }
        });
    }

    // Huy
    public List<PromotionsProduct> findByName(String name) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "SELECT * FROM promotions_products WHERE name LIKE :name";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", "%" + name + "%");

        return namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<PromotionsProduct>() {
            @Override
            public PromotionsProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                PromotionsProduct promotionsProduct = new PromotionsProduct();

                promotionsProduct.setId(rs.getInt("id"));
                promotionsProduct.setName(rs.getString("name"));
                promotionsProduct.setStartDate(rs.getDate("startdate"));
                promotionsProduct.setEndDate(rs.getDate("enddate"));
                promotionsProduct.setPercentDiscount(rs.getFloat("percentdiscount"));

                return promotionsProduct;
            }
        });
    }
}
