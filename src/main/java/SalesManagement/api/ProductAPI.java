package SalesManagement.api;

import SalesManagement.dao.ProductDAO;
import SalesManagement.dto.Product;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductAPI {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("")
    public ResponseEntity<Object> findAll(@RequestParam(value = "draw", required = true) int draw,
                                          @RequestParam(value = "start", required = true) int offset,
                                          @RequestParam(value = "length", required = true) int limit,
                                          @RequestParam(value = "search[value]", required = false) String searchValue) {
        Map<String, Object> map = new HashMap<>();

        List<Product> productList;
        int totalProducts = productDAO.countAll();
        int totalProductsFiltered;

        if (searchValue.isEmpty()) {
            totalProductsFiltered = totalProducts;
            productList = productDAO.findAll(offset, limit);
        } else {
            totalProductsFiltered = productDAO.countBySKUOrName(searchValue);
            productList = productDAO.findBySKUOrName(searchValue);
        }

        map.put("draw", draw);
        map.put("recordsTotal", totalProducts);
        map.put("recordsFiltered", totalProductsFiltered);
        map.put("data", productList);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<Object> findById(@PathVariable("product-id") Integer id) {
        Product product = productDAO.findProductById(id);

        if(product == null) {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Object>(product, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody Product product) {
        Map<String, String> errors = validateFormAdd(product);

        if(errors.isEmpty()) {
            productDAO.add(product);
            return new ResponseEntity<Object>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<Object> update(@RequestBody Product product, @PathVariable("product-id") Integer id) {
        Map<String, String> errors = validateFormUpdate(product, id);

        if(errors.isEmpty()) {
            productDAO.update(product, id);
            return new ResponseEntity<Object>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
    }


    public boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public Map<String, String> validateFormAdd(Product newProduct) {
        Map<String, String> errors = new HashMap<>();

        String sku = newProduct.getSKU();
        if(isBlank(sku)){
            errors.put("sku", "SKU kh??ng ???????c b??? tr???ng");
        } else if(productDAO.countBySKU(sku) > 0) {
            errors.put("sku", "SKU ???? t???n t???i");
        }

        validate(newProduct, errors);

        return errors;
    }

    public Map<String, String> validateFormUpdate(Product updatedProduct, Integer id) {
        Map<String, String> errors = new HashMap<>();

        String sku = updatedProduct.getSKU();
        Product existProduct = productDAO.findProductById(id);
        if(isBlank(sku)){
            errors.put("sku", "SKU kh??ng ???????c b??? tr???ng");
        } else if(!existProduct.getSKU().equals(sku)) { // N???u SKU updatedProduct != SKU existProduct th?? ki???m tra t???n t???i
            if(productDAO.countBySKU(sku) > 0) {
                errors.put("sku", "SKU ???? t???n t???i");
            }
        }

        validate(updatedProduct, errors);

        return errors;
    }

    public void validate(Product product, Map<String, String> errors) {
        String name = product.getName();
        Integer specification = product.getSpecification();
        Integer historicalCost = product.getHistoricalCost();
        Float tradeDiscount = product.getTradeDiscount();
        Integer promotionsId = product.getPromotionsId();


        // Ki???m tra t??n
        if(isBlank(name)) {
            errors.put("name", "T??n s???n ph???m kh??ng ???????c b??? tr???ng");
        }

        // Ki???m tra quy c??ch
        if(specification == null) {
            errors.put("specification", "Quy c??ch kh??ng ???????c b??? tr???ng");
        } else if(specification <= 0) {
            errors.put("specification", "Quy c??ch ph???i l???n h??n 0");
        }

        // Ki???m tra gi?? g???c
        if(historicalCost == null) {
            errors.put("historicalCost", "Gi?? g???c kh??ng ???????c b??? tr???ng");
        } else if(historicalCost <= 0) {
            errors.put("historicalCost", "Gi?? g???c ph???i l???n h??n 0");
        }

        // Ki???m tra chi???t kh???u
        if(tradeDiscount == null) {
            errors.put("tradeDiscount", "Chi???t kh???u kh??ng ???????c b??? tr???ng");
        } else if(tradeDiscount <= 0) {
            errors.put("tradeDiscount", "Chi???t kh???u ph???i l???n h??n 0");
        } else if(tradeDiscount > 100) {
            errors.put("tradeDiscount", "Chi???t kh???u ph???i nh??? h??n 100");
        }
    }
}
