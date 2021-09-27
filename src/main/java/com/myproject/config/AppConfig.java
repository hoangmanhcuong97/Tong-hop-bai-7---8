package com.myproject.config;

import com.myproject.formater.ProvinceFormatter;
import com.myproject.service.customerService.CustomerSV;
import com.myproject.service.customerService.IServiceCustomer;
import com.myproject.service.provinceService.IServiceProvince;
import com.myproject.service.provinceService.ProvinceSv;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan("com.myproject.controller")
@PropertySource("classpath:filePicture.properties")
@EnableJpaRepositories("com.myproject.repository")
@EnableAspectJAutoProxy
@EnableSpringDataWebSupport
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Value("${file_demo}")
    private String fileCustomer;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5");
        this.applicationContext = applicationContext;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        System.out.println("6");
        SpringResourceTemplateResolver tem = new SpringResourceTemplateResolver();
        tem.setApplicationContext(applicationContext);
        tem.setPrefix("/WEB-INF/views/");
        tem.setSuffix(".html");
        tem.setTemplateMode(TemplateMode.HTML);
        tem.setCharacterEncoding("UTF-8");
        return tem;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        System.out.println("7");
        SpringTemplateEngine temp = new SpringTemplateEngine();
        temp.setTemplateResolver(templateResolver());
        return temp;
    }
    @Bean
    public ThymeleafViewResolver viewResolver() {
        System.out.println("8");
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("UTF-8");
        return resolver;
    }
//JPA
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
        System.out.println("9");
        return entityManagerFactory.createEntityManager();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        System.out.println("10");
        LocalContainerEntityManagerFactoryBean eMFB = new LocalContainerEntityManagerFactoryBean();
        eMFB.setDataSource(dataSource());
        eMFB.setPackagesToScan("com.myproject.model");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        eMFB.setJpaVendorAdapter(jpaVendorAdapter);
        eMFB.setJpaProperties(additionalProperties());
        return eMFB;
    }
    @Bean
    public DataSource dataSource(){
        System.out.println("11");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fomatter_C05");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
    public Properties additionalProperties() {
        System.out.println("12");
        Properties pr = new Properties();
        pr.setProperty("hibernate.hbm2ddl.auto","update");
        pr.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        pr.setProperty("show_sql", "true");
        return pr;
    }

//    Hỗ trợ transaction
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory eMF){
        System.out.println("13");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(eMF);
        return transactionManager;
    }

    //    Cấu hình Upload File
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        System.out.println("14");
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:" + fileCustomer);
    }
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        System.out.println("15");
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800);
        return resolver;
    }

    @Bean
    public IServiceProvince iServiceProvince(){
        return new ProvinceSv();
    }
    @Bean
    public IServiceCustomer iServiceCustomer(){
        return new CustomerSV();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("16");
        registry.addFormatter(new ProvinceFormatter(applicationContext.getBean(IServiceProvince.class)));
    }
}
