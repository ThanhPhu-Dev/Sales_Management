package SalesManagement.api;

import SalesManagement.dao.ProductDAO;
import SalesManagement.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// JSON
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CheckoutAPI {
    @Autowired
    private ProductDAO _productDAO;

    @PostMapping("/api/cart")
    public Map<String, Float> calcCartInfo(@RequestBody String body) {
        Float OriginPrice = (float)0;
        Float ProductSale = (float)0;
        Float Discount = (float)0;
        Float Total = (float)0;

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

        // Anotation RequestBody trả về dữ liệu JSON.
        return map;
    }
}
