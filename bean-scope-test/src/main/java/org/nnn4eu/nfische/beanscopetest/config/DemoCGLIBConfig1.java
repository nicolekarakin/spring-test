package org.nnn4eu.nfische.beanscopetest.config;

import lombok.Getter;
import org.nnn4eu.nfische.beanscopetest.components.MyComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //(proxyBeanMethods = false)//<<< will execute something() not just one time regardless of how many time we call it
@ComponentScan( basePackages = "org.nnn4eu.nfische.beanscopetest.components")//basePackageClasses = {MyComponent.class},
//@DependsOn(value = "myComponent")
public class DemoCGLIBConfig1 {
    public DemoCGLIBConfig1(){
        System.out.println("default DemoCGLIB1 constructor! "+ counter +" -----------------------");
        System.out.println(this.getClass().getCanonicalName());
    }

    @Getter private int counter=0;
    @Autowired
    MyComponent myComponent;

    @Bean
    public String something2(){
        this.counter++;
        System.out.println("something() invoked, this.counter: "+this.counter);
        myComponent.doSome("something from config");
        return String.valueOf(this.counter);
    }


}
