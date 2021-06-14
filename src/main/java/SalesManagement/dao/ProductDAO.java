package SalesManagement.dao;

import SalesManagement.dto.Customer;
import SalesManagement.dto.Product;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class ProductDAO {
    private JdbcTemplate template;

    public Product findProductById(int id) {
        String sql = "select * from products where Id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Product.class));
    }
}
