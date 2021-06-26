/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.controller;

import SalesManagement.dao.ProductDAO;
import SalesManagement.dao.PromotionsProductDAO;
import SalesManagement.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 *
 * @author Minh Huy
 */
@Controller
public class ProductController {

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView productPage() {
        ModelAndView mav = new ModelAndView("Product/Product");
        return mav;
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public ModelAndView addProductPage() {
        ModelAndView mav = new ModelAndView("Product/AddProduct");
        return mav;
    }

    @RequestMapping(value = "/product/update/*", method = RequestMethod.GET)
    public ModelAndView updateProductPage() {
        ModelAndView mav = new ModelAndView("Product/UpdateProduct");
        return mav;
    }
}
