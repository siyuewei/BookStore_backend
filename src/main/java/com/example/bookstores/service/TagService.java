package com.example.bookstores.service;

import com.example.bookstores.entity.Tag;

public interface TagService {
    void addTag(Tag tag);

    void removeTag(String content);

    Tag getTagByContent(String context);
}
