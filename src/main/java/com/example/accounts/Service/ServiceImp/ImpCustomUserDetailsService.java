package com.example.accounts.Service.ServiceImp;

import com.example.accounts.Entity.Users;
import com.example.accounts.Exception.ResourceNotFounException;
import com.example.accounts.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImpCustomUserDetailsService implements UserDetailsService {
    final UserRepository userRepository;
    public ImpCustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));
        return new CustomUserDetail(user);
    }
}
