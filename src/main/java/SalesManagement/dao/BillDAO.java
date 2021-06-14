package SalesManagement.dao;

import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@Repository
public class BillDAO {
    private JdbcTemplate template;

    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private PromotionsCustomerDAO promotionsCustomerDAO;

    public List<Bill> findAll(){
        return template.query("select * from bills", new RowMapper<Bill>() {
            public Bill mapRow(ResultSet rs, int row) throws SQLException {
                Bill b = new Bill();
                b.setId(rs.getInt("Id"));
                b.setCustomerId(rs.getInt("CustomerId"));
                b.setTotal(rs.getInt("Total"));
                b.setDiscount(rs.getFloat("Discount"));
                b.setPromotionCustomerId(rs.getInt("PromotionCustomer"));
                b.setDateCreate(rs.getDate("DateCreate"));
                b.setCustomer(customerDao.findCustomerById(b.getCustomerId()));
                b.setPromotion(promotionsCustomerDAO.findPromotionById(b.getPromotionCustomerId()));
                return b;
            }
        });
    }
}
