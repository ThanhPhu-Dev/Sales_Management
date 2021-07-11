package SalesManagement.controller;

import SalesManagement.dao.BillDAO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	CustomerDAO customdfgerDAO;

	@Autowired
	private BillDAO billDao;

//	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
//	   public String homePage() {
//		return "Home";
//	   }

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(){
		ModelAndView mav = new ModelAndView("Home");
		return mav;
	}
}
