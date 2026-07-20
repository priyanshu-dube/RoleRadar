package com.priyanshu.roleradar.service;

import com.priyanshu.roleradar.entity.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> getAllUsers();
}