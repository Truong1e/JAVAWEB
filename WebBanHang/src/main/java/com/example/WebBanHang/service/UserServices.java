package com.example.WebBanHang.service;

import com.example.WebBanHang.model.User;
import com.example.WebBanHang.repository.IRoleRepository;
import com.example.WebBanHang.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if(roleId != 0 && userId !=0){
            userRepository.addUserRole(userId, roleId);
        }
    }
}
