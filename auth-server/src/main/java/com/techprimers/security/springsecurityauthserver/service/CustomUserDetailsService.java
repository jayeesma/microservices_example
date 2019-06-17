package com.techprimers.security.springsecurityauthserver.service;

import com.techprimers.security.springsecurityauthserver.model.CustomUserDetails;
import com.techprimers.security.springsecurityauthserver.model.Users;
import com.techprimers.security.springsecurityauthserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("C6");
        Optional<Users> usersOp = userRepository.findByName(username);
        //System.out.println("User---------"+usersOp.get());
        usersOp
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));

        //System.out.println(usersOp.map(users -> new CustomUserDetails(users)).get());
        return usersOp
                .map(CustomUserDetails::new)
                .get();
    }
}
