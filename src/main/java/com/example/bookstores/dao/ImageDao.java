package com.example.bookstores.dao;

import com.example.bookstores.entity.Image;

public interface ImageDao {
    Image getImageByName(String name);
    Image getImageById(String id);

    void saveImage(Image image);
}
