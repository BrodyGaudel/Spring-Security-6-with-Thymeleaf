package com.brody.userwebapi.repositories;

import com.brody.userwebapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select case when count(u)>0 then true else false END from User u where u.username=?1")
    Boolean verifyIfUsernameExists(String username);
}
