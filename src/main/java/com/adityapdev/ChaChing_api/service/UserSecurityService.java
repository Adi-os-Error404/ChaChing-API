package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.entity.UserPrincipal;
import com.adityapdev.ChaChing_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User \"%s\" does not exist", username)));
        return new UserPrincipal(user); // Interface UserDetails impl: UserPrincipal
    }

}
