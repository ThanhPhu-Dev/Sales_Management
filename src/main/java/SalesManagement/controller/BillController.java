package SalesManagement.controller;

import SalesManagement.dao.BillDAO;
import SalesManagement.dao.DetailBillDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.DetailBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class BillController {

    @Autowired
    private BillDAO billDAO;

    @Autowired
    private DetailBillDAO detailBillDAO;

    @GetMapping("/bills")
    public String index() {
        return "Bill/bill";
    }

    @GetMapping("/Detailbill")
    public String detailbill(@RequestParam String id, Model model) {
        Integer Id = Integer.parseInt(id);
        Bill bill = billDAO.findBillById(Id);
        List<DetailBill> dt = detailBillDAO.findAll(Id);
        model.addAttribute("bill", bill);
        model.addAttribute("detail", dt);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = df.format(bill.getDateCreate());
        model.addAttribute("date", dateString);
        float lastprice = 0;
        float total;
        Integer price;
        float lastprice1dt;

        for (DetailBill deta : dt) {
            price = deta.getProduct().getHistoricalCost();
            lastprice1dt = (price-(price*(deta.getProduct().getTradeDiscount()/100) + price*(deta.getPromotion().getPercentDiscount()/100)))*deta.getQuantity();
            lastprice += lastprice1dt;
        }
        float promotion = 0;
        if(bill.getPromotionCustomer() != null){
            promotion=bill.getPromotionCustomer().getPercentDiscount();
        }
        total = lastprice - (lastprice*(promotion/100) + lastprice*(bill.getDiscount()/100));
        return "Bill/DetailBill";
    }
}
