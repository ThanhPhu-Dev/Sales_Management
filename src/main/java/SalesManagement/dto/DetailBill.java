package SalesManagement.dto;

import lombok.Data;

@Data
public class DetailBill {
    private Integer Id;
    private Integer BillId;
    private Integer Quantity;
    private Integer ProductId;
    private Integer LastPrice;
    private Integer PromotionProductId;

    private Product Product;
    private PromotionsProduct Promotion;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
