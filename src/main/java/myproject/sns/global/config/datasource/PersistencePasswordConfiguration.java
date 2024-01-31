package myproject.sns.global.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(
        basePackages = "myproject.sns.domain.auth.dao",
        entityManagerFactoryRef = "passwordEntityManager",
        transactionManagerRef = "passwordTransactionManager"
)
public class PersistencePasswordConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean passwordEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(passwordDataSource());
        em.setPackagesToScan(new String[] { "myproject.sns.domain.auth.entity" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource passwordDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager passwordTransactionManager() {

        JpaTransactionManager transactionManager= new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(passwordEntityManager().getObject());
        return transactionManager;
    }
}
