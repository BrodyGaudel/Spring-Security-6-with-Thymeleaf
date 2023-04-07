package com.brody.userwebapi.repositories;

import com.brody.userwebapi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
