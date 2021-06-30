
package SalesManagement.controller;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dao.ProductDAO;
import SalesManagement.dto.Customer;
import java.util.HashMap;
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
public class CheckoutControllerTest {
    
     @Mock
    CustomerDAO customerDAO;    
     
     @Mock
    ProductDAO productDAO;

    @InjectMocks
    CheckoutController checkController;
    
    @Autowired
    Model model;
    
    public CheckoutControllerTest() {
    }

    //test get customer by id
    @Test
    public void getCustomerById_NonEmpty() {     
        Customer cus = new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000, null);

        Mockito.when(customerDAO.findCustomerById(1)).thenReturn(cus);          
        
        ModelAndView model = checkController.customerPage(1);
        
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        Customer cusExpected = (Customer) map.get("customer");
        assertEquals(cus.getName(), cusExpected.getName());
    }
    
}
