
import SalesManagement.api.CustomerApi;
import SalesManagement.controller.CustomerController;
import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
    
    @InjectMocks
    CustomerApi customerAPI;

    public CustomerControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //test get all item in list customer
    @Test
    public void getListCustomer_NonEmpty_UnitTest() {
        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(lst.size(), actual.size());
    }

    ////get list with MIN = 0
    @Test
    public void getListCustomer_MIN_0_UnitTest() {
        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();

        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }

    //get list with MAX = 50
    
    //get list with 5 item
    
    //name empty    
    /*
    @Test
    public void addCustomer_NameEmpty_AddFailed() {
        HashMap<String, String> temp = new HashMap<>();
        temp.put("name", "hung");
        temp.put("promotion", "1234567");
        temp.put("phone", "09872617918");
        temp.put("card", "0192826181");
        temp.put("identity", "98172811");
        
        Customer cus = new Customer();
        cus.setName("");
        cus.setNumberCard("123456");
        cus.setPhone("09872617928");
        cus.setIdentityCard("12345678");
        cus.setPromotionsId(1);
        
        Mockito.when(customerDAO.findCustomerByNumCard("0192826181")).thenReturn(0);  
        Mockito.when(customerDAO.findCustomerByIdentity("0192826181")).thenReturn(0);  
        Mockito.when(customerDAO.AddCustomer(cus)).thenReturn(1);         

        Map<String, String> arrError = new HashMap<String, String>();

        Mockito.when(customerAPI.addCustomerPost(temp)).thenReturn(arrError);

        Assert.assertEquals(0, arrError.size());
    }*/

    //identity empty
    @Test
    public void addCustomer_IdentityEmpty_AddFailed() {
        Customer cus = new Customer();

        cus.setName("Hung");
        cus.setNumberCard("123456");
        cus.setPhone("09872617928");
        cus.setIdentityCard("");
        cus.setPromotionsId(1);

        customerDAO.AddCustomer(cus);

        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }

    //numberPhone empty
    @Test
    public void addCustomer_NumberPhoneEmpty_AddFailed() {
        Customer cus = new Customer();

        cus.setName("Hung");
        cus.setNumberCard("123456");
        cus.setPhone("");
        cus.setIdentityCard("1234567");
        cus.setPromotionsId(1);

        customerDAO.AddCustomer(cus);

        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }

    //numberCard empty
    @Test
    public void addCustomer_NumberCardEmpty_AddFailed() {
        Customer cus = new Customer();

        cus.setName("Hung");
        cus.setNumberCard("");
        cus.setPhone("09872617928");
        cus.setIdentityCard("1234567");
        cus.setPromotionsId(1);

        customerDAO.AddCustomer(cus);

        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }

    //name include special characters
    @Test
    public void addCustomer_NameSpecialCharacter_AddFailed() {
        Customer cus = new Customer();

        cus.setName("Hung@#");
        cus.setNumberCard("123456");
        cus.setPhone("09872617928");
        cus.setIdentityCard("1234567");
        cus.setPromotionsId(1);

        customerDAO.AddCustomer(cus);

        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }
    
    //name include number
    @Test
    public void addCustomer_NameIncludeNumber_AddFailed() {
        Customer cus = new Customer();

        cus.setName("11111");
        cus.setNumberCard("123456");
        cus.setPhone("09872617928");
        cus.setIdentityCard("1234567");
        cus.setPromotionsId(1);

        customerDAO.AddCustomer(cus);

        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }

    //Name length < 5
    @Test
    public void addCustomer_NameLengthMin_AddFailed() {
        Customer cus = new Customer();

        cus.setName("1111");
        cus.setNumberCard("123456");
        cus.setPhone("09872617928");
        cus.setIdentityCard("1234567");
        cus.setPromotionsId(1);

        customerDAO.AddCustomer(cus);

        List<Customer> lst = new ArrayList<Customer>();

        Mockito.when(customerDAO.findAllCustomerBy("")).thenReturn(lst);

        ModelAndView model = cusController.customerPage();
        HashMap<String, Object> map = (HashMap<String, Object>) model.getModel();
        List<Customer> actual = (List<Customer>) map.get("customers");
        Assert.assertEquals(0, actual.size());
    }

}
