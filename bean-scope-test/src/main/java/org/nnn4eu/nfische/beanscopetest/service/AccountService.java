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
}
