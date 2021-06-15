package SalesManagement.api;

import SalesManagement.dao.BillDAO;
import SalesManagement.dto.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BillAPI {

    @Autowired
    private BillDAO billDao;

    @PostMapping("/bills")
    public List<Bill> getAllBill(){
        List<Bill> bills = billDao.findAll();
        return bills;
    }

}
