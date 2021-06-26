package SalesManagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
public class PromotionsProduct {
    private Integer Id;
    private String Name;
    private Date StartDate;
    private Date EndDate;
    private Float PercentDiscount;

    public int getStatus() {
        LocalDate now = LocalDate.now();

        if(now.isBefore(EndDate.toLocalDate())) {
            return 1;
        }
        return 0;
    }
}
