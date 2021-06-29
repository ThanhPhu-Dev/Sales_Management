package SalesManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer Id;
    private String Name;
    private String NumberCard;
    private String Phone;
    private String IdentityCard;
    private Integer AccountBalance;
    private Integer PromotionsId;
    private Integer DebtMax;

    private PromotionsCustomer Promotion;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường

    // Kiểm tra khách hàng có phải công nợ
    public boolean isDebtor() {
        // balance < 0, X > 0
        // balance + X < 0 => true
        return this.DebtMax + this.AccountBalance < 0;
    }

    // Kiểm tra với số tiền khấu trừ ${price} khách hàng có trở thành công nợ
    public boolean isDebtor(int price) {
        // balance < 0, X > 0
        // balance + X < 0 => true
        return this.DebtMax + (this.AccountBalance - price) < 0;
    }
}
