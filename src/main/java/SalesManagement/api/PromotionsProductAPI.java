package SalesManagement.api;

import SalesManagement.dao.PromotionsProductDAO;
import SalesManagement.dto.Product;
import SalesManagement.dto.PromotionsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/promotionsProduct")
public class PromotionsProductAPI {

    @Autowired
    PromotionsProductDAO promotionsProductDAO;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        int totalPromotionsProducts = promotionsProductDAO.countAll();
        List<PromotionsProduct> promotionsProductList = promotionsProductDAO.findAll(0, totalPromotionsProducts);

        if(promotionsProductList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(promotionsProductList, HttpStatus.OK);
    }

    @GetMapping("/table")
    public ResponseEntity<Object> findAllForTable(@RequestParam(value = "draw", required = true) int draw,
                                                  @RequestParam(value = "start", required = true) int offset,
                                                  @RequestParam(value = "length", required = true) int limit,
                                                  @RequestParam(value = "search[value]", required = false) String searchValue) {
        Map<String, Object> map = new HashMap<>();

        List<PromotionsProduct> promotionsProductList;
        int totalPromotionsProducts = promotionsProductDAO.countAll();
        int totalPromotionsProductsFiltered;

        if (searchValue.isEmpty()) {
            totalPromotionsProductsFiltered = totalPromotionsProducts;
            promotionsProductList = promotionsProductDAO.findAll(offset, limit);
        } else {
            totalPromotionsProductsFiltered = promotionsProductDAO.countByName(searchValue);
            promotionsProductList = promotionsProductDAO.findByName(searchValue);
        }

        map.put("draw", draw);
        map.put("recordsTotal", totalPromotionsProducts);
        map.put("recordsFiltered", totalPromotionsProductsFiltered);
        map.put("data", promotionsProductList);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
