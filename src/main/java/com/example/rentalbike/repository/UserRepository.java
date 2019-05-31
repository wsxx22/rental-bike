package com.example.rentalbike.repository;

import com.example.rentalbike.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername (String username);

    long deleteByUsername (String username);

}
