package SalesManagement.dto;

import lombok.Data;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Integer Id;
    private Integer Total;
    private Integer CustomerId;
    private Float   Discount;
    private Integer PromotionCustomerId;
    private Date    DateCreate;
    private Customer Customer;
    private PromotionsCustomer PromotionCustomer;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
