package com.candidate.interview.hiringevent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.candidate.interview.hiringevent")
@PropertySource("classpath:app-config.properties")
@EnableAutoConfiguration(exclude = {FlywayAutoConfiguration.class})
public class AppConfig {

    @Configuration
    @Profile("default")
    @PropertySource("file:/usr/local/content/config/hiring-event/db-local.properties")
    static class TestProfile
    {

    }

    @Configuration
    @Profile("dev")
    @PropertySource("file:/usr/local/content/config/hiring-event/db-dev.properties")
    static class DefaultProfile
    { }


    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUserName;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.driver-class}")
    private String driverClassName;

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(dbUrl);
        ds.setUsername(dbUserName);
        ds.setPassword(dbPassword);
        ds.setDriverClassName(driverClassName);
        return ds;
    }
}
