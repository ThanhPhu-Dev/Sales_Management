
package SalesManagement.dao;

import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import SalesManagement.dto.PromotionsCustomer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@RunWith(MockitoJUnitRunner.class)
public class BillDAOTest {
    
    @InjectMocks
    BillDAO billDAO;
    
    @Mock
    JdbcTemplate template;
    
    @Mock 
    CustomerDAO customerDao;
    
    @Mock 
    PromotionsCustomerDAO promotionCustomerDAO;
    
    @Autowired
    Bill bill;
    
    @Autowired
    PromotionsCustomer promotionCustomer;
    
    @Autowired
    Customer customer;
   
    
    public BillDAOTest() {
    }

    //test get all bill with list bill existed
    @Test
    public void testGetAllBill() throws ParseException {
        promotionCustomer = new PromotionsCustomer();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        customer = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);
        
        bill = new Bill();
        bill.setId(1);
        bill.setTotal(100000);
        bill.setCustomerId(1);
        bill.setDiscount((float)10);
        bill.setPromotionCustomerId(1);
        bill.setCustomer(customer);
        bill.setPromotionCustomer(promotionCustomer);
        bill.setDateCreate((java.util.Date)formatter.parse("2021/06/30"));
        
        List<Bill> lstExpect = new ArrayList<>();
        lstExpect.add(bill);
               
        Mockito.when(template.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<Bill>>any())).thenReturn(lstExpect);   
        
        List<Bill> lstResult = billDAO.findAll();
              
        assertEquals(lstExpect.size(), lstResult.size());
    }

    //test get all bill with list bill empty
    @Test
    public void testGetAllBillEmptyList() throws ParseException {        
        List<Bill> lstExpect = new ArrayList<>();
               
        Mockito.when(template.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<Bill>>any())).thenReturn(lstExpect);   
        
        List<Bill> lstResult = billDAO.findAll();
              
        assertEquals(0, lstResult.size());
    }
    
    //test get list bill with discount = 0
    @Test
    public void testGetListBillDiscountNone() throws ParseException {
        promotionCustomer = new PromotionsCustomer();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        customer = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);
        
        bill = new Bill();
        bill.setId(1);
        bill.setTotal(100000);
        bill.setCustomerId(1);
        bill.setDiscount((float) 0);
        bill.setPromotionCustomerId(1);
        bill.setCustomer(customer);
        bill.setPromotionCustomer(promotionCustomer);
        bill.setDateCreate((java.util.Date)formatter.parse("2021/06/30"));
        
        List<Bill> lstExpect = new ArrayList<>();
        lstExpect.add(bill);
               
        Mockito.when(template.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<Bill>>any())).thenReturn(lstExpect);   
        
        List<Bill> lstResult = billDAO.findAll();
              
        assertEquals(lstExpect.get(0).getDiscount() ,lstResult.get(0).getDiscount());
    }
    
    //test get list bill with  none promotion product
    @Test
    public void testGetListBillNonePromotionProduct() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        customer = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, null, 1000, null);
        
        bill = new Bill();
        bill.setId(1);
        bill.setTotal(100000);
        bill.setCustomerId(1);
        bill.setDiscount((float) 10);
        bill.setPromotionCustomerId(1);
        bill.setCustomer(customer);
        bill.setPromotionCustomer(null);
        bill.setDateCreate((java.util.Date)formatter.parse("2021/06/30"));
        
        List<Bill> lstExpect = new ArrayList<>();
        lstExpect.add(bill);
               
        Mockito.when(template.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<Bill>>any())).thenReturn(lstExpect);   
        
        List<Bill> lstResult = billDAO.findAll();
              
        assertEquals(lstExpect.get(0).getPromotionCustomer() ,lstResult.get(0).getPromotionCustomer());
    }
}
