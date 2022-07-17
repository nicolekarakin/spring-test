package org.nnn4eu.nfische.beanscopetest.service;

import org.nnn4eu.nfische.beanscopetest.web.model.Greeting;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GreetingService implements IMarker, IGreeting{ // implements IMarker

    private final Greeting greetingDefault;
    private final Greeting greetingHereto;

    public GreetingService( Greeting greetingDefault,Greeting greetingHereto){
        this.greetingDefault = greetingDefault;
        this.greetingHereto = greetingHereto;
        System.out.println("custom constructor GreetingService -----------------------");
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(greetingDefault.getClass().getCanonicalName());
        System.out.println(greetingHereto.getClass().getCanonicalName());
        System.out.println("end of constructor -----------------------");
    }
    @PostConstruct
    public void setUp(){
        System.out.println(this.getClass().getCanonicalName());
        System.out.println("end ofPostConstruct setup -----------------------");
    }

    @Override
    public String greetDefault(){
        return greetingDefault.toString();
    }
    @Override
    public String greetHereto(){
        return greetingHereto.toString();
    }

    @Override
    public void doNothing() {
        System.out.println("doing nothing");
    }
}
