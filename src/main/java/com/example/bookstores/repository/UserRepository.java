package com.example.bookstores.repository;

import com.example.bookstores.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    void deleteByUsername(String username);

    User getByUsername(String username);

    User getUserById(Long id);

}
