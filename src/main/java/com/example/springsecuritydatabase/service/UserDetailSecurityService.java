package com.example.springsecuritydatabase.service;

import com.example.springsecuritydatabase.model.User;
import com.example.springsecuritydatabase.model.UserPrincipal;
import com.example.springsecuritydatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailSecurityService  implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(name);
        Optional.ofNullable(user)
                .orElseThrow(() -> new UsernameNotFoundException("User with user name "+ name + " not found"));
        return new UserPrincipal(user);
    }
}
