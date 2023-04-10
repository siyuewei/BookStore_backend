package com.example.bookstores.repository;

import com.example.bookstores.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByContent(String content);
}
