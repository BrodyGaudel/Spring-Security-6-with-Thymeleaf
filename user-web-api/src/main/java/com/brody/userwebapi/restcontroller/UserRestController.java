package com.brody.userwebapi.restcontroller;

import com.brody.userwebapi.entities.Account;
import com.brody.userwebapi.entities.User;
import com.brody.userwebapi.exception.NotAuthorizedException;
import com.brody.userwebapi.services.AccountService;
import com.brody.userwebapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserRestController {

    private final UserService userService;
    private final AccountService accountService;

    public UserRestController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/get")
    @ResponseBody
    public User getCurrentUsers(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails userDetails){
            username = userDetails.getUsername();
        }else{
            username = principal.toString();
        }
        User user = userService.findUserByUsername(username);
        if(user != null){
            log.info("USER FOUND");
            user.setPassword(null);
            return user;
        }else{
            log.error("USER NOT FOUND");
            return null;
        }
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public Account getAccountByUserId(@PathVariable(name = "id") Long id) throws NotAuthorizedException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails userDetails){
            username = userDetails.getUsername();
        }else{
            username = principal.toString();
        }
        User user = userService.findUserByUsername(username);
        Account account = accountService.findById(id);
        Account userAccount = user.getAccount();
        if (account.getId().equals(userAccount.getId())) {
            log.info("ce compte vous appartient");
            return account;
        }else {
            throw new NotAuthorizedException("ce compte ne vous appartient pas");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


