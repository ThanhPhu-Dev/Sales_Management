package SalesManagement.api;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerApi {

    @Autowired
    CustomerDAO customerDAO;

    @PostMapping( "api/customer")
    public List<Customer> customerPagePost(@RequestBody String name) {
        String test = name;
        List<Customer> cusList;
        try {
            cusList = customerDAO.findAllCustomerBy();
        } catch (Exception e) {
            return null;
        }
        return cusList;
    }
}
