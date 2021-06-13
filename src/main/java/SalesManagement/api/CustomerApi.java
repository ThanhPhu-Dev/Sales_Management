package SalesManagement.api;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerApi {

    @Autowired
    CustomerDAO customerDAO;

    @PostMapping("api/customer")
    public List<Customer> customerPagePost(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        String id = payload.get("id");

        List<Customer> cusList;
        try {
            cusList = customerDAO.findAllCustomerBy(name);
        } catch (Exception e) {
            return null;
        }
        return cusList;
    }
}
