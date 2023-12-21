package com.example.demo.repository;

import com.example.demo.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, Long> {

    public UserDetails findByLogin(String login);
}
