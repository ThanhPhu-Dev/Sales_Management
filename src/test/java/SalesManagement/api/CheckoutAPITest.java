
package SalesManagement.api;

import SalesManagement.dao.BillDAO;
import SalesManagement.dao.CustomerDAO;
import SalesManagement.dao.DetailBillDAO;
import SalesManagement.dao.ProductDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import SalesManagement.dto.DetailBill;
import SalesManagement.dto.Product;
import SalesManagement.dto.PromotionsCustomer;
import SalesManagement.dto.PromotionsProduct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutAPITest {
             
    @Mock
    ProductDAO productDAO;
    
    @Mock
    CustomerDAO customerDAO;
    
    @Mock 
    BillDAO billDAO;
    
    @Mock
    DetailBillDAO detailBillDAO;

    @InjectMocks
    CheckoutAPI checkAPI;
    
    @Autowired
    PromotionsProduct promotionsProduct;
    
    @Autowired
    PromotionsCustomer promotionCustomer;
    
    @Autowired
    Bill bill;
    
    @Autowired
    DetailBill detailBill;
    
    public CheckoutAPITest() {
    }

    //test search product by id
    @Test
    public void testSearchProductById() {
        List<Product> products = new ArrayList<>();    
        products.add(new Product(1, "SP01" ,"SanPham01", 1000, 1000, (float) 1 , 1, null));      
        List<Integer> ids = new ArrayList<>();
        Mockito.when(productDAO.getProductsPaginationWithSearch(0, 0, ids, "1")).thenReturn(products);                 
        Map<String, List<Product>> map = checkAPI.getProducts(0, 0, "", "1");
        List<Product> lstExpected = map.get("products");        
        assertEquals(products.size(), lstExpected.size());
    }

    
    //test get all product
    @Test
    public void testGetAllProduct() {
        List<Product> products = new ArrayList<>();        
        products.add(new Product(1, "SP01" ,"SanPham01", 1000, 1000, (float) 1 , 1, null));       
        List<Integer> ids = new ArrayList<>();
        Mockito.when(productDAO.getProductsPaginationWithSearch(0, 0, ids, null)).thenReturn(products);                  
        Map<String, List<Product>> map = checkAPI.getProducts(0, 0, "", null);        
        List<Product> lstExpected = map.get("products");       
        assertEquals(products.get(0).getName(), lstExpected.get(0).getName());
    }
    
    //test get list product when click button add product
    @Test
    public void testGetListProudctWhenClickedAddProductButton() {
        List<Product> products = new ArrayList<>();     
        products.add(new Product(1,"SP01" ,"SanPham01", 1000, 1000, (float) 10 , 1, null));
        products.add(new Product(2, "SP02" ,"SanPham02", 1000, 1000, (float) 20 , 2, null));   
        List<Integer> ids = new ArrayList<>();
        ids.add(2);      
        Product productWhenClickAddBtn = products.remove(1);          
        Mockito.when(productDAO.getProductsPaginationWithSearch(0, 0, ids, null)).thenReturn(products);               
        Map<String, List<Product>> map = checkAPI.getProducts(0, 0, "2", null);      
        List<Product> lstExpected = map.get("products");       
        assertEquals(products.size(), lstExpected.size());
    }
    
    //test get list product when click button remove product
    @Test
    public void testGetListProudctWhenClickedRemoveProductButton() {
        List<Product> products = new ArrayList<>();     
        products.add(new Product(1,"SP01" ,"SanPham01", 1000, 1000, (float) 10 , 1, null)); 
        List<Integer> ids = new ArrayList<>();
        products.add(new Product(1,"SP02" ,"SanPham02", 1000, 1000, (float) 20 , 1, null));   
        Product productsThenRemove = products.remove(1);          
        Mockito.when(productDAO.getProductsPaginationWithSearch(0, 0, ids, null)).thenReturn(products);               
        Map<String, List<Product>> map = checkAPI.getProducts(0, 0, "", null);      
        List<Product> lstExpected = map.get("products");       
        assertEquals(products.size(), lstExpected.size());
    }
    
    //Test get originPrice of product then add to cart
    @Test
    public void testGetHistoricalCostProductIntoCart() {  
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);       
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json);
        assertEquals(pro.getHistoricalCost().toString(), lstResult.get("origin"));
    }
    
    //Test get ProductSale of product then add to cart with promotions is null
    @Test
    public void testGetProductSaleProductIntoCartWithPromotionNull() {  
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);       
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json);
        assertEquals(Integer.toString(0), lstResult.get("productSale"));
    }
    
    //Test get ProductSale of product then add to cart with promotions is null
    @Test
    public void testGetProductSaleProductIntoCartWithPromotionNotNull() throws ParseException {  
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);       
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount(Float.parseFloat("10"));
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json);
        //HistoricalCost * (Promotions.getPercentDiscount()/100) * quantity
        long expectedPrice = (long) (pro.getHistoricalCost() * (promotionsProduct.getPercentDiscount()/100) * 1);
        assertEquals(Long.toString(expectedPrice), lstResult.get("productSale"));
    }
    
    //Test get customerSale then add to cart with customer promotion null
    @Test
    public void testGetCustomerSaleProductIntoCartWithCustomerPromotionNull() throws ParseException {  
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "Sp01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json);       
        assertEquals(Integer.toString(0), lstResult.get("customerSale"));
    }
    
    //Test get customerSale then add to cart with customer promotion not null
    @Test
    public void testGetCustomerSaleProductIntoCartWithCustomerPromotionNotNull() throws ParseException {  
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        promotionCustomer = new PromotionsCustomer();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, Float.parseFloat("10") , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json);   
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSaleExpected = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts);
        assertEquals(Long.toString(customerSaleExpected), lstResult.get("customerSale"));
    }
    
    //test get discount product
    @Test
    public void testGetDiscountProduct()
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long tradeDiscouctExpected = (long)(pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        assertEquals(Long.toString(tradeDiscouctExpected), lstResult.get("discount"));
    }
    
    //test get total bill with not promotion product, not extra promotion, have customer product, discout
    @Test
    public void testGetTotalOfBill_NotPromotionProduct_NotExtraPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        promotionCustomer = new PromotionsCustomer();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not promotion product, not discount, have customer product, have extra promo
    @Test
    public void testGetTotalOfBill_NotPromotionProduct_NotDiscout() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();    
        promotionCustomer = new PromotionsCustomer();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + (((float)10 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not promotion product, not discount, not extra promo, have customer product
    @Test
    public void testGetTotalOfBill_NotPromotionProduct_NotDiscout_NotExtraPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        promotionCustomer = new PromotionsCustomer();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not promotion product, have customer product, discout, have extra promotion
    @Test
    public void testGetTotalOfBill_NotPromotionProduct_HaveExtraPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();    
        promotionCustomer = new PromotionsCustomer();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = (long)((cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts) + (((float)10 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not customer promo, not discount, not extra promo, have product promo
    @Test
    public void testGetTotalOfBill_NotPromotionCustomer_NotDiscount_NotExtraPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = 0;
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not customer promo, not extra promo, have product promo, discount
    @Test
    public void testGetTotalOfBill_NotPromotionCustomer_NotExtraPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = 0;
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not customer promo, not discount, have product promo, extra promo
    @Test
    public void testGetTotalOfBill_NotPromotionCustomer_NotDiscount() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();    
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = (long) (((float)10 / 100) * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with have customer promo, have product promo, not discount, not extra promo
    @Test
    public void testGetTotalOfBill_HaveCusPromo_HaveProductPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = (originPrice - (productSale + discount));
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with have customer promo, have product promo, have discount, not extra promo
    @Test
    public void testGetTotalOfBill_HaveCusPromo_HaveProductPromo_HaveDiscount() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);
        Product pro = new Product(1, "SP01" , "SanPham01", 1000, 200000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with have customer promo, have product promo, have extra, not discount
    @Test
    public void testGetTotalOfBill_HaveCusPromo_HaveProductPromo_HaveExtraPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();    
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + (((float)10 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not customer promo, not product promo, not extra, not discount
    @Test
    public void testGetTotalOfBill_NotCusPromo_NotProductPromo_NotExtraPromo_NotDiscount()
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = 0;
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not customer promo, not product promo, not extra, have discount
    @Test
    public void testGetTotalOfBill_NotCusPromo_NotProductPromo_NotExtraPromo_HaveDiscount()
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = 0;
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not customer promo, not product promo, not discount, have extra
    @Test
    public void testGetTotalOfBill_NotCusPromo_NotProductPromo_NotDiscount_HaveExtra()
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 0 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long) (((float)10 / 100) * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with not customer promo, not product promo, have discount, have extra
    @Test
    public void testGetTotalOfBill_NotCusPromo_NotProductPromo_HaveDiscount_HaveExtra()
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();    
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long) (((float)10 / 100) * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test get total bill with have customer promo, have product promo, have discount, have extra
    @Test
    public void testGetTotalOfBill_HaveCusPromo_HaveProductPromo_HaveDiscount_HaveExtra() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 200000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + (((float)10 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals(Long.toString(totalOfBill), lstResult.get("total"));
    }
    
    //test promotion higher value bill
    @Test
    public void testPromotionHigherValueBill() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 50);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("80"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 0);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + (((float)50 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals("false", lstResult.get("success"));
    }
    
    //test promotion lower value bill
    @Test
    public void testPromotionLowerValueBill() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 0);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + (((float)50 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals("true", lstResult.get("success"));
    }
    
    //test customer is a debtor
    @Test
    public void testCustomerIsDebtor() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "50");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 0);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost() * 50);
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100) * 50);
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100) * 50);
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + (((float)10 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals("true", lstResult.get("isDeptor"));
    } 
    
    //test customer is not a debtor
    @Test
    public void testCustomerIsNotDebtor() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 0);
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 0 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        Map<String, String> lstResult = checkAPI.calcCartInfo(json); 
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + (((float)10 / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        assertEquals("false", lstResult.get("isDeptor"));
    } 
    
    // test checkout have promotion product, promotion customer, extra promotion
    @Test
    public void testCheckout_promoProduct_promoCustomer_extraPromo() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));     
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 100000000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
               
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);  
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount(jsonObj.getFloat("extraPromotions"));
        bill.setPromotionCustomerId(cus.getPromotionsId());
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(productObj.getInt("quantity"));
        detailBill.setProductId(pro.getId());
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(pro.getPromotionsId());
           
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + ((jsonObj.getFloat("extraPromotions") / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
    
    // test checkout have promotion customer, not have promotion product and extra promotion
    @Test
    public void testCheckout__promoCustomer() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));     
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 100000000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
               
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 0 , null, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount(jsonObj.getFloat("extraPromotions"));
        bill.setPromotionCustomerId(cus.getPromotionsId());
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(productObj.getInt("quantity"));
        detailBill.setProductId(pro.getId());
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(null);
           
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + ((jsonObj.getFloat("extraPromotions") / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
    
    // test checkout have promotion customer, not have promotion product and have extra promotion
    @Test
    public void testCheckout__promoCustomer_extraPromotion() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));     
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 100000000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
               
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 0 , null, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount(jsonObj.getFloat("extraPromotions"));
        bill.setPromotionCustomerId(cus.getPromotionsId());
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(productObj.getInt("quantity"));
        detailBill.setProductId(pro.getId());
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(null); 
           
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + ((jsonObj.getFloat("extraPromotions") / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
    
    // test checkout have promotion customer, have promotion product but not have extra promotion
    @Test
    public void testCheckout__promoCustomer_promotionProduct() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        promotionCustomer = new PromotionsCustomer();
        promotionCustomer.setId(1);
        promotionCustomer.setName("khach hang moi");        
        promotionCustomer.setStartDate((java.util.Date)formatter.parse("2021/06/20"));
        promotionCustomer.setEndDate((java.util.Date)formatter.parse("2021/07/10"));
        promotionCustomer.setPercentDiscount(Float.parseFloat("10"));     
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 100000000, promotionCustomer);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
           
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);  
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount(jsonObj.getFloat("extraPromotions"));
        bill.setPromotionCustomerId(cus.getPromotionsId());
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(productObj.getInt("quantity"));
        detailBill.setProductId(pro.getId());
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(null);
             
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)(cus.getPromotion().getPercentDiscount() / 100 * totalOfProducts + ((jsonObj.getFloat("extraPromotions") / 100 ) * totalOfProducts));
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
    
    // test checkout have promotion product, have extra promotion ,not have promotion customer, 
    @Test
    public void testCheckout_promotionProduct() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, -1, 100000000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
           
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10);  
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount(jsonObj.getFloat("extraPromotions"));
        bill.setPromotionCustomerId(null);
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(productObj.getInt("quantity"));
        detailBill.setProductId(pro.getId());
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(pro.getPromotionsId());
             
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)((float)10 /100 * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
    
    // test checkout not have promotion product, have extra promotion ,not have customer product, 
    @Test
    public void testCheckout_extraPromotion() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 10);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, -1, 100000000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
           
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 10 , -1, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount(jsonObj.getFloat("extraPromotions"));
        bill.setPromotionCustomerId(null);
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(productObj.getInt("quantity"));
        detailBill.setProductId(pro.getId());
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(null);
             
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = (long)((float) 10 / 100  * totalOfProducts);
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
    
    // test checkout have promotion product, not have extra promotion ,not have promotion customer, 
    @Test
    public void testCheckout_promotionProduct_notExtraAndCustomerPromotion() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, -1, 100000000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
           
        promotionsProduct = new PromotionsProduct();
        promotionsProduct.setId(1);
        promotionsProduct.setName("san pham moi");        
        promotionsProduct.setStartDate(Date.valueOf("2021-06-20"));
        promotionsProduct.setEndDate(Date.valueOf("2021-07-10"));
        promotionsProduct.setPercentDiscount((float) 10); 
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 10 , 1, promotionsProduct);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount((float) 0);
        bill.setPromotionCustomerId(null);
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(1);
        detailBill.setProductId(1);
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(pro.getPromotionsId());
             
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = (long) (pro.getHistoricalCost() * ((float)promotionsProduct.getPercentDiscount()/100));
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = 0;
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
    
    // test checkout not have promotion product, not have extra promotion ,not have promotion customer, 
    @Test
    public void testCheckout_notHaveAllPromotion() throws ParseException
    {
        JSONObject jsonObj = new JSONObject();
        JSONObject productObj = new JSONObject();
        productObj.put("id", "1");
        productObj.put("quantity", "1");
        JSONArray productArr = new JSONArray();
        productArr.put(productObj);
        jsonObj.put("products", productArr);
        jsonObj.put("customerId", 1);
        jsonObj.put("extraPromotions", 0);
        String json = jsonObj.toString();  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, -1, 100000000, null);          
        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);
           
        Product pro = new Product(1, "SP01" ,"SanPham01", 1000, 100000, (float) 10 , null, null);
        Mockito.when(productDAO.findProductById(1)).thenReturn(pro); 
        
        bill = new Bill();
        bill.setCustomerId(cus.getId());
        bill.setDiscount((float) 0);
        bill.setPromotionCustomerId(null);
        int billId = 1;
        Mockito.when(billDAO.createBill(bill)).thenReturn(billId); 
        
        detailBill = new DetailBill();
        detailBill.setBillId(billId);
        detailBill.setQuantity(1);
        detailBill.setProductId(1);
        detailBill.setLastPrice(Math.round(pro.getProductSalePrice()));
        detailBill.setPromotionProductId(pro.getPromotionsId());
             
        long originPrice = (long) (pro.getHistoricalCost());
        long productSale = 0;
        long discount = (long) (pro.getHistoricalCost() * (pro.getTradeDiscount()/100));
        long totalOfProducts = originPrice - (productSale + discount);
        long customerSale = 0;
        long totalOfBill = totalOfProducts - customerSale;
        float newAccountBalance = cus.getAccountBalance() - totalOfBill;
        
        Mockito.when(billDAO.updateTotalBill(billId, Math.round(totalOfBill))).thenReturn(1);
        
        Mockito.when(customerDAO.updateAccountBalance(cus.getId(), newAccountBalance)).thenReturn(1);
        
        Map<String, String> lstResult = checkAPI.postCheckout(json); 
       
        assertEquals("true", lstResult.get("success"));
        assertEquals(Integer.toString(Math.round(totalOfBill)), lstResult.get("total"));
    } 
}
