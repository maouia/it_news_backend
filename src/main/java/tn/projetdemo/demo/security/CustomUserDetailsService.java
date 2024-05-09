package tn.projetdemo.demo.security;




import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import tn.projetdemo.demo.repository.UserRepository;

import tn.projetdemo.demo.entities.User;


import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPass_ut(),Collections.emptyList());
    }

   
}
