package com.example.bookstores.repository;

import com.example.bookstores.dao.ImageDao;
import com.example.bookstores.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
    Image getImageByImageName(String name);
    Image getImageBy_id(String id);
}
