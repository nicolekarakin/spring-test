package org.nnn4eu.nfische.beanscopetest.service;

import org.nnn4eu.nfische.beanscopetest.web.model.Account;

import java.util.List;

public interface IAccountService {
    Account getAccount(Long id);
    List<Account> getAccounts();
    Long addAccount(Account account);
    void editAccount(Long id,Account account);
    void deleteAccount(long id);
}
