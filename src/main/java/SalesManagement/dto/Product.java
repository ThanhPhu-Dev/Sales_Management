package SalesManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Integer Id;
    private String SKU;
    private String Name;
    private Integer Specification;
    private Integer HistoricalCost;
    private Float TradeDiscount;
    private Integer PromotionsId;
    private PromotionsProduct Promotions;

    // Phương thức lấy giá bán của sản phẩm
    // Lưu ý rằng giá bán này còn có thể giảm dựa vào ưu đãi của khách hàng.
    public Float getProductSalePrice() {
        // Kiểm tra có ưu đãi và tính
        Float sale = this.promotionsToPrice();
        // Giá bán = giá gốc - (chiết khấu + ưu đãi)
        return HistoricalCost - (HistoricalCost * (TradeDiscount/100) + sale);
    }

    // Phương thức lấy số tiền được giảm từ chương trình khuyến mãi
    public Float promotionsToPrice() {
        Float salePrice = 0f;
        if(Promotions != null) {
            if(Promotions.getStatus() == 1) {
                salePrice = HistoricalCost * (Promotions.getPercentDiscount()/100);
            }
        }

        return salePrice;
    }

    // Phương thức tính số tiền chiết khấu
    public  Float discountPrice() {
        Float discount = TradeDiscount != null ? HistoricalCost * (TradeDiscount/100) : 0;
        return discount;
    }

}
