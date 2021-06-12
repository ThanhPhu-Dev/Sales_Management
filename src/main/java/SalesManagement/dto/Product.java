package SalesManagement.dto;

import lombok.Data;

@Data
public class Product {
    private Integer Id;
    private String Name;
    private Integer Specification;
    private Integer HistoricalCost;
    private Float TradeDiscount;
    private Integer PromotionsId;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
