package SalesManagement.dao;

import SalesManagement.dto.Customer;
import SalesManagement.dto.Product;
import java.sql.*;
import java.util.*;
import javax.security.auth.login.AccountException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class CustomerDAO {

    private JdbcTemplate template;
    @Autowired
    private PromotionsCustomerDAO promotionsCustomerDAO;

    public Customer findCustomerById(int id) {
        String sql = "select * from Customers where Id=?";

        return template.queryForObject(sql, new Object[]{id}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setPhone(resultSet.getString("Phone"));
                customer.setIdentityCard(resultSet.getString("IdentityCard"));
                customer.setNumberCard(resultSet.getString("numbercard"));
                customer.setAccountBalance(resultSet.getInt("accountbalance"));
                customer.setPromotionsId(resultSet.getInt("PromotionsId"));
                customer.setDebtMax(resultSet.getInt("DebtMax"));
                customer.setPromotion(customer.getPromotionsId() > 0
                        ? promotionsCustomerDAO.findPromotionById(customer.getPromotionsId()) : null);
                return customer;
            }
        });
    }

    public List<Customer> findAllCustomerBy(String cusName) {
        String sql;
        if (cusName.isEmpty()) {
            sql = "select * from Customers";
        } else {
            try {
                sql = "select * from Customers where Phone like '%" + cusName + "%' OR IdentityCard like '%" + cusName + "%'";
            } catch (Exception e) {
                return null; 
            }
        }
        return template.query(sql, new RowMapper<Customer>() {
            public Customer mapRow(ResultSet rs, int row) throws SQLException {
                Customer e = new Customer();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setPhone(rs.getString("phone"));
                e.setIdentityCard(rs.getString("identityCard"));
                e.setNumberCard(rs.getString("numbercard"));
                e.setAccountBalance(rs.getInt("accountbalance"));
                e.setPromotionsId(rs.getInt("PromotionsId"));
                e.setDebtMax(rs.getInt("DebtMax"));
                e.setPromotion(e.getPromotionsId() > 0 ? promotionsCustomerDAO.findPromotionById(e.getPromotionsId()) : null);
                return e;
            }
        });
    }

    public int AddCustomer(Customer cus) {
        String sql;
        //n???u m?? ??u ????i = -1 th?? kh????i insert n??
        if (cus.getPromotionsId() > -1) {
            sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        } else {
            sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance) values "
                    + "('%s', '%s', '%s', '%s', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance());
        }
        return template.update(sql);
    }

    public int UpdateCustomer(Customer cus) {
        String sql;
        //n???u m?? ??u ????i = -1 th?? kh????i set n??
        if (cus.getPromotionsId() > -1) {
            sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        } else {
            sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', PromotionsId = NULL where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getId());
        }
        return template.update(sql);
    }

    public int findCustomerByNumCard(String card) {
        String sql = "select count(*) from Customers where NumberCard = ?";
        int rows = template.queryForObject(sql, new Object[]{card}, (Integer.class));
        return rows;
    }

    public int findCustomerByIdentity(String card) {
        String sql = "select count(*) from Customers where IdentityCard = ?";
        int rows = template.queryForObject(sql, new Object[]{card}, (Integer.class));
        return rows;
    }
    public int findCustomerByPhone(String phone) {
        String sql = "select count(*) from Customers where Phone = ?";
        int rows = template.queryForObject(sql, new Object[]{phone}, (Integer.class));
        return rows;
    }

    public int updateAccountBalance(int id, float newAccountBalance) {
        String sql = "UPDATE CUSTOMERS SET ACCOUNTBALANCE = ? WHERE ID = ?";
        return template.update(sql, new Object[]{newAccountBalance, id});
    }

    public List<Customer> getCustomersPagination(int offset, int limit, String searchValue) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());
        String sql = "SELECT * FROM CUSTOMERS LIMIT :offset, :limit";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        if (!searchValue.equals("")) {
            sql = "SELECT * FROM CUSTOMERS WHERE Phone like :numPhone OR IdentityCard like :numCard LIMIT :offset, :limit";
            parameters.addValue("numPhone", "%" + searchValue + "%");
            parameters.addValue("numCard", "%" + searchValue + "%");
        }

        parameters.addValue("offset", offset);
        parameters.addValue("limit", limit);

        return namedParameterJdbcTemplate.query(sql,
                parameters,
                new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int row) throws SQLException {
                Customer e = new Customer();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setPhone(rs.getString("phone"));
                e.setIdentityCard(rs.getString("identityCard"));
                e.setNumberCard(rs.getString("numbercard"));
                e.setAccountBalance(rs.getInt("accountbalance"));
                e.setPromotionsId(rs.getInt("PromotionsId"));
                e.setDebtMax(rs.getInt("DebtMax"));
                e.setPromotion(e.getPromotionsId() > 0 ? promotionsCustomerDAO.findPromotionById(e.getPromotionsId()) : null);
                return e;
            }
        });
    }

    // ???????m s??? s???n ph???m c??n l???i
    public Integer getRemainCustomersCount(String searchValue) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());
        String sql = "SELECT COUNT(*) FROM CUSTOMERS";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        if (!searchValue.equals("")) {
            sql = "SELECT COUNT(*) FROM CUSTOMERS WHERE Phone like :numPhone OR IdentityCard like :numCard";
            parameters.addValue("numPhone", "%" + searchValue + "%");
            parameters.addValue("numCard", "%" + searchValue + "%");
        }

        return namedParameterJdbcTemplate.queryForObject(sql, parameters, Integer.class);
    }
}
