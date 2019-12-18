package ru.nikolaev.photogallery.files.controller;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@Component
public class MultipartFileDto {
   private MultipartFile[] file;
   private Long orderId;
}
