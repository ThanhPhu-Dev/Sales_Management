package SalesManagement.dao;

import SalesManagement.dto.Customer;
import java.sql.*;
import java.util.*;
import javax.security.auth.login.AccountException;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class CustomerDAO {

    private JdbcTemplate template;

    public Customer findCustomerById(int id) {
        String sql = "select * from Customers where Id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class));
    }

    public List<Customer> findAllCustomerBy(String cusName) {
        String sql;
        if (cusName.isEmpty()) {
            sql = "select * from Customers";
        } else {
            sql = "select * from Customers where Name like '%" + cusName + "%'";
        }
        return template.query(sql, new RowMapper<Customer>() {
            public Customer mapRow(ResultSet rs, int row) throws SQLException {
                Customer e = new Customer();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setNumberCard(rs.getString(3));
                e.setAccountBalance(rs.getInt(4));
                e.setPromotionsId(rs.getInt(5));
                e.setDebtMax(rs.getInt(6));
                return e;
            }
        });
    }

    public int AddCustomer(Customer cus) {
        String sql = String.format("insert into Customers (Name, NumberCard, AccountBalance, PromotionsId) values "
                + "('%s', '%s', '%d', '%f')",
                cus.getName(), cus.getNumberCard(), cus.getAccountBalance(), cus.getPromotionsId());
        return template.update(sql);
    }

    public Customer findCustomerByNumCard(String card) {
         String sql = "select * from Customers where PromotionId = ?";
        return template.queryForObject(sql, new Object[]{card}, new BeanPropertyRowMapper<>(Customer.class));
    }
}
