package SalesManagement.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Receipt {
    private Integer Id;
    private Integer CustomerId;
    private Integer Price;
    private Date    DateCreate;
    //tạo getter - setter ngầm rồi. bên ngoài gọi getter-setter như bình thường
}
