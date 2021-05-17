package com.zp.service.Impl;

import com.zp.dao.UserRepository;
import com.zp.pojo.User;
import com.zp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        return user;
    }
}
