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
    private PromotionsProduct Promotions;

    // Phương thức lấy giá bán của sản phẩm
    // Lưu ý rằng giá bán này còn có thể giảm dựa vào ưu đãi của khách hàng.
    public Float getProductSalePrice() {
        // Kiểm tra có ưu đãi và tính
        Float sale = Promotions != null ?  HistoricalCost * (Promotions.getPercentDiscount()/100) : 0;
        // Giá bán = giá gốc - (chiết khấu + ưu đãi)
        return HistoricalCost - (HistoricalCost * (TradeDiscount/100) + sale);
    }

    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
