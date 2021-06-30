/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.dao;

import SalesManagement.dto.Customer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/context/applicationContext.xml")
@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {
    
    
    @InjectMocks
    CustomerDAO cusDAO;
    
    @Mock
    JdbcTemplate template;
    
    
    /*
    @Autowired
    private CustomerDAO customerDAO;
    */
    
    // name empty
    /*
    @Test
    public void addCustomer_nameEmpty_addFailed() {
        Customer cus = new Customer();
        cus.setName("hung");
        cus.setIdentityCard("4444444222");
        cus.setNumberCard("28172818");
        cus.setPhone("0298172712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int rs = customerDAO.AddCustomer(cus);
        
        assertEquals("hung", cus.getName());
    }
    */
    
    
    // identity empty
    @Test
    public void addCustomer_identityEmpty_addFailed() {
        Customer cus = new Customer();
        cus.setName("Hungphan");
        cus.setIdentityCard("");
        cus.setNumberCard("28172818");
        cus.setPhone("0298172712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }

    
    // numberCard empty
    @Test
    public void addCustomer_numberCardEmpty_addFailed() {
        Customer cus = new Customer();
        cus.setName("Hungphan");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("");
        cus.setPhone("0298172712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    // phone empty
    @Test
    public void addCustomer_phoneEmpty_addFailed() {
        Customer cus = new Customer();
        cus.setName("Hungphan");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght < 5 characters
    @Test
    public void addCustomer_namLess5Character_addFailed() {
        Customer cus = new Customer();
        cus.setName("abc");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(0);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght = 5 characters
    @Test
    public void addCustomer_nameMinLength_addSuccess() {
        Customer cus = new Customer();
        cus.setName("abcde");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    
    //name have lenght > 5 characters and < 50 character
    @Test
    public void addCustomer_nameHaveValidLenght_addSuccess() {
        Customer cus = new Customer();
        cus.setName("phan phi hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght 50 character
    @Test
    public void addCustomer_nameMaxLenght_addSuccess() {
        Customer cus = new Customer();
        cus.setName("abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght > 50 character
    @Test
    public void addCustomer_nameMoreLenght_addFailed() {
        Customer cus = new Customer();
        cus.setName("abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name contains special characters
    @Test
    public void addCustomer_nameSpecialCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Hungphan@123");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity contains letter and special characters
    @Test
    public void addCustomer_identityCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312@abc");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length < 9
    @Test
    public void addCustomer_identityLessLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("9812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length = 9 and have special character
    @Test
    public void addCustomer_identityMinLengthHaveSpecialCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("1234567@a");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length = 12 and have special character
    @Test
    public void addCustomer_identityMaxLengthHaveSpecialCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("1234567@a");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length > 12
    @Test
    public void addCustomer_identityMoreLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("123456789101112");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
     //identity have length = 12
    @Test
    public void addCustomer_identityMaxLength_addSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("123456789101112");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length 10 
    @Test
    public void addCustomer_identityLength10_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("12345678910@#");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(0);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length 11 
    @Test
    public void addCustomer_identityLength11_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("12345678910@#");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    
    //numberCard contains letter and special characters
    @Test
    public void addCustomer_numberCardCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712@abc");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length > 20
    @Test
    public void addCustomer_numberMoreLenght_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789987654321");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length = 20 but have special character
    @Test
    public void addCustomer_numberCardValidLengthButHaveCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789987654321@abc");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length = 20
    @Test
    public void addCustomer_numberCardValidLength_addSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789987654321");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length < 20 and have character
    @Test
    public void addCustomer_numberCardHaveCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789@abc");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length < 20 and don't have character
    @Test
    public void addCustomer_numberCardValid_addSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    
    //phone contains letter and special characters
    @Test
    public void addCustomer_phoneCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821@abc");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
     //phone length > 10
    @Test
    public void addCustomer_phoneMoreLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821812912");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
     //phone length < 10
    @Test
    public void addCustomer_phoneLessLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("098271");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //phone length = 10 but have character
    @Test
    public void addCustomer_phoneValidLengthButHaveCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982710@bac");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    
    //phone length = 10 and don't have character
    @Test
    public void addCustomer_phoneValid_addSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982710@bac");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //phone length = 10 and don't have character
    @Test
    public void addCustomer_ValidInfo_addSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982710928");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("insert into Customers (Name, NumberCard, Phone, IdentityCard, AccountBalance, PromotionsId) values "
                    + "('%s', '%s', '%s', '%s', '%d', '%d')",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getAccountBalance(), cus.getPromotionsId());
        
        Mockito.when(template.update(sql)).thenReturn(1);
        
        int rs = cusDAO.AddCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght < 5 characters
    @Test
    public void updateCustomer_namLess5Character_addFailed() {
        Customer cus = new Customer();
        cus.setName("abc");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght = 5 characters
    @Test
    public void updateCustomer_nameMinLength_UpdateSuccess() {
        Customer cus = new Customer();
        cus.setName("abcde");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    
    //name have lenght > 5 characters and < 50 character
    @Test
    public void updateCustomer_nameHaveValidLenght_UpdateSuccess() {
        Customer cus = new Customer();
        cus.setName("phan phi hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght 50 character
    @Test
    public void updateCustomer_nameMaxLenght_UpdateSuccess() {
        Customer cus = new Customer();
        cus.setName("abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name have lenght > 50 character
    @Test
    public void updateCustomer_nameMoreLenght_addFailed() {
        Customer cus = new Customer();
        cus.setName("abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0987266712");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //name contains special characters
    @Test
    public void updateCustomer_nameSpecialCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Hungphan@123");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity contains letter and special characters
    @Test
    public void updateCustomer_identityCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312@abc");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length < 9
    @Test
    public void updateCustomer_identityLessLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("9812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length = 9
    @Test
    public void updateCustomer_identityMinLength_UpdateSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("123456789");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length > 12
    @Test
    public void updateCustomer_identityMoreLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("123456789101112");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
     //identity have length = 12
    @Test
    public void updateCustomer_identityMaxLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("123456789101112");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length > 9 and < 12 but have special character
    @Test
    public void updateCustomer_identityValidLengthButHaveCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("12345678910@#");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //identity have length > 9 and < 12 and have not special character
    @Test
    public void updateCustomer_indetityValid_UpdateSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("123456789");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //numberCard contains letter and special characters
    @Test
    public void updateCustomer_numberCardCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712@abc");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length > 20
    @Test
    public void updateCustomer_numberMoreLenght_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789987654321");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length = 20 but have special character
    @Test
    public void updateCustomer_numberCardValidLengthButHaveCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789987654321@abc");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length = 20
    @Test
    public void updateCustomer_numberCardValidLength_UpdateSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789987654321");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length < 20 and have character
    @Test
    public void updateCustomer_numberCardHaveCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789@abc");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //NumberCard have length < 20 and don't have character
    @Test
    public void updateCustomer_numberCardValid_UpdateSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("123456789");
        cus.setPhone("0982718821");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    
    //phone contains letter and special characters
    @Test
    public void updateCustomer_phoneCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821@abc");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
     //phone length > 10
    @Test
    public void updateCustomer_phoneMoreLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982718821812912");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
     //phone length < 10
    @Test
    public void updateCustomer_phoneLessLength_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("098271");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //phone length = 10 but have character
    @Test
    public void updateCustomer_phoneValidLengthButHaveCharacter_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982710@bac");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    
    //phone length = 10 and don't have character
    @Test
    public void updateCustomer_phoneValid_addFailed() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982710@bac");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 0;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
    
    //phone length = 10 and don't have character
    @Test
    public void updateCustomer_ValidInfo_updateSuccess() {
        Customer cus = new Customer();
        cus.setName("Phan Phi Hung");
        cus.setIdentityCard("79812312");
        cus.setNumberCard("898172712");
        cus.setPhone("0982710928");
        cus.setPromotionsId(1);
        cus.setAccountBalance(10000);
        
        int expResult = 1;
        
        String sql = String.format("Update Customers set Name = '%s', NumberCard = '%s', Phone = '%s', IdentityCard = '%s',  PromotionsId = '%d' where Id = '%d' ",
                    cus.getName(), cus.getNumberCard(), cus.getPhone(), cus.getIdentityCard(), cus.getPromotionsId(), cus.getId());
        
        Mockito.when(template.update(sql)).thenReturn(expResult);
        
        int rs = cusDAO.UpdateCustomer(cus);
        
        assertEquals(expResult, rs);
    }
  
}
