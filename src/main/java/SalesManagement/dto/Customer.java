package SalesManagement.dto;

import lombok.Data;

@Data
public class Customer {
    private Integer Id;
    private String Name;
    private String NumberCard;
    private Integer AccountBalance;
    private Integer PromotionsId;
    private Integer DebtMax;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
