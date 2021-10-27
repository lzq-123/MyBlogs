package com.lzq.demo.service;

import com.lzq.demo.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
