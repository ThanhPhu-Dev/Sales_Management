package SalesManagement.dao;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class PromotionsProductDAO {
    private JdbcTemplate template;
}
