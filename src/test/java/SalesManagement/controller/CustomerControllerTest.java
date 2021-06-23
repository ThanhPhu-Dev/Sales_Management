
package SalesManagement.controller;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;


@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    
    @Mock
    CustomerDAO customerDAO;

    @InjectMocks
    CustomerController cusController;
    
    
    public CustomerControllerTest() {
    }

    
    //get all item in list
    @Test
    public void getListCustomer_NonEmpty_UnitTest() {
        List<Customer> cusList = new ArrayList<Customer>();
        
        cusList.add(new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(cusList);

        ModelAndView model = cusController.customerPage();

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(cusList.get(0).getName(), actual.get(0).getName());
    }

    //get list empty
    @Test
    public void getListCustomer_Empty_UnitTest() {
        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);
        
        int expect_size = 0;

        ModelAndView model = cusController.customerPage();

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(expect_size, actual.size());
    }
    
    //get list Min lenght
    @Test
    public void getListCustomer_MinLength_UnitTest() {
        List<Customer> cusList = new ArrayList<Customer>();
        
        cusList.add(new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(cusList);

        ModelAndView model = cusController.customerPage();

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(1, actual.size());
    }
    
    //get list Max lenght
    @Test
    public void getListCustomer_MaxLength_UnitTest() {
        List<Customer> cusList = new ArrayList<Customer>();
        
        cusList.add(new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(2, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(3, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(4, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(5, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(6, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(7, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(8, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(9, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        cusList.add(new Customer(10, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(cusList);

        ModelAndView model = cusController.customerPage();

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(10, actual.size());
    }

    
    //get view list customer
    @Test
    public void getListCustomer_CheckView_UnitTest() {
        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);
        
        String expect_view = "Customer/Customer";

        ModelAndView model = cusController.customerPage();

        String actual_view =  model.getViewName();
        
        Assert.assertEquals(expect_view, actual_view);
    }
    
    //search customer by phone invalid
    @Test
    public void searchCustomer_phoneInvalid_SearchFail() {
        List<Customer> cusList = new ArrayList<Customer>();
        
        Mockito.when(customerDAO.findAllCustomerBy("minhhuy")).thenReturn(cusList);

        ModelAndView model = cusController.customerSearch("minhhuy");
        
        int expect_result = 0;

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }
    
    //search customer by IndentityCard invalid
    @Test
    public void searchCustomer_IdentityCardInvalid_SearchFail() {
        List<Customer> cusList = new ArrayList<Customer>();
        
        Mockito.when(customerDAO.findAllCustomerBy("282897998")).thenReturn(cusList);

        ModelAndView model = cusController.customerSearch("282897998");
        
        int expect_result = 0;

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }
    
    //search customer by phone valid and return customer
    @Test
    public void searchCustomer_PhoneValid_SearchSuccess() {
        List<Customer> cusList = new ArrayList<Customer>();
        
        cusList.add(new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        Mockito.when(customerDAO.findAllCustomerBy("0978726817")).thenReturn(cusList);

        ModelAndView model = cusController.customerSearch("0978726817");
        
        String expect_result = "phihung";
        
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(expect_result, actual.get(0).getName());
    }
    
    //search customer by indetity card valid and return customer
    @Test
    public void searchCustomer_IdentityCardValid_SearchSuccess() {
        List<Customer> cusList = new ArrayList<Customer>();
        
        cusList.add(new Customer(1, "phihung", "87261829182", "0978726817", "827166281728", 100000, 1, 1000));
        
        Mockito.when(customerDAO.findAllCustomerBy("827166281728")).thenReturn(cusList);

         ModelAndView model = cusController.customerSearch("827166281728");
        
        String expect_result = "827166281728";

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(expect_result, actual.get(0).getIdentityCard());
    }
    
}
