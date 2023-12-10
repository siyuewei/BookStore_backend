package com.example.bookstores.controller;

import com.example.bookstores.entity.Image;
import com.example.bookstores.service.ImageService;
import com.example.bookstores.util.msg.Msg;
import com.example.bookstores.util.msg.MsgUtil;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

@RestController
@RequestMapping("/api/img")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{img_id}")
    public void findImg(@PathVariable("img_id") String imgId, @NotNull HttpServletResponse response) throws IOException {
        if (imgId.equals("null")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "img_name is null");
            return;
        }
        try {
            Image image = imageService.getImageById(imgId);
            if (image == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
                return;
            }

            byte[] imageData = Base64.getDecoder().decode(image.getImageBase64());
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(imageData);
            outputStream.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving image: " + e.getMessage());
        }
    }

    @PostMapping(value = "/upload")
    public Msg uploadImg(@RequestBody MultipartFile file) {
        try {
            byte[] imageBytes = file.getBytes();
            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

            Image image = new Image();
            image.setImageName(file.getOriginalFilename());
            image.setImageBase64(imageBase64);

            imageService.saveImage(image);

            // You can return the image ID or any other relevant information
            JSONObject resp = new JSONObject();
            resp.put("path", image.get_id());

            return MsgUtil.makeMsg(MsgUtil.SUCCESS, "上传成功", resp);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgUtil.makeMsg(MsgUtil.UPLOAD_ERROR, "上传失败");
        }
    }
}
