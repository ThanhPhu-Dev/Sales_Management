package SalesManagement.config;

import SalesManagement.dao.CustomerDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.ResourceBundle;


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
        CustomerDAO empDao = new CustomerDAO();
        empDao.setTemplate(jdbcTemplate());
        return empDao;
    }

}
