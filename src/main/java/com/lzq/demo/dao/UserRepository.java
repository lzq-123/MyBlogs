package com.lzq.demo.dao;

import com.lzq.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String name, String password);
}
