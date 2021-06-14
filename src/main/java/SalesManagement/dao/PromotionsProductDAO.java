package SalesManagement.dao;

import SalesManagement.dto.PromotionsCustomer;
import SalesManagement.dto.PromotionsProduct;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class PromotionsProductDAO {
    private JdbcTemplate template;

    public PromotionsProduct findPromotionById(int id) {
        String sql = "select * from promotions_products where Id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PromotionsProduct.class));
    }
}
