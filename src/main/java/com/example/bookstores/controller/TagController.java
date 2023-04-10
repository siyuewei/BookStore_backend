package com.example.bookstores.controller;

import com.example.bookstores.entity.Tag;
import com.example.bookstores.service.TagService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "api/tag/{content}",method = RequestMethod.GET)
    Tag getTagByContent(@PathVariable String content){
        return tagService.getTagByContent(content);
    }

    @RequestMapping(value = "api/tag/delete",method = RequestMethod.DELETE)
    void removeTag(@PathVariable String content){
        tagService.removeTag(content);
    }

    @RequestMapping(value = "api/tag/add",method = RequestMethod.POST)
    void addTag(String content){
        tagService.addTag(new Tag(content));
    }
}
