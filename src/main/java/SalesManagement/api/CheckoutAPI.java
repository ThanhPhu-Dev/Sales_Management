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
        // T??nh gi?? cu???i c??ng
        totalOfProducts = originPrice - (productSale + discount);

        //T??nh ??u ????i c???a kh??ch h??ng
        customerSale = (long) ((customer.getPromotion() != null ? customer.getPromotion().getPercentDiscount() / 100 : 0) * totalOfProducts
                + (extraPromotions > 0 ? extraPromotions / 100 : 0) * totalOfProducts);

        long totalOfBill = totalOfProducts - customerSale;

        //Ki???m tra ??u ????i c?? v?????t qu?? gi?? tr??? ho?? ????n
        if (totalOfBill < 0) {
            map.put("success", "false");
            map.put("message", "??u ????i ???? v?????t qu?? gi?? tr??? s???n ph???m");
        }

        //Ki???m tra c?? s???n ph???m trong gi??? h??ng v?? kh??ch h??ng kh??ng ph???i l?? c??ng n??? v?????t m???c
        if (productsJSON.length() > 0 && !customer.isDebtor()) {
            isDeptor = customer.isDebtor(Math.round(totalOfBill));
        }

        map.put("origin", Long.toString(originPrice));
        map.put("productSale", Long.toString(productSale));
        map.put("customerSale", Long.toString(customerSale));
        map.put("discount", Long.toString(discount));
        map.put("total", Long.toString(totalOfBill));
        map.put("isDeptor", Boolean.toString(isDeptor));

        // Anotation @ResponseBody tr??? v??? d??? li???u JSON.
        return map;
    }

    // Ph????ng th???c get producst nh???n params:
    // offset: b??? qua ${offset} records,
    // limit: tr??? v??? ${limit} records
    // excludeIds: b??? qua c??c id n??y
    // searchId: c?? id like ${searchId}
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

        // Anotation @ResponseBody tr??? v??? d??? li???u JSON.
        return map;
    }

    //Ph????ng th???c ?????m s??? s???n ph???m c??n l???i
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

        // Anotation @ResponseBody tr??? v??? d??? li???u JSON.
        return map;
    }

    // Ph????ng th???c mua h??ng
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
                map.put("message", "Vui l??ng th??m s???n ph???m v??o gi??? h??ng");

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
                if (product.getPromotions() != null) {
                    detailBill.setPromotionProductId(product.getPromotionsId());
                } else {
                    detailBill.setPromotionProductId(null);
                }

                detailBills.add(detailBill);
            }
            long totalOfProducts = originPrice - (productSale + discount);

            // T??nh gi?? cu???i c??ng
            // Tr??? th??m ??u ????i kh??ch h??ng v?? ??u ????i th??m(n???u c??), ??p d???ng cho to??n ho?? ????n
            //T??nh ??u ????i c???a kh??ch h??ng
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

        // Anotation @ResponseBody tr??? v??? d??? li???u JSON.
        return map;
    }
}
