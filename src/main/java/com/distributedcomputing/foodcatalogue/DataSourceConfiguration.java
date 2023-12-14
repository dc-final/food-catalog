package com.distributedcomputing.foodcatalogue;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfiguration {
	
	@Bean
    @Primary
    public DataSource dataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://mysql-db-instance.clpt78iucczw.us-east-2.rds.amazonaws.com:3306/mydb");
	        dataSource.setUsername("admin");
	        dataSource.setPassword("Avengers1");
	        return dataSource;
    }

}
