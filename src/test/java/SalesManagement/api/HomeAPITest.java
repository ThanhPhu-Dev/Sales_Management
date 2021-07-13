/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.api;

import SalesManagement.dao.BillDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import SalesManagement.dto.Product;
import SalesManagement.dto.PromotionsCustomer;
import SalesManagement.dto.PromotionsProduct;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author phihung
 */

@RunWith(MockitoJUnitRunner.class)
public class HomeAPITest {
    
    @Mock
    BillDAO billDao;
    
    @InjectMocks
    HomeAPI homeAPI;
    
    @Autowired
    PromotionsProduct promotionsProduct;
    
    @Autowired
    PromotionsCustomer promotionCustomer;
    
    public HomeAPITest() {
    }

    // lấy lịch bán hàng khi tồn tại danh sách hoá đơn
    @Test
    public void testGetAllBillByDate() throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/30"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);
        
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(1, 1000000, 1, (float) 10, 1,(java.util.Date)formatter.parse("2021/06/20") , cus, promotionCustomer));
        
        Integer month = 6;
        Integer year = 2021;
        
        Mockito.when(billDao.findbyDate(month,year)).thenReturn(bills);  
        
        ResponseEntity<Object> responseEntity = homeAPI.getAllBillByDate(month, year);
        
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");  
        
        assertEquals(bills.size(), lstResult.size());
    }
    
    // lấy lịch bán hàng khi không tồn tại danh sách hoá đơn
    @Test
    public void testGetAllBillByDate_noneList() throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        List<Bill> bills = new ArrayList<>(); 
        
        Integer month = 6;
        Integer year = 2021;
        
        Mockito.when(billDao.findbyDate(month,year)).thenReturn(bills);  
        
        ResponseEntity<Object> responseEntity = homeAPI.getAllBillByDate(month, year);
        
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");  
        
        assertEquals(bills.size(), lstResult.size());
    }
      
}
