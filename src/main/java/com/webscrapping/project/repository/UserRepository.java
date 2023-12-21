package com.webscrapping.project.repository;

import com.webscrapping.project.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, Long> {

    public UserDetails findByLogin(String login);
}
