package SalesManagement.api;

import SalesManagement.dao.ProductDAO;
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

    @PostMapping("/api/cart")
    public Map<String, Float> calcCartInfo(@RequestBody String body) {
        Float OriginPrice = (float) 0;
        Float ProductSale = (float) 0;
        Float Discount = (float) 0;
        Float Total = (float) 0;

        // parse to json object
        JSONObject bodyJSON = new JSONObject(body);
        JSONArray productsJSON = bodyJSON.getJSONArray("products");

        for (int i = 0; i < productsJSON.length(); i++) {
            int id = Integer.parseInt(productsJSON.getJSONObject(i).getString("id"));
            int quantity = Integer.parseInt(productsJSON.getJSONObject(i).getString("quantity"));

            Product product = _productDAO.findProductById(id);
            OriginPrice += product.getHistoricalCost() * quantity;
            ProductSale += product.promotionsToPrice() * quantity;
            Discount += product.discountPrice() * quantity;

        }
        // Tính giá cuối cùng
        Total = OriginPrice - (ProductSale + Discount);

        HashMap<String, Float> map = new HashMap<>();
        map.put("origin", OriginPrice);
        map.put("sale", ProductSale);
        map.put("discount", Discount);
        map.put("total", Total);

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
}
