package SalesManagement.dao;

import SalesManagement.dto.Bill;
import SalesManagement.dto.DetailBill;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@Repository
public class DetailBillDAO {
    private JdbcTemplate template;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private PromotionsProductDAO promotionsProductDAO;

    @Autowired
    private BillDAO billDAO;

    public List<DetailBill> findAll(Integer id){
        return  template.query("select * from detail_bills where BillId = ?",new Object[]{id}, new RowMapper<DetailBill>() {
            public DetailBill mapRow(ResultSet rs, int row) throws SQLException {
                DetailBill detail = new DetailBill();
                detail.setBillId(rs.getInt("Id"));
                detail.setBillId(rs.getInt("BillId"));
                detail.setQuantity(rs.getInt("Quantity"));
                detail.setProductId(rs.getInt("ProductId"));
                detail.setLastPrice(rs.getInt("LastPrice"));
                detail.setPromotionProductId(rs.getInt("PromotionProductId"));
                detail.setPromotion(promotionsProductDAO.findPromotionById(detail.getPromotionProductId()));
                detail.setProduct(productDAO.findProductById(detail.getProductId()));
                return detail;
            }
        });

    }
}
