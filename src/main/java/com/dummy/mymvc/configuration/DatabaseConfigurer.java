package com.dummy.mymvc.configuration;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dummy.mymvc.entities.MyTable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAsync
@EnableTransactionManagement
public class DatabaseConfigurer {

	private static final String HOST = "db.host";
	private static final String PORT = "db.port";
	private static final String DBNAME = "db.dbname";
	private static final String USER = "db.user";
	private static final String UPD = "db.pwd";
	
	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

	@Autowired
	private Environment env;

	private String jdbcUrl;

	@PostConstruct
	public void setup() {
		log.info("setting jdbc url");
		this.jdbcUrl = new StringBuilder()
				.append("jdbc:mysql://")
				.append(this.env.getProperty(HOST, "localhost"))
				.append(":")
				.append(this.env.getProperty(PORT, "3306"))
				.append("/")
				.append(this.env.getProperty(DBNAME, "mydb"))
				.append("?verifyServerCertificate=false&useSSL=false&requireSSL=false").toString();
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER_CLASS);
		ds.setUrl(this.jdbcUrl);
		ds.setUsername(this.env.getProperty(USER));
		ds.setPassword(this.env.getProperty(UPD));
		return ds;
	}

	@Bean
	public Properties hibernateProperties() {
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.default_schema", "mydb");
		return props;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(hibernateProperties);
		factoryBean.setAnnotatedClasses(MyTable.class);
		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}
