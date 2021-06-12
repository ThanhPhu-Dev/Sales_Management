package SalesManagement.controller;

import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	   public String homePage() {
	      return "Home";
	   }
}
