package SalesManagement.api;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
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

        List<Customer> cusList;
        try {
            cusList = customerDAO.findAllCustomerBy(name);
        } catch (Exception e) {
            return null;
        }
        return cusList;
    }

    @PostMapping("api/addcustomer")
    public Map<String, String> addCustomerPost(@RequestBody Map<String, String> formData) {
        String name = formData.get("name");
        String promotion = formData.get("promotion");
        String numcard = formData.get("card");
        Map<String, String> arrError = new HashMap<String, String>();

        Customer cus = new Customer();
        cus.setName(name);
        cus.setNumberCard(numcard);
        cus.setAccountBalance(0);
        cus.setPromotionsId(Integer.parseInt(promotion));

        try {
//            check error card
            int checkNumCard = customerDAO.findCustomerByNumCard(cus.getNumberCard());
            if (checkNumCard > 0) {
                arrError.put("card", "Số tài khoản đã tồn tại!");
            }
            if (arrError.size() < 1) {
                int rowUpdate = customerDAO.AddCustomer(cus);
            }

        } catch (Exception e) {
            return null;
        }
        return arrError;
    }

    @PostMapping("api/updatecustomer")
    public Map<String, String> updateCustomerPost(@RequestBody Map<String, String> formData) {

        String id = formData.get("id");
        String name = formData.get("name");
        String promotion = formData.get("promotion");
        String numcard = formData.get("card");
        Map<String, String> arrError = new HashMap<String, String>();

        //l?y ra KH n�y t? db 
        Customer cusAtDB = customerDAO.findCustomerById(Integer.parseInt(id));

        Customer cus = new Customer();
        cus.setId(Integer.parseInt(id));
        cus.setName(name);
        cus.setNumberCard(numcard);
        cus.setPromotionsId(Integer.parseInt(promotion));

        try {
//            check error card
            if (!cusAtDB.getNumberCard().equals(cus.getNumberCard())) {
                int checkNumCard = customerDAO.findCustomerByNumCard(cus.getNumberCard());
                if (checkNumCard > 0) {
                    arrError.put("card", "Số tài khoản đã tồn tại!");
                }
            }
            if (arrError.size() < 1) {
                int rowUpdate = customerDAO.UpdateCustomer(cus);
            }
        } catch (Exception e) {
            return null;
        }
        return arrError;
    }
}
