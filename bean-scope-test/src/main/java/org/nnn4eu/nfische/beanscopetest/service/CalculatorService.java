package org.nnn4eu.nfische.beanscopetest.service;

import org.nnn4eu.nfische.beanscopetest.web.model.Greeting;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CalculatorService implements InitializingBean {
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

    @PostConstruct
    public void setUp(){
        System.out.println(this.getClass().getCanonicalName());
        System.out.println("end ofPostConstruct setup -----------------------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet -----------------------");
    }

//    @Autowired
//    private  AccountRepo accountRepo;
//    @Transactional
//    public Long addAccount(Account account){
//        return accountRepo.save(account).getId();
//    }

    @Bean
    public Greeting getGreeting(){
        return new Greeting().greetMe();
    }
    @Bean
    public Greeting greetingAgainAgainMonday(Greeting getGreeting){
        return getGreeting.greetMe("InCalculatorServiceGreeting");
    }

    @Bean
    public Greeting greetingThursday(){
        return new Greeting().greetMe("Thursday");
    }
    @Bean
    public Greeting greetingThursdaySunday(Greeting greetingThursday){
        return greetingThursday.greetMe("ThursdaySunday");
    }
}
