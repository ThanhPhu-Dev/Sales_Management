package SalesManagement.api;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dao.ProductDAO;
import SalesManagement.dto.Product;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ProductAPITest {
    
    @Mock
    ProductDAO productDAO;
   
    @InjectMocks
    ProductAPI productAPI;
    
    public ProductAPITest() {
    }

    //get all product in list product    
    @Test
    public void getAllProduct() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Product> products = new ArrayList<>();    
        products.add(new Product(1, "SP01" ,"SanPham01", 1000, 1000, (float) 1 , null, null));            
        Mockito.when(productDAO.findAll(0,1)).thenReturn(products);   
        ResponseEntity<Object> responseEntity = productAPI.findAll(0, 0, 1, "");
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");  
        assertEquals(products.get(0).getName(), lstResult.get(0).getName());
    }
    
    //get all product in list product when list empty
    @Test
    public void getAllProduct_listEmpty() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Product> products = new ArrayList<>();            
        Mockito.when(productDAO.findAll(0,1)).thenReturn(products); 
        ResponseEntity<Object> responseEntity = productAPI.findAll(0, 0, 1, "");
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");
        assertEquals(0, lstResult.size());
    }
    
    //search product by name with name existed in list product
    @Test
    public void searchProductByNameExisted() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Product> products = new ArrayList<>();   
        products.add(new Product(1, "SP01" ,"SanPham01", 1000, 1000, (float) 1 , null, null)); 
        Mockito.when(productDAO.findBySKUOrName("SanPham01")).thenReturn(products);    
        ResponseEntity<Object> responseEntity = productAPI.findAll(0, 0, 1, "SanPham01");
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");
        assertEquals(products.get(0).getName(), lstResult.get(0).getName());
    }
    
    //search product by name with name not existed in list product
    @Test
    public void searchProductByNameNone() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Product> products = new ArrayList<>();   
        Mockito.when(productDAO.findBySKUOrName("SanPham01")).thenReturn(products);    
        ResponseEntity<Object> responseEntity = productAPI.findAll(0, 0, 1, "SanPham01");
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");
        assertEquals(0, lstResult.size());
    }
    
    //search product by name with SKU existed in list product
    @Test
    public void searchProductBySKUExisted() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Product> products = new ArrayList<>();   
        products.add(new Product(1, "SP01" ,"SanPham01", 1000, 1000, (float) 1 , null, null));
        Mockito.when(productDAO.findBySKUOrName("SP01")).thenReturn(products);    
        ResponseEntity<Object> responseEntity = productAPI.findAll(0, 0, 1, "SP01");
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");
        assertEquals(products.get(0).getSKU(), lstResult.get(0).getSKU());
    }
    
     //search product by name with SKU not existed in list product
    @Test
    public void searchProductBySKUNone() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Product> products = new ArrayList<>();   
        Mockito.when(productDAO.findBySKUOrName("SP01")).thenReturn(products);    
        ResponseEntity<Object> responseEntity = productAPI.findAll(0, 0, 1, "SP01");
        Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
        List<Product> lstResult = (List<Product>) map.getClass().getMethod("get", Object.class).invoke(map, "data");
        assertEquals(0, lstResult.size());
    }
}
