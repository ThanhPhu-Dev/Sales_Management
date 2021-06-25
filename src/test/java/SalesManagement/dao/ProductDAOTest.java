
package SalesManagement.dao;

import SalesManagement.dto.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import jdk.internal.module.ModuleLoaderMap.Mapper;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOTest {
    
    @Mock
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Mock
    JdbcTemplate template;
    
    @InjectMocks
    ProductDAO productDAO;
     
    @InjectMocks
    PromotionsProductDAO proDAO;   
        
    public ProductDAOTest() {
       
    }
    
    @Test
    public void testFindProductById() {
    }

    @Test
    public void testGetProducts() {
    }

    @Test
    public void testGetProductsPagination() {
    }

    @Test
    public void testGetRemainProductsCount() {
    }

    /*
    @Test
    public void testGetProductsPaginationWithSearch() {
        Product pro = new Product();
        pro.setName("Hungphan");
        pro.setSpecification(1000);
        pro.setHistoricalCost(1000);
        pro.setTradeDiscount(Float.parseFloat("10"));
        pro.setPromotionsId(1);
        
        List<Product> lstProduct = new ArrayList<Product>();
        lstProduct.add(pro);
        
        List<Integer> excludeIds = new ArrayList<Integer>(); 
               
        //Mockito.when(template.getDataSource()).thenReturn();
        
        
        Mockito.doAnswer(invocationOnMock ->{
            ResultSet resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getInt("Id")).thenReturn(1);
            Mockito.when(resultSet.getString("Name")).thenReturn("HungPhan");
            Mockito.when(resultSet.getInt(1000)).thenReturn(1000);
            Mockito.when(resultSet.getInt(1000)).thenReturn(1);  

            ResultSetExtractor<RowMapper<Product>> resultSetExtractor =
                    invocationOnMock.getArgument(4);
            return resultSetExtractor.extractData(resultSet);

        }).when(namedParameterJdbcTemplate).query(
                Mockito.anyString(),
                Mockito.any(MapSqlParameterSource.class),
                Mockito.any(ResultSetExtractor.class)
        );
              
        List<Product> lstExpected = productDAO.getProductsPaginationWithSearch(0, 0, excludeIds, "1");
        
        assertEquals(0, lstExpected.size());
    }
    */

    @Test
    public void testGetTemplate() {
    }

    @Test
    public void testGetPromotionsProductDAO() {
    }

    @Test
    public void testSetTemplate() {
    }

    @Test
    public void testSetPromotionsProductDAO() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testCanEqual() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testToString() {
    }
    
}
