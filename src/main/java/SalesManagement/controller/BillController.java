package SalesManagement.controller;

import SalesManagement.dao.BillDAO;
import SalesManagement.dao.DetailBillDAO;
import SalesManagement.dto.Bill;
import SalesManagement.dto.DetailBill;
import SalesManagement.dto.ReportBill;
import SalesManagement.dto.objectBill;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BillController {

    @Autowired
    private BillDAO billDAO;

    @Autowired
    private DetailBillDAO detailBillDAO;

    @GetMapping("/bills")
    public String index() {
        return "Bill/bill";
    }

    @GetMapping("/Detailbill")
    public String detailbill(@RequestParam String id, Model model) {
        Map<String,Object> data = datadetailbill(id);
        model.addAttribute("bill", (Bill)data.get("bill"));
        model.addAttribute("date",(String)data.get("date"));
        model.addAttribute("sumbill", (Integer)data.get("sumbill"));
        model.addAttribute("discount",(Integer)data.get("discount"));
        model.addAttribute("promotion",(Integer)data.get("promotion"));
        model.addAttribute("detail", (List<DetailBill>)data.get("detail"));
        return "Bill/DetailBill";
    }

    private Map<String,Object> datadetailbill(String id){
        Map<String,Object> data = new HashMap<>();
        float lastprice = 0;
        int promotion = 0;
        Integer Id = Integer.parseInt(id);
        Bill bill = billDAO.findBillById(Id);
        List<DetailBill> dt = detailBillDAO.findAll(Id);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = df.format(bill.getDateCreate());
        for (DetailBill deta : dt) {
            lastprice += deta.getLastPrice();
        }
        if(bill.getPromotionCustomer() != null) {
            promotion = (int)(lastprice*(bill.getPromotionCustomer().getPercentDiscount()/100));
        }
        data.put("id",id);
        data.put("bill",bill);
        data.put("detail",dt);
        data.put("date",dateString);
        data.put("sumbill",(int)lastprice);
        data.put("discount",(int)(lastprice*(bill.getDiscount()/100)));
        data.put("promotion",promotion);
        return data;
    }
    
 
    
    @GetMapping("report")
    public void report(@RequestParam String id, HttpServletResponse response) {
        JRDataSource dataSource;
        try {
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/ReportBill.jrxml").getInputStream());
        Map<String, Object> parameters = getReportInfoCustomer(id);
    	dataSource=getDataSource(id);
    	
			DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance(); 
			
			//p.put("net.sf.jasperreports.awt.igno‌​re.missing.font","true");
			//.put("net.sf.jasperreports.default.pdf.encoding","utf8");
			JRPropertiesUtil.getInstance(context)
			.setProperty("net.sf.jasperreports.default.pdf.encoding","Cp1250")
			; 
			
			ServletOutputStream servletOutputStream = response.getOutputStream();

			try
			{		
				response.setContentType("application/pdf");
				response.setCharacterEncoding("utf8");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
				JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
				servletOutputStream.flush();
				servletOutputStream.close();

			} catch (Exception e)
			{
				// display stack trace in the browser
				StringWriter stringWriter = new StringWriter();
				PrintWriter printWriter = new PrintWriter(stringWriter);
				e.printStackTrace(printWriter);
				response.setContentType("text/plain");
				response.getOutputStream().print(stringWriter.toString());
				e.printStackTrace();
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception print PDF: " + e.getMessage());
		}
    }
    
    private HashMap<String,Object> getReportInfoCustomer(String id){
    	HashMap<String,Object> rs = new HashMap<String, Object>();
    	Map<String,Object> data = datadetailbill(id);
    	Bill bill = (Bill)data.get("bill");
    	rs.put("id", Integer.parseInt((String)data.get("id")));
    	rs.put("nameCustomer", bill.getCustomer().getName());
    	if(bill.getPromotionCustomer() !=null) {
    		rs.put("namePromotionCustomer", bill.getPromotionCustomer().getName());
    		rs.put("percentDiscountCustomer", bill.getPromotionCustomer().getPercentDiscount());
    	}
    	rs.put("numberCard", bill.getCustomer().getNumberCard());
    	
    	rs.put("discount", bill.getDiscount());
    	rs.put("accountBalance", bill.getCustomer().getAccountBalance());
    	rs.put("createAt", (String)data.get("date"));
    	rs.put("sumBill", (Integer)data.get("sumbill"));
    	rs.put("promotionCustomerPrice", (Integer)data.get("promotion"));
    	rs.put("discountPrice", (Integer)data.get("discount"));
    	rs.put("total", bill.getTotal());
    	return rs;
    	
    }
    
    
    
    private JRDataSource getDataSource(String id)
    {
        int stt= 1;
        Map<String,Object> data = datadetailbill(id);
    	List<ReportBill> item=new ArrayList<ReportBill>();
        Bill bill = (Bill) data.get("bill");
        for (DetailBill dt : (List<DetailBill>)data.get("detail")){
            ReportBill rp = new ReportBill();
            rp.setStt(stt++);
            rp.setNameProduct(dt.getProduct().getName());
            rp.setHistoricalCost(dt.getProduct().getHistoricalCost());
            rp.setLastPrice(dt.getLastPrice());
            rp.setQuantity(dt.getQuantity());
            rp.setSpecification(dt.getProduct().getSpecification());
            rp.setTradeDiscount(dt.getProduct().getTradeDiscount());
            rp.setNamePromotionProduct(dt.getPromotion().getName());
            rp.setPercentDiscountProduct(dt.getProduct().getTradeDiscount());
            item.add(rp);
        }
    	return new JRBeanCollectionDataSource(item);
    }
}
