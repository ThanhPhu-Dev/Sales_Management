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
        Float sale = this.promotionsToPrice();
        // Giá bán = giá gốc - (chiết khấu + ưu đãi)
        return HistoricalCost - (HistoricalCost * (TradeDiscount/100) + sale);
    }

    // Phương thức lấy số tiền được giảm
    public Float promotionsToPrice() {
        Float sale = Promotions != null ?  HistoricalCost * (Promotions.getPercentDiscount()/100) : 0;
        return sale;
    }

    // Phương thức tính số tiền chiết khấu
    public  Float discountPrice() {
        Float discount = TradeDiscount != null ? HistoricalCost * (TradeDiscount/100) : 0;
        return discount;
    }

    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường

    public Product(Integer Id, String Name, Integer Specification, Integer HistoricalCost, Float TradeDiscount, Integer PromotionsId, PromotionsProduct Promotions) {
        this.Id = Id;
        this.Name = Name;
        this.Specification = Specification;
        this.HistoricalCost = HistoricalCost;
        this.TradeDiscount = TradeDiscount;
        this.PromotionsId = PromotionsId;
        this.Promotions = Promotions;
    }

    public Product() {
        this.Id = 0;
        this.Name = "";
        this.Specification = 0;
        this.HistoricalCost = 0;
        this.TradeDiscount = null;
        this.PromotionsId = 0;
        this.Promotions=null;
    }
    
    
}
