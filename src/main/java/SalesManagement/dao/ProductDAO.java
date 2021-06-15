package SalesManagement.dao;

import SalesManagement.dto.Customer;
import SalesManagement.dto.Product;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

// JAVA UTIL
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Data
@Repository
public class ProductDAO {
    private JdbcTemplate template;

    public Product findProductById(int id) {
        String sql = "select * from products where Id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> getProducts() {
        String sql = "SELECT * FROM PRODUCTS";
        return template.query(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("Nd"));
                product.setName(resultSet.getString("Name"));
                product.setSpecification(resultSet.getInt("Specification"));
                product.setPromotionsId(resultSet.getInt("PromotionId "));
                product.setTradeDiscount(resultSet.getFloat("TradeDiscount"));

                return product;
            }
        });
    }
}
