package com.echenique.linkelog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.repositories"})
@EnableTransactionManagement
public class ProjectConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
   /*
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }*/
}
