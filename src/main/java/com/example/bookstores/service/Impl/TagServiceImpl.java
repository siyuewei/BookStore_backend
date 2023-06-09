package com.example.bookstores.service.Impl;

import com.example.bookstores.dao.TagDao;
import com.example.bookstores.entity.Tag;
import com.example.bookstores.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public void addTag(Tag tag) {
        tagDao.addTag(tag);
    }

    @Override
    public void removeTag(String content) {
        tagDao.removeTag(content);
    }

    @Override
    public Tag getTagByContent(String context) {
        return tagDao.getTagByContent(context);
    }
}
