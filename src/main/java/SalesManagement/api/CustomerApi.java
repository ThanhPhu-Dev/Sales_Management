package SalesManagement.api;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApi {

    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
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
