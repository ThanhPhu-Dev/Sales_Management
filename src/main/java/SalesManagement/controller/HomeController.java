package SalesManagement.controller;

import SalesManagement.dao.CustomerDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	CustomerDAO customerDAO;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	   public String homePage() {
		Customer cus = customerDAO.findCustomerById(1);
		return "Home";
	   }
}
