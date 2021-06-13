package SalesManagement.dao;

import SalesManagement.dto.Customer;
import SalesManagement.dto.PromotionsCustomer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class PromotionsCustomerDAO {

    private JdbcTemplate template;

    public List<PromotionsCustomer> findAllPromitionsCustomer() {
        return template.query("select * from promotions_customers", new RowMapper<PromotionsCustomer>() {
            public PromotionsCustomer mapRow(ResultSet rs, int row) throws SQLException {
                PromotionsCustomer e = new PromotionsCustomer();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setStartDate(rs.getDate(3));
                e.setEndDate(rs.getDate(4));
                e.setPercentDiscount(rs.getFloat(5));
                return e;
            }
        });
    }
}
