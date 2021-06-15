/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.controller;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dao.PromotionsCustomerDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import SalesManagement.dto.DetailBill;
import SalesManagement.dto.PromotionsCustomer;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author NghiaDX
 */
@Controller
public class CustomerController {

    @Autowired
    CustomerDAO customerDAO;
    
    @Autowired
    PromotionsCustomerDAO proCustomerDAO;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView customerPage() {
        List<Customer> cusList = customerDAO.findAllCustomerBy("");
        
        ModelAndView mav = new ModelAndView("Customer/Customer", "customers", cusList);
        return mav;
    }
    @RequestMapping(value = "/customer/add", method = RequestMethod.GET)
    public ModelAndView addCustomerPage() {
        List<PromotionsCustomer> cusList = proCustomerDAO.findAllPromitionsCustomer();
        ModelAndView mav = new ModelAndView("Customer/AddCustomer", "proCus", cusList);
        return mav;
    }
//    @RequestMapping(value = "/customer/update/{Id}", method = RequestMethod.GET)
//    public ModelAndView updateCustomerPage(@PathVariable int Id) {
//        Customer cus = customerDAO.findCustomerById(Id);
//        List<PromotionsCustomer> proList = proCustomerDAO.findAllPromitionsCustomer();
//        ModelAndView mav = new ModelAndView("Customer/UpdateCustomer", "List", {cus: "cus", proList: "proList"});
//        return mav;
//    }    
    @GetMapping("/customer/update")
    public String detailbill(@RequestParam String id, Model model){
        Integer Id = Integer.parseInt(id);
        Customer cus = customerDAO.findCustomerById(Id);
  
        List<PromotionsCustomer> proList = proCustomerDAO.findAllPromitionsCustomer();
        model.addAttribute("cus", cus);
        model.addAttribute("promotions",proList);
        return "Customer/UpdateCustomer";
    }
}
