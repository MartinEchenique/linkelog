package com.echenique.linkelog.security;

import com.echenique.linkelog.models.UserProfile;
import com.echenique.linkelog.repositories.UserRepository;
import com.echenique.linkelog.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JdbcSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user = userRepository.getProfileByUsername(username);
        return new UserSecurity(user);
    }
}
