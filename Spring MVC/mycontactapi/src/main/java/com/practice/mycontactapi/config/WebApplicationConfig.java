package com.practice.mycontactapi.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = "com.allstate.mycontactapi")
@EnableWebMvc
@EnableSwagger2
@PropertySource("classpath:dbconfig.properties")
@EnableTransactionManagement
public class WebApplicationConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket apiDocumentation() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.paths(PathSelectors.regex("/api/v1/.*"))
				.apis(RequestHandlerSelectors.basePackage("com.allstate.mycontactapi.controller")).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().description("MY Contact API description").title("MY Contact API").build();

	}
	
	//Configuring Connection details to the H2 database
	//This is used when you connect to an actual DB server instead of Embedded
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	
	
	//Spring's Integration with JPA's EntityManagerFactory
	//Set the Datasource and scan the entity classes
	@Bean
	public LocalContainerEntityManagerFactoryBean emf() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.allstate.mycontactapi.model");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(jpaProperties());
		return emf;
		
	}

	//Hibernate properties. How hibernate behaves
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show-sql", "true");
		return properties;
	}
	
	
	
	//Transaction manager bean for starting and closing transactions
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(emf().getObject());
		return manager;
	}

}
