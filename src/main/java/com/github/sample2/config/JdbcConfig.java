/*
Yedam Lee
This is the class where we connect our program to DataBase.
Therefore, this program can communicate with the database we are using and keep it updated.
We use singleton design pattern to keep it simple.
 */
package com.github.sample2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.DriverManager;

//collecting jdbc(connection to DB) beans
@Configuration
public class JdbcConfig {

    @Bean
    //DataSource is what we need in jdbc template to connect with database
    // !!must make it into a bean!!
    public DataSource dataSource() {

        //DriverManager is part of the jdbc template
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("20031221");

        //determining what driver class we will use (ex. mysql)
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //getting where our sql database is located
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/sample2?useUnicode=true&characterEncoding=UTF-8");

        return dataSource;
    }

    //creating new jdbctemplate every time it is called
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    //making it transactional, so both sides can be applied to
    @Bean
    public PlatformTransactionManager transactionManager(){

        //transactional has been applied to dataSource
        return new DataSourceTransactionManager(dataSource());
    }

}
