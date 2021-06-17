package SalesManagement.dao;

import SalesManagement.dto.Bill;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
                b.setPromotionCustomerId(rs.getInt("PromotionCustomerId"));
                b.setDateCreate(rs.getDate("DateCreate"));
                b.setCustomer(customerDao.findCustomerById(b.getCustomerId()));
                b.setPromotionCustomer(promotionsCustomerDAO.findPromotionById(b.getPromotionCustomerId()));
                return b;
            }
        });
    }

    public Bill findBillById(int id) {
        String sql = "select * from bills where Id=?";
        Bill bill=  template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Bill.class));
        bill.setCustomer(customerDao.findCustomerById(bill.getCustomerId()));
        if(bill.getPromotionCustomerId() != null){
            bill.setPromotionCustomer(promotionsCustomerDAO.findPromotionById(bill.getPromotionCustomerId()));
        }
        return bill;
    }

    // return bill id after inserted
    public int createBill(Bill bill) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(template.getDataSource());

        String sql = "INSERT INTO BILLS (TOTAL, CUSTOMERID, DISCOUNT, PROMOTIONCUSTOMERID, DATECREATE)" +
                " VALUES(:total, :customerId, :discount, :promotionsId, :date)";
        // parameters
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("customerId", bill.getCustomerId());
        parameters.addValue("discount", bill.getDiscount());
        parameters.addValue("promotionsId", bill.getPromotionCustomerId());
        parameters.addValue("total", 0);
        parameters.addValue("date", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = namedParameterJdbcTemplate.update(sql, parameters, keyHolder);

        return row == 1 ? keyHolder.getKey().intValue() : -1;
    }

    public int updateTotalBill(int id, int total) {
        String sql = "UPDATE BILLS SET TOTAL = ? WHERE ID = ?";

        return template.update(sql, new Object[] {total, id});
    }
}
