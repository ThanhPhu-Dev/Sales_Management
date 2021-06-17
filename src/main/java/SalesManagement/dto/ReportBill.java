package SalesManagement.dto;

import lombok.Data;

@Data
public class ReportBill {
    private Integer stt;
    private String nameProduct;
    private Integer specification;
    private Integer historicalCost;
    private Float tradeDiscount;
    private String namePromotionProduct;
    private Float percentDiscountProduct;
    private Integer quantity;
    private Integer lastPrice;
}
