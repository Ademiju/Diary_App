package com.africa.semicolon.diaryapp.config;

import com.africa.semicolon.diaryapp.datas.models.RoleType;
import com.africa.semicolon.diaryapp.datas.models.User;
import com.africa.semicolon.diaryapp.datas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.net.PasswordAuthentication;

@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(userRepository.findUserByEmail("admin@gmail.com").isEmpty()) {
            User user = new User("admin@gmail.com",passwordEncoder.encode("password123"), RoleType.ROLE_ADMIN);
            userRepository.save(user);
        }

    }
}
