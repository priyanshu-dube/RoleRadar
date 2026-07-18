package com.priyanshu.roleradar.service.impl;

import com.priyanshu.roleradar.entity.User;
import com.priyanshu.roleradar.repository.UserRepository;
import com.priyanshu.roleradar.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}