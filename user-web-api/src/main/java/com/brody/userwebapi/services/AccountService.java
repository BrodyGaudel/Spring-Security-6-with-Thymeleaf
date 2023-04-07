package com.brody.userwebapi.services;

import com.brody.userwebapi.entities.Account;

public interface AccountService {
    Account update(Account account);
    Account findById(Long id);
}
