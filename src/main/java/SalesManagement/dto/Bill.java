package SalesManagement.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Bill {
    private Integer Id;
    private Integer DetailId;
    private Integer Total;
    private Integer CustomerId;
    private Float   Discount;
    private Integer PromotionCustomer;
    private Date    DateCreate;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}