
package SalesManagement.dao;

import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import SalesManagement.dto.DetailBill;
import SalesManagement.dto.Product;
import SalesManagement.dto.PromotionsCustomer;
import SalesManagement.dto.PromotionsProduct;
import java.sql.Date;
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
public class DetailBillDAOTest {
    
    @InjectMocks
    DetailBillDAO detailBillDAO;
        
    @Mock
    JdbcTemplate template;
    
    @Autowired
    DetailBill detailBill;
    
    @Autowired
    PromotionsProduct promotionsProduct;
    
    @Autowired
    PromotionsCustomer promotionCustomer;
    
    @Autowired
    Product product;
    
    @Autowired
    Customer customer;
    

    @Autowired
    Bill bill;
 
    // test get info bill with bill have all info
    @Test
    public void testGetInfoBill() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount(Float.parseFloat("10"));
        product = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, promotionsProduct);
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        customer = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer); 
        
        bill = new Bill();
        bill.setId(1);
        bill.setTotal(100000);
        bill.setCustomerId(customer.getId());
        bill.setDiscount((float)10);
        bill.setPromotionCustomerId(customer.getPromotionsId());
        bill.setCustomer(customer);
        bill.setPromotionCustomer(promotionCustomer);     
        bill.setDateCreate((java.util.Date)formatter.parse("2021/06/30"));
        
        detailBill = new DetailBill();
        detailBill.setId(1);
        detailBill.setBillId(bill.getId());
        detailBill.setLastPrice(1000000);
        detailBill.setProductId(product.getId());
        detailBill.setQuantity(1);
        detailBill.setPromotionProductId(product.getPromotionsId());
        detailBill.setProduct(product);
        detailBill.setPromotion(promotionsProduct);
        
        List<DetailBill> lstExpect = new ArrayList<>();
        lstExpect.add(detailBill);
               
        Mockito.when(template.query(Mockito.anyString(), Mockito.any(Object[].class) ,ArgumentMatchers.<RowMapper<DetailBill>>any())).thenReturn(lstExpect);   
        
        List<DetailBill> lstResult = detailBillDAO.findAll(1);
              
        assertEquals(lstExpect.size(), lstResult.size());      
    }

    // test get info bill with bill not apply promotion customer
    @Test
    public void testGetInfoBill_NotPromotionCustomer() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount(Float.parseFloat("10"));
        product = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, promotionsProduct);
        
        customer = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, -1, 1000, null); 
        
        bill = new Bill();
        bill.setId(1);
        bill.setTotal(100000);
        bill.setCustomerId(customer.getId());
        bill.setDiscount((float)10);
        bill.setPromotionCustomerId(customer.getPromotionsId());
        bill.setCustomer(customer);
        bill.setPromotionCustomer(null);     
        bill.setDateCreate((java.util.Date)formatter.parse("2021/06/30"));
        
        detailBill = new DetailBill();
        detailBill.setId(1);
        detailBill.setBillId(bill.getId());
        detailBill.setLastPrice(1000000);
        detailBill.setProductId(product.getId());
        detailBill.setQuantity(1);
        detailBill.setPromotionProductId(product.getPromotionsId());
        detailBill.setProduct(product);
        detailBill.setPromotion(promotionsProduct);
        
        List<DetailBill> lstExpect = new ArrayList<>();
        lstExpect.add(detailBill);
               
        Mockito.when(template.query(Mockito.anyString(), Mockito.any(Object[].class) ,ArgumentMatchers.<RowMapper<DetailBill>>any())).thenReturn(lstExpect);   
        
        List<DetailBill> lstResult = detailBillDAO.findAll(1);
               
        assertEquals(lstExpect, lstResult);      
    }
    
    // test get info bill with bill not apply promotion product
    @Test
    public void testGetInfoBill_NotPromotionProduct() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        product = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , null, null);
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        customer = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer); 
        
        bill = new Bill();
        bill.setId(1);
        bill.setTotal(100000);
        bill.setCustomerId(customer.getId());
        bill.setDiscount((float)10);
        bill.setPromotionCustomerId(customer.getPromotionsId());
        bill.setCustomer(customer);
        bill.setPromotionCustomer(promotionCustomer);     
        bill.setDateCreate((java.util.Date)formatter.parse("2021/06/30"));
        
        detailBill = new DetailBill();
        detailBill.setId(1);
        detailBill.setBillId(bill.getId());
        detailBill.setLastPrice(1000000);
        detailBill.setProductId(product.getId());
        detailBill.setQuantity(1);
        detailBill.setPromotionProductId(product.getPromotionsId());
        detailBill.setProduct(product);
        detailBill.setPromotion(null);
        
        List<DetailBill> lstExpect = new ArrayList<>();
        lstExpect.add(detailBill);
               
        Mockito.when(template.query(Mockito.anyString(), Mockito.any(Object[].class) ,ArgumentMatchers.<RowMapper<DetailBill>>any())).thenReturn(lstExpect);   
        
        List<DetailBill> lstResult = detailBillDAO.findAll(1);
              
        assertEquals(lstExpect.get(0).getPromotion(), lstResult.get(0).getPromotion());      
    }
    
    // test get info bill with bill not existed
    @Test
    public void testGetInfoBill_NotBill() throws ParseException {     
        List<DetailBill> lstExpect = new ArrayList<>();
               
        Mockito.when(template.query(Mockito.anyString(), Mockito.any(Object[].class) ,ArgumentMatchers.<RowMapper<DetailBill>>any())).thenReturn(lstExpect);   
        
        List<DetailBill> lstResult = detailBillDAO.findAll(1);
              
        assertEquals(0, lstResult.size());      
    }
    
    // test get info bill with bill not have discount
    @Test
    public void testGetInfoBill_NotDiscount() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount(Float.parseFloat("10"));
        product = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, promotionsProduct);
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        customer = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer); 
        
        bill = new Bill();
        bill.setId(1);
        bill.setTotal(100000);
        bill.setCustomerId(customer.getId());
        bill.setDiscount((float) 0);
        bill.setPromotionCustomerId(customer.getPromotionsId());
        bill.setCustomer(customer);
        bill.setPromotionCustomer(promotionCustomer);     
        bill.setDateCreate((java.util.Date)formatter.parse("2021/06/30"));
        
        detailBill = new DetailBill();
        detailBill.setId(1);
        detailBill.setBillId(bill.getId());
        detailBill.setLastPrice(1000000);
        detailBill.setProductId(product.getId());
        detailBill.setQuantity(1);
        detailBill.setPromotionProductId(product.getPromotionsId());
        detailBill.setProduct(product);
        detailBill.setPromotion(promotionsProduct);
        
        List<DetailBill> lstExpect = new ArrayList<>();
        lstExpect.add(detailBill);              
        Mockito.when(template.query(Mockito.anyString(), Mockito.any(Object[].class) ,ArgumentMatchers.<RowMapper<DetailBill>>any())).thenReturn(lstExpect);         
        List<DetailBill> lstResult = detailBillDAO.findAll(1);
        assertEquals(lstExpect, lstResult);      
    }
}
