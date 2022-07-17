package org.nnn4eu.nfische.beanscopetest.web.controller;

import org.nnn4eu.nfische.beanscopetest.service.GreetingService;
import org.nnn4eu.nfische.beanscopetest.service.IGreeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@NoArgsConstructor
public class GreetingCtrl {
    private final IGreeting service;
    public GreetingCtrl(GreetingService service){
        this.service=service;
        System.out.println("custom constructor GreetingCtrl -----------------------");
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(service.getClass().getCanonicalName());
        System.out.println("end of constructor -----------------------");
    }
    @GetMapping("default")
    public String greetDefault(){
        System.out.println(service.getClass().getCanonicalName());
        return service.greetDefault();
    }

    @GetMapping("hereto")
    public String greetHereto(){
        System.out.println(service.getClass().getCanonicalName());
        return service.greetHereto();
    }


}
