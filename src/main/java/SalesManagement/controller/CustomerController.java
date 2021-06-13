/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.controller;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Customer;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    CustomerDAO customdfgerDAO;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView customerPage() {
        List<Customer> cusList = customdfgerDAO.findAllCustomerBy();
        
        ModelAndView mav = new ModelAndView("Customer", "customers", cusList);
        return mav;
    }
    
    @RequestMapping(value = "/customer/add", method = RequestMethod.GET)
    public ModelAndView addCustomerPage() {
        ModelAndView mav = new ModelAndView("AddCustomer");
        return mav;
    }

    @RequestMapping(value = "/customer/update/{Id}", method = RequestMethod.GET)
    public ModelAndView updateCustomerPage(@PathVariable int Id) {
        ModelAndView mav = new ModelAndView("UpdateCustomer", "id", Id);
        return mav;
    }
}
