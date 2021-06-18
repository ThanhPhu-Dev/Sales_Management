package SalesManagement.api;

import SalesManagement.dao.BillDAO;
import SalesManagement.dao.CustomerDAO;
import SalesManagement.dao.DetailBillDAO;
import SalesManagement.dao.ProductDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import SalesManagement.dto.DetailBill;
import SalesManagement.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// JSON
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController ~ @Controller + @ResponseBody
@RestController
public class CheckoutAPI {
    @Autowired
    private ProductDAO _productDAO;

    @Autowired
    private BillDAO _billDAO;

    @Autowired
    private CustomerDAO _customerDAO;

    @Autowired
    private DetailBillDAO _detailBillDAO;

    @PostMapping("/api/cart")
    public Map<String, String> calcCartInfo(@RequestBody String body) {
        float originPrice = (float) 0;
        float productSale = (float) 0;
        float discount = (float) 0;
        float customerSale = (float) 0;
        float totalOfProducts = (float) 0;

        // parse to json object
        JSONObject bodyJSON = new JSONObject(body);
        JSONArray productsJSON = bodyJSON.getJSONArray("products");
        int customerId = bodyJSON.getInt("customerId");
        float extraPromotions = bodyJSON.getFloat("extraPromotions");

        // find customer
        Customer customer = _customerDAO.findCustomerById(customerId);

        for (int i = 0; i < productsJSON.length(); i++) {
            int id = Integer.parseInt(productsJSON.getJSONObject(i).getString("id"));
            int quantity = Integer.parseInt(productsJSON.getJSONObject(i).getString("quantity"));

            Product product = _productDAO.findProductById(id);
            originPrice += product.getHistoricalCost() * quantity;
            productSale += product.promotionsToPrice() * quantity;
            discount += product.discountPrice() * quantity;

        }
        // Tính giá cuối cùng
        totalOfProducts = originPrice - (productSale + discount);

        //Tính ưu đãi của khách hàng
        customerSale = (customer.getPromotion() != null ? customer.getPromotion().getPercentDiscount() / 100 : 0) * totalOfProducts +
                (extraPromotions > 0 ? extraPromotions / 100 : 0) * totalOfProducts;

        float totalOfBill = totalOfProducts - customerSale;

        HashMap<String, String> map = new HashMap<>();
        map.put("origin", Float.toString(originPrice));
        map.put("productSale", Float.toString(productSale));
        map.put("customerSale", Float.toString(customerSale));
        map.put("discount", Float.toString(discount));
        map.put("total", Float.toString(totalOfBill));
        map.put("isDeptor", Boolean.toString(customer.isDebtor(Math.round(totalOfBill))));

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }

    // Phương thức get producst nhận params:
    // offset: bỏ qua ${offset} records,
    // limit: trả về ${limit} records
    @GetMapping("/api/products")
    public Map<String, List<Product>> getProducts(@RequestParam(defaultValue = "0") int offset,
                                                  @RequestParam(defaultValue = "10") int limit,
                                                  @RequestParam String excludeIds) {
        String[] idsString = excludeIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String s : idsString) {
            if (!s.isEmpty()) {
                ids.add(Integer.parseInt(s));
            }
        }

        List<Product> products = _productDAO.getProductsPagination(offset, limit, ids);
        HashMap<String, List<Product>> map = new HashMap<>();
        map.put("products", products);

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }

    //Phương thức đếm số sản phẩm còn lại
    @GetMapping("/api/products/remain")
    public Map<String, Integer> getProducts(@RequestParam String excludeIds) {
        String[] idsString = excludeIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String s : idsString) {
            if (!s.isEmpty()) {
                ids.add(Integer.parseInt(s));
            }
        }

        Integer remain = _productDAO.getRemainProductsCount(ids);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("remain", remain);

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }

    // Phương thức mua hàng
    @PostMapping("/api/checkout")
    public Map<String, Boolean> postCheckout(@RequestBody String body) {
        HashMap<String, Boolean> map = new HashMap<>();
        try {
            float originPrice = (float) 0;
            float productSale = (float) 0;
            float discount = (float) 0;

            //List detail bill
            List<DetailBill> detailBills = new ArrayList<>();

            // parse to json object
            JSONObject bodyJSON = new JSONObject(body);
            JSONArray productsJSON = bodyJSON.getJSONArray("products");
            int customerId = bodyJSON.getInt("customerId");
            float extraPromotions = bodyJSON.getFloat("extraPromotions");

            // find customer
            Customer customer = _customerDAO.findCustomerById(customerId);

            Bill bill = new Bill();
            bill.setCustomerId(customer.getId());
            bill.setDiscount(extraPromotions);
            bill.setPromotionCustomerId(customer.getPromotionsId() > 0 ? customer.getPromotionsId() : null);

            // Create bill return id
            int billId = _billDAO.createBill(bill);

            for (int i = 0; i < productsJSON.length(); i++) {
                DetailBill detailBill = new DetailBill();
                int id = Integer.parseInt(productsJSON.getJSONObject(i).getString("id"));
                int quantity = Integer.parseInt(productsJSON.getJSONObject(i).getString("quantity"));

                Product product = _productDAO.findProductById(id);
                originPrice += product.getHistoricalCost() * quantity;
                productSale += product.promotionsToPrice() * quantity;
                discount += product.discountPrice() * quantity;

                detailBill.setBillId(billId);
                detailBill.setQuantity(quantity);
                detailBill.setProductId(id);
                detailBill.setLastPrice(Math.round(product.getProductSalePrice()) * quantity);
                detailBill.setPromotionProductId(product.getPromotionsId());

                detailBills.add(detailBill);
            }
            float totalOfProducts = originPrice - (productSale + discount);

            // Tính giá cuối cùng
            // Trừ thêm ưu đãi khách hàng và ưu đãi thêm(nếu có), áp dụng cho toàn hoá đơn
            //Tính ưu đãi của khách hàng
            float customerSale = (customer.getPromotion() != null ? customer.getPromotion().getPercentDiscount() / 100 : 0) * totalOfProducts +
                    (extraPromotions > 0 ? extraPromotions / 100 : 0) * totalOfProducts;

            float totalOfBill = totalOfProducts - customerSale;
            for (DetailBill dt : detailBills) {
                _detailBillDAO.insertDetailBill(dt);
            }
            if (_billDAO.updateTotalBill(billId, Math.round(totalOfBill)) > 0) {
                // update account balance
                float newAccountBalance = customer.getAccountBalance() - totalOfBill;
                int result = _customerDAO.updateAccountBalance(customerId, newAccountBalance);
                if (result == 1) {
                    map.put("success", true);
                }
            }

        } catch (Exception e) {
            map.put("success", false);
        }

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }
}
