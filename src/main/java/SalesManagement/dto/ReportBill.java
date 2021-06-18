package SalesManagement.dto;

import lombok.Data;

@Data
public class ReportBill {
    private Integer stt;
    private String nameProduct;
    private String specification;
    private String historicalCost;
    private Float tradeDiscount;
    private String namePromotionProduct;
    private Float percentDiscountProduct;
    private String quantity;
    private String lastPrice;
}
