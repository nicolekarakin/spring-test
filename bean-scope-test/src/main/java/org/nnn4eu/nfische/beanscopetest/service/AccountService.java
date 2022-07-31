package org.nnn4eu.nfische.beanscopetest.service;

import org.nnn4eu.nfische.beanscopetest.web.model.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements IAccountService{
    private final AccountRepo accountRepo;

//    @PostConstruct
//    public void setUp(){
//        System.out.println(this.getClass().getCanonicalName());
//        System.out.println("end ofPostConstruct setup -----------------------");
//    }

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
        System.out.println("custom constructor AccountService -----------------------");
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(accountRepo.getClass().getCanonicalName());//jdk.proxy4.$Proxy95
        System.out.println("end of constructor -----------------------");
    }
    //@Cacheable(value="accounts", key="#id")
    public Account getAccount(Long id){
        return accountRepo.findById(id).orElseThrow(()->new EntityNotFoundException("not found"));
    }
    public List<Account> getAccounts(){
        return accountRepo.findAll();
    }

    @Transactional
    public Long addAccount(Account account){
       return accountRepo.save(account).getId();
    }

    public void editAccount(Long id,Account account){
        accountRepo.findById(id).orElseThrow(()->new EntityNotFoundException("not found"));
        if(!id.equals(account.getId()))throw new IllegalArgumentException("id not matching");
        addAccount(account);
    }

    public void deleteAccount(long id){
        Account account=accountRepo.findById(id).orElseThrow(()->new EntityNotFoundException("not found"));
        accountRepo.delete(account);
    }

//    doesn't work ????????
//    Caused by: org.springframework.beans.BeanInstantiationException:
//    Failed to instantiate [org.nnn4eu.nfische.beanscopetest.web.model.Greeting]:
//    Illegal arguments to factory method 'getGreeting';
//    args: ; nested exception is java.lang.IllegalArgumentException: object is not an instance of declaring class

//    @Bean
//    public Greeting getGreeting(){
//        return new Greeting().greetMe();
//    }
//    @Bean
//    public Greeting greetingAgainAgainMonday(Greeting getGreeting){
//        return getGreeting.greetMe("InCalculatorServiceGreeting");
//    }

//    @Bean
//    public String say(){
//        System.out.println("say() invoked");
//        return "Holla";
//    }
}
