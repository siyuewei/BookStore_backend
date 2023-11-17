package com.example.bookstores.service;

import com.example.bookstores.dao.ImageDao;
import com.example.bookstores.entity.Image;

public interface ImageService {
    Image getImageByName(String name);
    Image getImageById(String id);
    void saveImage(Image image);
}
