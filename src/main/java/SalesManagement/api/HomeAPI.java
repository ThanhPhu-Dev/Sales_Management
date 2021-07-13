package SalesManagement.api;


import SalesManagement.dao.BillDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.Product;
import SalesManagement.dto.Home;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeAPI {
    @Autowired
    private BillDAO billDao;

    @GetMapping("/trang-chu")
    public ResponseEntity<Object> getAllBillByDate(
            //@RequestParam(value = "draw", required = true) int draw,
            @RequestParam(value = "month", required = false, defaultValue = "6") Integer month,
            @RequestParam(value = "year", required = false, defaultValue = "2021") Integer year) {
        Map<String, Object> map = new HashMap<>();
        
        List<Bill> bills = billDao.findbyDate(month, year);
        //map.put("draw", draw);
        map.put("data", bills);
        //return bills;
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

//    @GetMapping("/trang-chu")
//    public ResponseEntity<Object> getAllBillByDate(
//            @RequestParam(value = "month", required = false, defaultValue = "6") Integer month,
//            @RequestParam(value = "year", required = false, defaultValue = "2021") Integer year)
//    {
//        List<Bill> bills = billDao.findbyDate(month, year);
//        return new ResponseEntity<Object>(bills, HttpStatus.OK);
//
//    }
}
