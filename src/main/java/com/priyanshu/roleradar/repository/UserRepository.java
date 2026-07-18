package com.priyanshu.roleradar.repository;

import com.priyanshu.roleradar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}