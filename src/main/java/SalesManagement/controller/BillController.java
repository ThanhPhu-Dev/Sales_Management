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
        float lastprice = 0;
        Integer price;
        Integer Id = Integer.parseInt(id);
        Bill bill = billDAO.findBillById(Id);
        List<DetailBill> dt = detailBillDAO.findAll(Id);
        model.addAttribute("bill", bill);
        model.addAttribute("detail", dt);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = df.format(bill.getDateCreate());
        model.addAttribute("date", dateString);
        for (DetailBill deta : dt) {
            price = deta.getProduct().getHistoricalCost();
            lastprice += (price-(price*(deta.getProduct().getTradeDiscount()/100) + price*(deta.getPromotion().getPercentDiscount()/100)))*deta.getQuantity();
        }
        model.addAttribute("sumbill", (int)lastprice);
        model.addAttribute("discount",(int)(lastprice*(bill.getDiscount()/100)));
        model.addAttribute("promotion",(int)(lastprice*(bill.getPromotionCustomer().getPercentDiscount()/100)));
        return "Bill/DetailBill";
    }
}
