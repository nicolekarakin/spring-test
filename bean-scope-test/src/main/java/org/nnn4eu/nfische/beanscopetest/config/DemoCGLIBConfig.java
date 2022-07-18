package org.nnn4eu.nfische.beanscopetest.config;

import lombok.Getter;
import org.nnn4eu.nfische.beanscopetest.web.model.Greeting;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

// @Configuration require CGLIB subclassing of each such configuration class at runtime.
// As a consequence, @Configuration classes and their factory methods must not be marked
// as final or private in this mode.
@Configuration
public class DemoCGLIBConfig {
    public DemoCGLIBConfig(){
        System.out.println("default DemoCGLIB constructor! "+ counter +" -----------------------");
        System.out.println(this.getClass().getCanonicalName());
    }

    @Getter private int counter=0;

    @Bean
    public String something(){
        this.counter++;
        System.out.println("something() invoked, this.counter: "+this.counter);
        return String.valueOf(this.counter);
    }

    @Bean
    //alternative to @Lookup, ensures that if injected in singlton, it will be proxy >>
    // >> argo created new on each request
    //creating prototype bean instances at runtime, we even could use constructor with arguments
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Greeting greetingDefault(){
        return new Greeting().greetMe();
    }

    @Bean
    public Greeting greetingHereto(){
        return new Greeting().greetMe("Hereto");
    }

//==============================

    @Bean("yyyyMMdd")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DateFormat simpleDateFormatYyyyMmDd() {
        return new SimpleDateFormat("yy/MM/dd");
    }

// https://stackoverflow.com/questions/51140534/proxyfactorybean-uses-jdkdynamicaopproxy-instead-of-cglib
// Using thread scope will create an instance for each thread and discard when the thread dies.
// Using a thread local will reuse te instance. (The thread scope will also if the thread never dies!).
// However using a thread local directly here is probably easier and more readable.
    @Bean(destroyMethod = "destroy")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON,proxyMode = ScopedProxyMode.DEFAULT)
    public ThreadLocalTargetSource threadLocalYyyyMmDd() {
        final ThreadLocalTargetSource threadLocalTargetSource = new ThreadLocalTargetSource();
        threadLocalTargetSource.setTargetBeanName("yyyyMMdd");

        System.out.println("threadLocalTargetSource targetName: "+threadLocalTargetSource.getTargetBeanName());
        System.out.println("threadLocalTargetSource class: "+threadLocalTargetSource.getClass());
        return threadLocalTargetSource;
    }

    @Bean
    @Primary
    public ProxyFactoryBean proxiedThreadLocalTargetSource(final ThreadLocalTargetSource threadLocalTargetSource) {

        final ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetSource(threadLocalTargetSource);
        proxyFactoryBean.setProxyTargetClass(true);

        System.out.println("proxyFactoryBean: "+proxyFactoryBean);
        System.out.println("proxyFactoryBean targetSourse: "+proxyFactoryBean.getTargetSource());
        return proxyFactoryBean;
    }
}
