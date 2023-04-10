package com.example.bookstores.dao;

import com.example.bookstores.entity.Tag;

public interface TagDao {
    void addTag(Tag tag);

    void removeTag(String content);

    Tag getTagByContent(String content);
}
