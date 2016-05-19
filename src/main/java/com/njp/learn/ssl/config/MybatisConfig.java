package com.njp.learn.ssl.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
public class MybatisConfig {
	private static final Logger logger = LoggerFactory.getLogger("image");
	
	@Value("${jdbc.driverClassName:com.mysql.jdbc.Driver}")
	private String driverClassName;

	@Value("${jdbc.url:jdbc}")
	private String url;

	@Value("${jdbc.username:root}")
	private String username;

	@Value("${jdbc.password:root}")
	private String password;
	
/*	@Value("${jdbc.maxActive:20}")
	private String maxActive;
	
	@Value("${jdbc.initialSize:1}")
	private String initialSize;
	
	@Value("${jdbc.minIdle:3}")
	private String minIdle;
	
	@Value("${jdbc.removeAbandoned:true}")
	private String removeAbandoned;
	 
	@Value("${jdbc.removeAbandonedTimeout:180}")
	private String removeAbandonedTimeout;
	
	@Value("${jdbc.maxWait:60000}")
	private String maxWait;*/

	@Value("${jdbc.maxActive:20}")
	private int maxActive;

	@Value("${jdbc.initialSize:1}")
	private int initialSize;

	@Value("${jdbc.minIdle:3}")
	private int minIdle;

	@Value("${jdbc.removeAbandoned:true}")
	private boolean removeAbandoned;

	@Value("${jdbc.removeAbandonedTimeout:180}")
	private String removeAbandonedTimeout;

	@Value("${jdbc.maxWait:60000}")
	private int maxWait;


    //You need this
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }

	@Bean
	public DataSource dataSourcePool() {
		BasicDataSource dataSource = new BasicDataSource();
		logger.info(driverClassName);
		logger.info(url);
		logger.info(username);
		logger.info(password);

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		dataSource.setMaxActive(maxActive);
		dataSource.setInitialSize(initialSize);
		dataSource.setMinIdle(minIdle);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(180);
		dataSource.setConnectionProperties("clientEncoding=UTF-8");
		dataSource.setMaxWait(maxWait);
		dataSource.setValidationQuery("SELECT 1");


		return dataSource;
	}
/*
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		logger.info(driverClassName);
		logger.info(url);
		logger.info(username);
		logger.info(password);

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	@Bean
	public Properties connectionProperties() {
		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("maxActive", maxActive);
		connectionProperties.setProperty("initialSize", initialSize);
		connectionProperties.setProperty("minIdle", minIdle);
		connectionProperties.setProperty("removeAbandonedTimeout", removeAbandonedTimeout);
		connectionProperties.setProperty("removeAbandoned", removeAbandoned);
		connectionProperties.setProperty("maxWait", maxWait);

		return connectionProperties;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}*/

}
