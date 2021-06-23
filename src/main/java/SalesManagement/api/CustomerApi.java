package SalesManagement.api;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import SalesManagement.dto.Product;
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
    CustomerDAO _customerDAO;

//    @PostMapping("api/customer")
//    public List<Customer> customerPagePost(@RequestBody Map<String, String> payload) {
//        String name = payload.get("name");
//
//        List<Customer> cusList;
//        try {
//            cusList = customerDAO.findAllCustomerBy(name);
//        } catch (Exception e) {
//            return null;
//        }
//        return cusList;
//    }
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
            int checkNumCard = _customerDAO.findCustomerByNumCard(cus.getNumberCard());
            int checkIdentity = _customerDAO.findCustomerByIdentity(cus.getIdentityCard());
            int checkPhone = _customerDAO.findCustomerByPhone(cus.getPhone());

            //check name
            boolean checkName = cus.getName().matches("^[^0-9!<>?=+@{}_$%#]+$./");
            if (!checkName) {
                arrError.put("nameError", "Tên không bao gồm số hoặc kí tự đặc biệt!");
            }

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
            } else if (checkPhone > 0) {
                arrError.put("phoneError", "Số điện thoại đã tồn tại!");
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
                int rowUpdate = _customerDAO.AddCustomer(cus);
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
        Customer cusAtDB = _customerDAO.findCustomerById(Integer.parseInt(id));

        Customer cus = new Customer();
        cus.setId(Integer.parseInt(id));
        cus.setName(name);
        cus.setPhone(phone);
        cus.setIdentityCard(identity);
        cus.setNumberCard(numcard);
        cus.setPromotionsId(Integer.parseInt(promotion));

        try {
            //check name
            boolean checkName = cus.getName().matches("^[^0-9!<>?=+@{}_$%#]+$");
            if (!checkName) {
                arrError.put("nameError", "Tên không bao gồm số hoặc kí tự đặc biệt!");
            }

            //check numberCard
            if (!cusAtDB.getNumberCard().equals(cus.getNumberCard())) {
                int checkNumCard = _customerDAO.findCustomerByNumCard(cus.getNumberCard());
                boolean checkIsNum = cus.getNumberCard().matches("^[0-9]*$");
                if (!checkIsNum) {
                    arrError.put("cardError", "Số tài khoản Phải là số!");
                } else if (checkNumCard > 0) {
                    arrError.put("cardError", "Số tài khoản đã tồn tại!");
                }
            }
            
            boolean test = cusAtDB.getPhone().equals(cus.getPhone());
            //check phone
            if (!cusAtDB.getPhone().equals(cus.getPhone())) {
                int checkPhone = _customerDAO.findCustomerByPhone(cus.getPhone());
                boolean checkIsNumPhone = cus.getPhone().matches("^[0-9]*$");
                if (!checkIsNumPhone) {
                    arrError.put("phoneError", "Số điện thoại phải là số!");
                } else if (checkPhone > 0) {
                    arrError.put("phoneError", "Số điện thoại đã tồn tại!");
                }
            }

            //check identity
            if (!cusAtDB.getIdentityCard().equals(cus.getIdentityCard())) {
                int checkIdentity = _customerDAO.findCustomerByIdentity(cus.getIdentityCard());
                boolean checkIsNumIdentity = cus.getIdentityCard().matches("^[0-9]*$");
                if (!checkIsNumIdentity) {
                    arrError.put("identityError", "Số cmnd phải là số!");
                } else if (checkIdentity > 0) {
                    arrError.put("identityError", "Số cmnd đã tồn tại!");
                }
            }

            if (arrError.size() < 1) {
                int rowUpdate = _customerDAO.UpdateCustomer(cus);
            }
        } catch (Exception e) {
            return null;
        }
        return arrError;
    }

    //[GET] lấy danh sách khách hàng giới hạn là 5
    @GetMapping("/api/customers")
    public Map<String, List<Customer>> getProducts(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam String searchValue) {
        HashMap<String, List<Customer>> map = new HashMap<>();

        try {
            List<Customer> customers = _customerDAO.getCustomersPagination(offset, limit, searchValue);

            map.put("customers", customers);

            // Anotation @ResponseBody trả về dữ liệu JSON.
        } catch (Exception e) {
            e.getStackTrace();
        }
        return map;
    }

    //Phương thức đếm số khách hàng còn lại
    @GetMapping("/api/customers/remain")
    public Map<String, Integer> getProducts(@RequestParam String searchValue) {
        Integer remain = _customerDAO.getRemainCustomersCount(searchValue);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("remain", remain);

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }

    //Phương thức lấy thông tin 1 khách hàng
    @GetMapping("/api/customer")
    public Map<String, Customer> getCustomer(int id) {
        Customer customer = _customerDAO.findCustomerById(id);
        HashMap<String, Customer> map = new HashMap<>();
        map.put("customer", customer);
        return map;
    }
}
