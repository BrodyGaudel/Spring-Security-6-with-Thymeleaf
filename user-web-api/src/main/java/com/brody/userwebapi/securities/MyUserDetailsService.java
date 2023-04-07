package com.brody.userwebapi.securities;

import com.brody.userwebapi.entities.User;
import com.brody.userwebapi.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found !");
        }
        List<GrantedAuthority> auths = new ArrayList<>();
        user.getRoles().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            auths.add(authority);
        });
        return new org.springframework.security.core.
                userdetails.User(user.getUsername(),user.getPassword(),auths);
    }
}
