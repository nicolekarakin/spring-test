package org.nnn4eu.nfische.beanscopetest.web.controller;

import org.nnn4eu.nfische.beanscopetest.service.IAccountService;
import org.nnn4eu.nfische.beanscopetest.web.model.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("account")
public class AccountCtrl {
//    private final AccountService accountService;
    private final IAccountService accountService;

    public AccountCtrl(IAccountService accountService) {
        this.accountService = accountService;
        System.out.println("custom constructor AccountCtrl -----------------------");
        System.out.println(this.getClass().getCanonicalName());
        System.out.println(accountService.getClass().getCanonicalName());
        System.out.println("end of constructor -----------------------");
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id){
        return accountService.getAccount(id);
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @PostMapping("/{id}")
    public void getAccounts(@PathVariable Long id, @RequestBody Account account){
        accountService.editAccount(id, account);
    }

    @PostMapping
    public void getAccounts(@RequestBody Account account){
        accountService.addAccount(account);
    }
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }
}

//curl -d '{"name":"Mario","color":"blue"}' -X POST -H "Content-Type: application/json" localhost:8080/account

