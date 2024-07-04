package com.example.WebBanHang.validator;


import com.example.WebBanHang.model.User;
import com.example.WebBanHang.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
       if(user == null)
           return true;
       return user.getId() != null;
    }


}
