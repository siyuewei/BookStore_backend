package com.example.bookstores.repository;

import com.example.bookstores.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth,Long> {

    UserAuth getByUsername(String username);

    void deleteByUsername(String username);

}
