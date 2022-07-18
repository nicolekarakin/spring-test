package org.nnn4eu.nfische.beanscopetest.web.controller;

import org.nnn4eu.nfische.beanscopetest.service.GreetingService;
import org.nnn4eu.nfische.beanscopetest.service.IGreeting;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@RestController //@NoArgsConstructor
public class GreetingCtrl {
    private final IGreeting service;

    private final ProxyFactoryBean proxiedThreadLocalTargetSource;
    public GreetingCtrl(GreetingService service, ProxyFactoryBean proxiedThreadLocalTargetSource){
        this.service=service;
        this.proxiedThreadLocalTargetSource=proxiedThreadLocalTargetSource;
        System.out.println("custom constructor GreetingCtrl -----------------------");
        System.out.println("ThreadId: "+String.valueOf(Thread.currentThread().getId()));
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(service.getClass().getCanonicalName());
        System.out.println("end of constructor -----------------------");
    }
    @GetMapping("default")
    public String greetDefault(){
        String id="ThreadId: "+String.valueOf(Thread.currentThread().getId());
        System.out.println(id);
        System.out.println(service.getClass().getCanonicalName());
        return service.greetDefault()+" "+id;
    }

    @GetMapping("hereto")
    public String greetHereto(){
        String id="ThreadId: "+String.valueOf(Thread.currentThread().getId());
        System.out.println(id);
        System.out.println(service.getClass().getCanonicalName());
        return service.greetHereto()+" "+id;
    }

    @GetMapping("date")
    public String greetToday(){
        String id="ThreadId: "+String.valueOf(Thread.currentThread().getId());
        System.out.println(id);

        System.out.println("proxyFactoryBean: "+proxiedThreadLocalTargetSource);
        System.out.println("proxyFactoryBean targetSourse: "+proxiedThreadLocalTargetSource.getTargetSource());
        Date n=new Date();
        DateFormat df=(DateFormat)(proxiedThreadLocalTargetSource.getObject());
//        String formatted = n.format(df);
//        System.out.println(formatted);

        String formatted2 = df.format(n);
        System.out.println(formatted2);
        return formatted2+" "+id;
    }
}
