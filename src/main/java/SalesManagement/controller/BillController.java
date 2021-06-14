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

import java.util.List;

@Controller
public class BillController {

    @Autowired
    private BillDAO billDAO;

    @Autowired
    private DetailBillDAO detailBillDAO;

    @GetMapping("/bills")
    public String index(){
        return "Bill/bill";
    }

    @GetMapping("/Detailbill")
    public String detailbill(@RequestParam String id, Model model){
        Integer Id = Integer.parseInt(id);
        Bill bill = billDAO.findBillById(Id);
        List<DetailBill> dt = detailBillDAO.findAll(Id);
        model.addAttribute("bill", bill);
        model.addAttribute("detail",dt);
        return "Bill/DetailBill";
    }
}
