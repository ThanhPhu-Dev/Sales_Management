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
 * @author USER
 */
@Controller
public class CheckoutController {
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView customerPage() {
        ModelAndView mav = new ModelAndView("Checkout");
        return mav;
    }
}
