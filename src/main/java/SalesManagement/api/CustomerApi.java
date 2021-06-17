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
        String phone = formData.get("phone");
        String identity = formData.get("identity");
        Map<String, String> arrError = new HashMap<String, String>();

        Customer cus = new Customer();
        cus.setName(name);
        cus.setPhone(phone);
        cus.setIdentityCard(identity);
        cus.setNumberCard(numcard);
        cus.setAccountBalance(0);
        cus.setPromotionsId(Integer.parseInt(promotion));

        try {
            //Kiem tra số tài khoản có chữ hoặc đã tòn tại thì báo lỗi
            int checkNumCard = customerDAO.findCustomerByNumCard(cus.getNumberCard());
            int checkIdentity = customerDAO.findCustomerByIdentity(cus.getIdentityCard());

            //check numberCard
            boolean checkIsNumCard = cus.getNumberCard().matches("^[0-9]*$");
            if (!checkIsNumCard) {
                arrError.put("cardError", "Số tài khoản phải là số!");
            } else if (checkNumCard > 0) {
                arrError.put("cardError", "Số tài khoản đã tồn tại!");
            }

            //check phone
            boolean checkIsNumPhone = cus.getPhone().matches("^[0-9]*$");
            if (!checkIsNumPhone) {
                arrError.put("phoneError", "Số điện thoại phải là số!");
            }

            //check identity
            boolean checkIsNumIdentity = cus.getIdentityCard().matches("^[0-9]*$");
            if (!checkIsNumIdentity) {
                arrError.put("identityError", "Số cmnd phải là số!");
            } else if (checkIdentity > 0) {
                arrError.put("identityError", "Số cmnd đã tồn tại!");
            }

            //nếu ko có lỗi thì thêm
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
        String phone = formData.get("phone");
        String identity = formData.get("identity");
        Map<String, String> arrError = new HashMap<String, String>();

        //lấy ra KH này trong db để so sánh thông tin 
        Customer cusAtDB = customerDAO.findCustomerById(Integer.parseInt(id));

        Customer cus = new Customer();
        cus.setId(Integer.parseInt(id));
        cus.setName(name);
        cus.setPhone(phone);
        cus.setIdentityCard(identity);
        cus.setNumberCard(numcard);
        cus.setPromotionsId(Integer.parseInt(promotion));

        try {
            //check numberCard
            if (!cusAtDB.getNumberCard().equals(cus.getNumberCard())) {
                int checkNumCard = customerDAO.findCustomerByNumCard(cus.getNumberCard());
                boolean checkIsNum = cus.getNumberCard().matches("^[0-9]*$");
                if (!checkIsNum) {
                    arrError.put("cardError", "Số tài khoản Phải là số!");
                } else if (checkNumCard > 0) {
                    arrError.put("cardError", "Số tài khoản đã tồn tại!");
                }
            }

            //check phone
            boolean checkIsNumPhone = cus.getPhone().matches("^[0-9]*$");
            if (!checkIsNumPhone) {
                arrError.put("phoneError", "Số điện thoại phải là số!");
            }

            //check identity
            if (!cusAtDB.getIdentityCard().equals(cus.getIdentityCard())) {
                int checkIdentity = customerDAO.findCustomerByIdentity(cus.getIdentityCard());
                boolean checkIsNumIdentity = cus.getIdentityCard().matches("^[0-9]*$");
                if (!checkIsNumIdentity) {
                    arrError.put("identityError", "Số cmnd phải là số!");
                } else if (checkIdentity > 0) {
                    arrError.put("identityError", "Số cmnd đã tồn tại!");
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
