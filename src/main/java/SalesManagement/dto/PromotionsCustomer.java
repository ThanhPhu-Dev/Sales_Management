package SalesManagement.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PromotionsCustomer {
    private Integer Id;
    private String Name;
    private Date StartDate;
    private Date EndDate;
    private  Float PercentDiscount;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
