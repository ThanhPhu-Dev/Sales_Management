package SalesManagement.dao;

import SalesManagement.dto.Customer;
import SalesManagement.dto.PromotionsCustomer;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class PromotionsCustomerDAO {
    private JdbcTemplate template;

    public PromotionsCustomer findPromotionById(int id) {
        String sql = "select * from promotions_customers where Id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(PromotionsCustomer.class));
    }
}
