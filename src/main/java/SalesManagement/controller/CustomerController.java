/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author NghiaDX
 */
@Controller
public class CustomerController {

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView customerPage() {
        ModelAndView mav = new ModelAndView("Customer");
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
