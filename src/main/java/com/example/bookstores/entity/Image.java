package com.example.bookstores.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "image")
public class Image {
    @Id
    private String _id;

    private String imageName;

    private String imageBase64;
}
