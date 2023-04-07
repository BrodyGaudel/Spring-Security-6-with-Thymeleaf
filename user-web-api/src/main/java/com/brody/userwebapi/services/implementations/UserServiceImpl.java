package com.brody.userwebapi.services.implementations;

import com.brody.userwebapi.entities.Account;
import com.brody.userwebapi.entities.Role;
import com.brody.userwebapi.entities.User;
import com.brody.userwebapi.enums.Currecy;
import com.brody.userwebapi.repositories.RoleRepository;
import com.brody.userwebapi.repositories.UserRepository;
import com.brody.userwebapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User saveUser(User user) {
        Boolean exist = userRepository.verifyIfUsernameExists(user.getUsername());
        if(Boolean.TRUE.equals(exist)){
            log.info("username already exist");
            return null;
        }else{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Account account = new Account();
            account.setId(null);
            account.setBalance(0.0);
            account.setCurrecy(Currecy.EUR);
            account.setUser(user);
            user.setAccount(account);
            User userSaved = userRepository.save(user);
            return addRoleToUser(userSaved.getUsername(), "USER");
        }
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(rolename);
        user.getRoles().add(role);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
