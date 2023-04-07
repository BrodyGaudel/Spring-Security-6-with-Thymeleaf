package com.brody.userwebapi.services.implementations;

import com.brody.userwebapi.entities.Account;
import com.brody.userwebapi.repositories.AccountRepository;
import com.brody.userwebapi.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account update(Account account) {
        Account a = repository.findById(account.getId()).orElse(null);
        if(a == null){
            log.error("Account Not Found");
            return null;
        }else{
            a.setBalance(account.getBalance());
            a.setCurrecy(account.getCurrecy());
            return repository.save(a);
        }
    }

    @Override
    public Account findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
