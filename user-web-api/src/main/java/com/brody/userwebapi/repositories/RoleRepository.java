package com.brody.userwebapi.repositories;

import com.brody.userwebapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String rolename);
}
