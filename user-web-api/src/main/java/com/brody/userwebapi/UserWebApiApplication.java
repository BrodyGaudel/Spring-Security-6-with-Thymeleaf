package com.brody.userwebapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
public class UserWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserWebApiApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder getBCE(){
        return new BCryptPasswordEncoder();
    }




}
