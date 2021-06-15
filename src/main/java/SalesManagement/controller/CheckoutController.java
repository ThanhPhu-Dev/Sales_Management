/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.controller;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dao.ProductDAO;
import SalesManagement.dto.Customer;
import SalesManagement.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    CustomerDAO _customerDAO;

    @Autowired
    ProductDAO _productDAO;

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String customerPage(@RequestParam("id") int id, Model model) {
        Customer cus = _customerDAO.findCustomerById(id);
        List<Product> products = _productDAO.getProducts();
        model.addAttribute("customer", cus);
        model.addAttribute("products", products);
        return "Checkout";
    }
}
