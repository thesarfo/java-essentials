package dev.thesarfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement // makes spring have transaction management enabled, but you have to specify the methods that should be wrapped in a transaction
@ComponentScan(basePackages = {"dev.thesarfo.repository", "dev.thesarfo.service"})
public class ProjectConfig {

    /* minimal implementation of a datasource bean in spring */
    @Bean
    public DataSource datasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/tilly");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    /* this template makes us use jdbc while hiding the abstractions */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    /* to enable transactions */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /* NOTE: The rollback of transactions work only on Runtime exceptions. If you use a checked exception, then you will have to throw the exception everywhere the method is called, and the Transaction rollback will not work on it */

    /* Since by default, the transaction will rollback for any runtime exception, we can explicitly tell it not to rollback for the runtime exception like below */
    @Transactional(noRollbackFor = RuntimeException.class)
    public void test(){
        System.out.println("No rollback");
        throw new RuntimeException("Will not be thrown");
    }

    /* Likewise, we can tell spring to rollback for Checked exceptions, like below */
    @Transactional(noRollbackFor = Exception.class)
    public void anotherTest() throws Exception {
        System.out.println("No rollback for the checked exception");
        throw new Exception("Will not be thrown");
    }
}
