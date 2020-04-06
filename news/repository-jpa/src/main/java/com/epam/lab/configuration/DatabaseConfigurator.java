package com.epam.lab.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.epam.lab")
@PropertySource("classpath:database.properties")
@EnableTransactionManagement()
public class DatabaseConfigurator {

    private static final String PACKAGES_TO_SCAN = "com.epam.lab.model";
    private static final String PROPERTY_NAME_USER = "jdbc.user";
    private static final String PROPERTY_NAME_PASSWORD = "jdbc.password";
    private static final String PROPERTY_NAME_URL = "jdbc.url";
    private static final String PROPERTY_NAME_DRIVER = "jdbc.driverClassName";
    private static final String PROPERTY_NAME_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_SHOW_SQL = "hibernate.show_sql";

    @Autowired
    private Environment environment;

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_NAME_DIALECT, environment.getProperty(PROPERTY_NAME_DIALECT));
        properties.setProperty(PROPERTY_NAME_SHOW_SQL, environment.getProperty(PROPERTY_NAME_SHOW_SQL));
        return properties;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(environment.getProperty(PROPERTY_NAME_USER));
        dataSource.setPassword(environment.getProperty(PROPERTY_NAME_PASSWORD));
        dataSource.setJdbcUrl(environment.getProperty(PROPERTY_NAME_URL));
        dataSource.setDriverClassName(environment.getProperty(PROPERTY_NAME_DRIVER));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(hibernateProperties());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
