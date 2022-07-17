package org.nnn4eu.nfische.beanscopetest.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private GreetingService greetingService;
    public int add(int left, int right) {
        String str=greetingService.greetHereto();
        System.out.println("add CalculatorService -----------------------");
        System.out.println("greetHereto from add() >> "+str);
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(greetingService.getClass().getCanonicalName());
        System.out.println("add -----------------------");
        return left + right;
    }

    public CalculatorService(GreetingService greetingService) {
        this.greetingService = greetingService;
        System.out.println("custom constructor CalculatorService -----------------------");
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(greetingService.getClass().getCanonicalName());
        System.out.println("end of constructor -----------------------");
    }
}
