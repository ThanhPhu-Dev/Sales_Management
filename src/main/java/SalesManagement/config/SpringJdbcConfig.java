package SalesManagement.config;

import SalesManagement.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.ResourceBundle;
import  java.util.Properties;

@Configuration
public class SpringJdbcConfig {

    ResourceBundle infodb = ResourceBundle.getBundle("ConnectDB");
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"+infodb.getString("IP")+":"+infodb.getString("Port")+"/"+infodb.getString("Database"));
		dataSource.setUsername(infodb.getString("Username"));
		dataSource.setPassword(infodb.getString("Password"));
        Properties connectionProps = new Properties();
        connectionProps.put("useUnicode", "true");
        connectionProps.put("characterEncoding", "UTF-8");
		dataSource.setConnectionProperties(connectionProps);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public CustomerDAO customerDAO(){
        CustomerDAO Dao = new CustomerDAO();
        Dao.setTemplate(jdbcTemplate());
        return Dao;
    }

    @Bean
    public ProductDAO productDAO(){
        ProductDAO Dao = new ProductDAO();
        Dao.setTemplate(jdbcTemplate());
        return Dao;
    }

    @Bean
    public BillDAO billDAO(){
        BillDAO Dao = new BillDAO();
        Dao.setTemplate(jdbcTemplate());
        return Dao;
    }
    @Bean
    public DetailBillDAO detailBillDAO(){
        DetailBillDAO Dao = new DetailBillDAO();
        Dao.setTemplate(jdbcTemplate());
        return Dao;
    }

    @Bean
    public PromotionsCustomerDAO promotionsCustomerDAO(){
        PromotionsCustomerDAO Dao = new PromotionsCustomerDAO();
        Dao.setTemplate(jdbcTemplate());
        return Dao;
    }

    @Bean
    public PromotionsProductDAO promotionsProductDAO(){
        PromotionsProductDAO Dao = new PromotionsProductDAO();
        Dao.setTemplate(jdbcTemplate());
        return Dao;
    }

    @Bean
    public ReceiptDAO receiptDAO(){
        ReceiptDAO Dao = new ReceiptDAO();
        Dao.setTemplate(jdbcTemplate());
        return Dao;
    }
}
