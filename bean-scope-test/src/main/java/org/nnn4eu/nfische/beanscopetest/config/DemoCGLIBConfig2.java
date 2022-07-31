package org.nnn4eu.nfische.beanscopetest.config;

import lombok.Getter;
import org.nnn4eu.nfische.beanscopetest.service.IMarker;
import org.nnn4eu.nfische.beanscopetest.web.model.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration require CGLIB subclassing of each such configuration class at runtime.
// As a consequence, @Configuration classes and their factory methods must not be marked
// as final or private in this mode.
@Configuration
public class DemoCGLIBConfig2 implements IMarker {
    public DemoCGLIBConfig2(){
        System.out.println("default DemoCGLIB2 constructor! "+ counter +" -----------------------");
        System.out.println(this.getClass().getCanonicalName());
    }

    @Getter private int counter=0;

    @Bean
    public Greeting greetingAgain(){
        return new Greeting().greetMe();
    }
    @Bean
    public Greeting greetingAgainMonday(){
        return greetingAgain().greetMe("AgainMonday");
    }
    @Bean
    public Greeting greetingMonday(){
        return new Greeting().greetMe("Monday");
    }
    @Bean
    public Greeting greetingSunday(){
        return greetingMonday().greetMe("Sunday");
    }

    @Override
    public void doNothing() {
        System.out.println("demo2 nothing");
    }


//==============================

}
