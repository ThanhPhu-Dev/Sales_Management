package SalesManagement.dto;

import lombok.Data;

@Data
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

   
     public Customer(Integer Id, String Name, String NumberCard, String Phone, String IdentityCard, Integer AccountBalance, Integer PromotionsId, Integer DebtMax, PromotionsCustomer Promotion) {
        this.Id = Id;
        this.Name = Name;
        this.NumberCard = NumberCard;
        this.Phone = Phone;
        this.IdentityCard = IdentityCard;
        this.AccountBalance = AccountBalance;
        this.PromotionsId = PromotionsId;
        this.DebtMax = DebtMax;
        this.Promotion = Promotion;
    }

    public Customer() {
        this.Id = 0;
        this.Name = "";
        this.NumberCard = "";
        this.Phone = "";
        this.IdentityCard = "";
        this.AccountBalance = 0;
        this.PromotionsId = 0;
        this.DebtMax = 0;
        this.Promotion = null;
    } 
    
}
