package SalesManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BillController {

    @GetMapping("/bills")
    public String index(){
        return "Bill/bill";
    }
}
