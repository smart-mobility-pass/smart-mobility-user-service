package com.mobilitypass.user_mobility.repository;

import com.mobilitypass.user_mobility.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);}
