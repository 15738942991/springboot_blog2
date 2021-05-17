package com.zp.service;


import com.zp.pojo.User;

public interface UserService {
    User findUserByUsernameAndPassword(String username, String password);

}
