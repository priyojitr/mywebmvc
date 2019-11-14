package com.dummy.mymvc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dummy.mymvc.entities.MyTable;

@Configuration
@EnableAsync
@EnableTransactionManagement
public class DatabaseConfigurer {

	private static final String HOST = "ubuntu-vm";
	private static final String PORT = ":3306/";
	private static final String DBNAME = "mydb";
	private static final String USER = "remoteuser";
	private static final String UPD = "remoteuserpwd";

	private static final String JDBCURL = new StringBuilder()
			.append("jdbc:mysql://")
			.append(HOST)
			.append(PORT)
			.append(DBNAME)
			.append("?verifyServerCertificate=false&useSSL=false&requireSSL=false")
			.toString();
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl(JDBCURL);
		ds.setUsername(USER);
		ds.setPassword(UPD);
		return ds;
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
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
