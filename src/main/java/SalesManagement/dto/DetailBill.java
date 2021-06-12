package SalesManagement.dto;

import lombok.Data;

@Data
public class DetailBill {
    private Integer BillId;
    private Integer Quantity;
    private Integer ProductId;
    private Integer LastPrice;
    private Integer PromotionProduct;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
