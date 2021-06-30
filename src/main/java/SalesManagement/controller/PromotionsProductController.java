package SalesManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PromotionsProductController {
    @RequestMapping(value = "/promotionsProduct", method = RequestMethod.GET)
    public ModelAndView promotionsPage() {
        ModelAndView mav = new ModelAndView("Product/Promotions");
        return mav;
    }
}
