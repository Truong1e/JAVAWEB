package com.example.WebBanHang.service;

import com.example.WebBanHang.model.CustomUserDetail;
import com.example.WebBanHang.model.User;
import com.example.WebBanHang.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailServices implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw  new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(user, userRepository);
    }
}
