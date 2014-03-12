package com.faiez;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "com.faiez")
@ImportResource("classpath:setup-database.xml")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(H2).build();
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(createSessionFactoryBean().getObject());
		return transactionManager;
	}

	@Bean
	public LocalSessionFactoryBean createSessionFactoryBean(){
		LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
		Properties properties=new Properties();
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.connection.driver_class","org.h2.Driver");
		properties.setProperty("hibernate.default_schema","PUBLIC");
		properties.setProperty("hibernate.connection.username","sa");
		properties.setProperty("hibernate.connection.password","");
		properties.setProperty("hibernate.hbm2ddl.auto","create");
		properties.setProperty("hibernate.show_sql","true");
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setAnnotatedClasses(new Class<?>[]{Book.class});
		localSessionFactoryBean.setDataSource(dataSource());
		return localSessionFactoryBean;
	}
}
