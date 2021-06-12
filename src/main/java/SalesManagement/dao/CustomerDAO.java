package SalesManagement.dao;

import SalesManagement.dto.Customer;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class CustomerDAO {
    private JdbcTemplate template;

    public Customer findCustomerById(int id) {
        String sql = "select * from Customers where Id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Customer>(Customer.class));
    }

}
