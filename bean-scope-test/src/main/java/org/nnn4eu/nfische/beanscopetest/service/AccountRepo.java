package org.nnn4eu.nfische.beanscopetest.service;

import org.nnn4eu.nfische.beanscopetest.web.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepo extends JpaRepository<Account,Long> {
}
