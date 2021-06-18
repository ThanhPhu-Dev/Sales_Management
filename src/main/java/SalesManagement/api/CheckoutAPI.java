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
        HashMap<String, String> map = new HashMap<>();
        map.put("success", "true");
        long originPrice = 0;
        long productSale = 0;
        long discount = 0;
        long customerSale = 0;
        long totalOfProducts = 0;
        boolean isDeptor = false;

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
        customerSale = (long) ((customer.getPromotion() != null ? customer.getPromotion().getPercentDiscount() / 100 : 0) * totalOfProducts
                + (extraPromotions > 0 ? extraPromotions / 100 : 0) * totalOfProducts);

        long totalOfBill = totalOfProducts - customerSale;

        //Kiểm tra ưu đãi có vượt quá giá trị hoá đơn
        if (totalOfBill < 0) {
            map.put("success", "false");
            map.put("message", "Ưu đãi đã vượt quá giá trị sản phẩm");
        }

        //Kiểm tra có sản phẩm trong giỏ hàng và khách hàng không phải là công nợ vượt mức
        if (productsJSON.length() > 0 && !customer.isDebtor()) {
            isDeptor = customer.isDebtor(Math.round(totalOfBill));
        }

        map.put("origin", Long.toString(originPrice));
        map.put("productSale", Long.toString(productSale));
        map.put("customerSale", Long.toString(customerSale));
        map.put("discount", Long.toString(discount));
        map.put("total", Long.toString(totalOfBill));
        map.put("isDeptor", Boolean.toString(isDeptor));

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }

    // Phương thức get producst nhận params:
    // offset: bỏ qua ${offset} records,
    // limit: trả về ${limit} records
    // excludeIds: bỏ qua các id này
    // searchId: có id like ${searchId}
    @GetMapping("/api/products")
    public Map<String, List<Product>> getProducts(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam String excludeIds, @RequestParam String searchId) {
        String[] idsString = excludeIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String s : idsString) {
            if (!s.isEmpty()) {
                ids.add(Integer.parseInt(s));
            }
        }

        List<Product> products = _productDAO.getProductsPaginationWithSearch(offset, limit, ids, searchId);
        HashMap<String, List<Product>> map = new HashMap<>();
        map.put("products", products);

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }

    //Phương thức đếm số sản phẩm còn lại
    @GetMapping("/api/products/remain")
    public Map<String, Integer> getProducts(@RequestParam String excludeIds, @RequestParam String searchId) {
        String[] idsString = excludeIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String s : idsString) {
            if (!s.isEmpty()) {
                ids.add(Integer.parseInt(s));
            }
        }

        Integer remain = _productDAO.getRemainProductsCount(ids, searchId);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("remain", remain);

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }

    // Phương thức mua hàng
    @PostMapping("/api/checkout")
    public Map<String, String> postCheckout(@RequestBody String body) {
        HashMap<String, String> map = new HashMap<>();
        try {
            long originPrice = 0;
            long productSale = 0;
            long discount = 0;

            //List detail bill
            List<DetailBill> detailBills = new ArrayList<>();

            // parse to json object
            JSONObject bodyJSON = new JSONObject(body);
            JSONArray productsJSON = bodyJSON.getJSONArray("products");
            int customerId = bodyJSON.getInt("customerId");
            float extraPromotions = bodyJSON.getFloat("extraPromotions");
            if (productsJSON.length() == 0) {
                map.put("success", "false");
                map.put("message", "Vui lòng thêm sản phẩm vào giỏ hàng");

                return map;
            }
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
            long totalOfProducts = originPrice - (productSale + discount);

            // Tính giá cuối cùng
            // Trừ thêm ưu đãi khách hàng và ưu đãi thêm(nếu có), áp dụng cho toàn hoá đơn
            //Tính ưu đãi của khách hàng
            float customerSale = (customer.getPromotion() != null ? customer.getPromotion().getPercentDiscount() / 100 : 0) * totalOfProducts
                    + (extraPromotions > 0 ? extraPromotions / 100 : 0) * totalOfProducts;

            float totalOfBill = totalOfProducts - customerSale;

            for (DetailBill dt : detailBills) {
                _detailBillDAO.insertDetailBill(dt);
            }

            if (_billDAO.updateTotalBill(billId, Math.round(totalOfBill)) > 0) {
                // update account balance
                float newAccountBalance = customer.getAccountBalance() - totalOfBill;
                int result = _customerDAO.updateAccountBalance(customerId, newAccountBalance);
                if (result == 1) {
                    map.put("success", "true");
                    map.put("total", Integer.toString(Math.round(totalOfBill)));
                    map.put("id", Integer.toString(billId));
                }
            }

        } catch (Exception e) {
            map.put("success", "false");
        }

        // Anotation @ResponseBody trả về dữ liệu JSON.
        return map;
    }
}
