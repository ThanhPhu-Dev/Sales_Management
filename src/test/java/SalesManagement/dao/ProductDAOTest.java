
package SalesManagement.dao;

import SalesManagement.dto.Product;
import SalesManagement.dto.PromotionsProduct;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOTest {
     
    @InjectMocks
    ProductDAO productDAO;
    
    @Mock
    JdbcTemplate template;
    
    @Autowired
    PromotionsProduct promoProductDTO;
         
    public ProductDAOTest() {
       
    }
    
    /******************************************** TEST INSERT PRODUCT ****************************************************/
    
    //invalid all input
    @Test
    public void addProduct_invalidAllInput_addFailed() {
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(null);
        product.setPromotions(null);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }   
    
    //SKU empty
    @Test
    public void addProduct_emptySKU_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    } 
    
    //Name empty
    @Test
    public void addProduct_emptyName_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Specification = 0
    @Test
    public void addProduct_specificationZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //HistoricalCost = 0
    @Test
    public void addProduct_HistoricalCostZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //TradeDiscount = 0
    @Test
    public void addProduct_TradeDiscountZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //promotionId not exist in list promotionProduct
    @Test
    public void addProduct_invalid_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(0);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU empty, Name empty
    @Test
    public void addProduct_emptySKUAndName_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    } 
    
    //SKU empty, Specification = 0 
    @Test
    public void addProduct_emptySKUAndSpecificationZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU empty, HistoricalCost = 0 
    @Test
    public void addProduct_emptySKUAndHistoricalCostZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU empty, TradeDiscount = 0 
    @Test
    public void addProduct_emptySKUAndTradeDiscountZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Name empty, TradeDiscount = 0 
    @Test
    public void addProduct_emptyNameAndSpecificationZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Name empty, HistoricalCost = 0 
    @Test
    public void addProduct_emptyNameAndHistoricalCostZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Name empty, TradeDiscount = 0 
    @Test
    public void addProduct_emptyNameAndTradeDiscountZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Specification = 0, HistoricalCost = 0 
    @Test
    public void addProduct_specificationAndHistoricalZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Specification = 0, TradeDiscount = 0 
    @Test
    public void addProduct_specificationAndTradeDiscountZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //HistoricalCost = 0, TradeDiscount = 0 
    @Test
    public void addProduct_historicalAndTradeDiscountZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="", Name="", Specification = 0
    @Test
    public void addProduct_emptySKUAndNameSpecificationZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="",Name="", HistoricalCost = 0
    @Test
    public void addProduct_emptySKUAndNameHistoricalCostZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="",Name="", TradeDiscount = 0
    @Test
    public void addProduct_emptySKUAndNameTradeDiscountZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Name="",Specification=0, HistoricalCost=0
    @Test
    public void addProduct_emptyNameSpeccifiAndHistoricalZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Name="", HistoricalCost=0, TradeDiscount=0
    @Test
    public void addProduct_emptyNameHistoriAndTradeZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //Specification=0, HistoricalCost=0, TradeDiscount=0
    @Test
    public void addProduct_SpecifiHistoriTradeZero_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=10000,TradeDiscount=10,PromotionId=1
    @Test
    public void addProduct_invalidSpecification_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=100, HistoricalCost=-10000,TradeDiscount=10,PromotionId=1
    @Test
    public void addProduct_invalidHistoricalCost_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=100, HistoricalCost=10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void addProduct_invalidTradeDiscount_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=-10000,TradeDiscount=10,PromotionId=1
    @Test
    public void addProduct_invalidSpecificationAndHistorical_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void addProduct_invalidSpecificationAndTradeDiscount_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
     //SKU="SP01",Name="sanpham01",Specification=100, HistoricalCost=-10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void addProduct_invalidHistoricalAndTradeDiscount_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=-10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void addProduct_invalidSpecifiHistoriTradeDiscount_addFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    
    // valid all input
    @Test
    public void addProduct_validAllInput_addSuccess() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 1;
        
        String sql = "INSERT INTO products (SKU, Name, Specification, HistoricalCost, TradeDiscount, PromotionId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId())).thenReturn(expResult);
        
        Integer rs = productDAO.add(product);
        
        assertEquals(expResult, rs);
    }
    /*********************************************** END TEST INSERT PRODUCT *********************************************/
    
    /********************************************** TEST UPDATE PRODUCT ****************************************************/
    
    //invalid all input
    @Test
    public void updateProduct_invalidAllInput_updateFailed() {
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(null);
        product.setPromotions(null);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }   
    
    //SKU empty
    @Test
    public void updateProduct_emptySKU_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    } 
    
    //Name empty
    @Test
    public void updateProduct_emptyName_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Specification = 0
    @Test
    public void updateProduct_specificationZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //HistoricalCost = 0
    @Test
    public void updateProduct_HistoricalCostZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //TradeDiscount = 0
    @Test
    public void updateProduct_TradeDiscountZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //promotionId not exist in list promotionProduct
    @Test
    public void updateProduct_invalid_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(0);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU empty, Name empty
    @Test
    public void updateProduct_emptySKUAndName_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    } 
    
    //SKU empty, Specification = 0 
    @Test
    public void updateProduct_emptySKUAndSpecificationZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU empty, HistoricalCost = 0 
    @Test
    public void updateProduct_emptySKUAndHistoricalCostZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU empty, TradeDiscount = 0 
    @Test
    public void updateProduct_emptySKUAndTradeDiscountZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Name empty, TradeDiscount = 0 
    @Test
    public void updateProduct_emptyNameAndSpecificationZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Name empty, HistoricalCost = 0 
    @Test
    public void updateProduct_emptyNameAndHistoricalCostZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Name empty, TradeDiscount = 0 
    @Test
    public void updateProduct_emptyNameAndTradeDiscountZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Specification = 0, HistoricalCost = 0 
    @Test
    public void updateProduct_specificationAndHistoricalZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Specification = 0, TradeDiscount = 0 
    @Test
    public void updateProduct_specificationAndTradeDiscountZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //HistoricalCost = 0, TradeDiscount = 0 
    @Test
    public void updateProduct_historicalAndTradeDiscountZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("SP01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="", Name="", Specification = 0
    @Test
    public void updateProduct_emptySKUAndNameSpecificationZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="",Name="", HistoricalCost = 0
    @Test
    public void updateProduct_emptySKUAndNameHistoricalCostZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="",Name="", TradeDiscount = 0
    @Test
    public void updateProduct_emptySKUAndNameTradeDiscountZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(100000);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
         String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Name="",Specification=0, HistoricalCost=0
    @Test
    public void updateProduct_emptyNameSpeccifiAndHistoricalZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Name="", HistoricalCost=0, TradeDiscount=0
    @Test
    public void updateProduct_emptyNameHistoriAndTradeZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("");
        product.setSpecification(100);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //Specification=0, HistoricalCost=0, TradeDiscount=0
    @Test
    public void updateProduct_SpecifiHistoriTradeZero_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(0);
        product.setHistoricalCost(0);
        product.setTradeDiscount(((float) 0));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=10000,TradeDiscount=10,PromotionId=1
    @Test
    public void updateProduct_invalidSpecification_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=100, HistoricalCost=-10000,TradeDiscount=10,PromotionId=1
    @Test
    public void updateProduct_invalidHistoricalCost_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=100, HistoricalCost=10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void updateProduct_invalidTradeDiscount_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=-10000,TradeDiscount=10,PromotionId=1
    @Test
    public void updateProduct_invalidSpecificationAndHistorical_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void updateProduct_invalidSpecificationAndTradeDiscount_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
     //SKU="SP01",Name="sanpham01",Specification=100, HistoricalCost=-10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void updateProduct_invalidHistoricalAndTradeDiscount_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    //SKU="SP01",Name="sanpham01",Specification=-100, HistoricalCost=-10000,TradeDiscount=-10,PromotionId=1
    @Test
    public void updateProduct_invalidSpecifiHistoriTradeDiscount_updateFailed() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(-100);
        product.setHistoricalCost(-10000);
        product.setTradeDiscount(((float) -10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 0;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }
    
    // valid all input
    @Test
    public void updateProduct_validAllInput_updateSuccess() {
        
        //initial promotionsProduct
        promoProductDTO = new PromotionsProduct();
        promoProductDTO.setId(1);
        promoProductDTO.setName("San pham moi");
        promoProductDTO.setStartDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setEndDate(Date.valueOf("2021-06-20"));
        promoProductDTO.setPercentDiscount((float) 10);
        Product product = new Product();
        product.setId(1);
        product.setSKU("Sp01");
        product.setName("Sanpham01");
        product.setSpecification(100);
        product.setHistoricalCost(10000);
        product.setTradeDiscount(((float) 10));
        product.setPromotionsId(1);
        product.setPromotions(promoProductDTO);
        
        Integer expResult = 1;
        
        String sql = "UPDATE products SET SKU = ?, Name = ?, Specification = ?, HistoricalCost = ?, TradeDiscount = ?, PromotionId = ? " +
                "WHERE Id = ?";
        
        Mockito.when(template.update(sql, product.getSKU(), product.getName(), product.getSpecification(),
                product.getHistoricalCost(), product.getTradeDiscount(), product.getPromotionsId(), product.getId())).thenReturn(expResult);
        
        Integer rs = productDAO.update(product, product.getId());
        
        assertEquals(expResult, rs);
    }  
    /********************************************** TEST UPDATE PRODUCT ****************************************************/
}
