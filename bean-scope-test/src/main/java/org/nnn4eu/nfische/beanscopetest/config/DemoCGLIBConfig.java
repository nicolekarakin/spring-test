package org.nnn4eu.nfische.beanscopetest.config;

import lombok.Getter;
import org.nnn4eu.nfische.beanscopetest.web.model.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Greeting greetingDefault(){
        return new Greeting().greetMe();
    }

    @Bean
    public Greeting greetingHereto(){
        return new Greeting().greetMe("Hereto");
    }
}
