package com.brody.userwebapi.services;

import com.brody.userwebapi.entities.Role;
import com.brody.userwebapi.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findUserByUsername (String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
    List<User> findAllUsers();
}
