package com.findar.findar.service;

import com.findar.findar.models.User;
import com.findar.findar.models.UserPrincipal;
import com.findar.findar.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAuthDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + s + "Not Found in DB"));
        return UserPrincipal.create(user);

    }
}