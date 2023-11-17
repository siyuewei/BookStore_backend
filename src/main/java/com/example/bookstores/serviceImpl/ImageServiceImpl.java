package com.example.bookstores.serviceImpl;

import com.example.bookstores.dao.ImageDao;
import com.example.bookstores.entity.Image;
import com.example.bookstores.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public Image getImageByName(String name) {
        return imageDao.getImageByName(name);
    }

    @Override
    public Image getImageById(String id) {
        return imageDao.getImageById(id);
    }

    @Override
    public void saveImage(Image image) {
        imageDao.saveImage(image);
    }
}
