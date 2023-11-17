package com.example.bookstores.dao.impl;

import com.example.bookstores.dao.ImageDao;
import com.example.bookstores.entity.Image;
import com.example.bookstores.repository.ImageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl implements ImageDao {
    private final ImageRepository imageRepository;

    public ImageDaoImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getImageByName(String name) {
        return imageRepository.getImageByImageName(name);
    }

    @Override
    public Image getImageById(String id) {
        return imageRepository.getImageBy_id(id);
    }

    @Override
    public void saveImage(Image image) {
        imageRepository.save(image);
    }
}
