package com.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.spring.*")
public class Config {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/School");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplateObj = new JdbcTemplate();
        jdbcTemplateObj.setDataSource(dataSource());
        return jdbcTemplateObj;
    }

}
