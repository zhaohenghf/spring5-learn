package com.zhouyu;

import com.zhouyu.service.OrderService;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@ComponentScan("com.zhouyu")
@EnableScheduling
@PropertySource("classpath:spring.properties")
public class AppConfig {

//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource());
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//		transactionManager.setDataSource(dataSource());
//		return transactionManager;
//	}
//
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/tuling?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
//		dataSource.setUsername("root");
//		dataSource.setPassword("Zhouyu123456***");
//		return dataSource;
//	}


	//
//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception {
//		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource());
//		return sessionFactoryBean.getObject();
//	}
//
//	@Bean
//	public SqlSessionTemplate sqlSession() throws Exception {
//		return new SqlSessionTemplate(sqlSessionFactory());
//	}

}

