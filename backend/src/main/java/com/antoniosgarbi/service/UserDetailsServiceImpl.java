package com.antoniosgarbi.service;

import com.antoniosgarbi.entities.User;
import com.antoniosgarbi.repository.UserRepository;
import com.antoniosgarbi.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not Found");
        }
        return new UserDetailsImpl(userOptional);
    }
}
