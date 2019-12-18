package ru.nikolaev.photogallery.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.nikolaev.photogallery.dao.OrderDao;
import ru.nikolaev.photogallery.files.controller.Util;
import ru.nikolaev.photogallery.files.exception.FileStorageException;
import ru.nikolaev.photogallery.files.payload.UploadFileResponse;
import ru.nikolaev.photogallery.files.service.FileStorageService;
import ru.nikolaev.photogallery.model.Order;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private Util util;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile{}")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        Long id = util.getStringLongMap().get("id");
        Order one = orderDao.findOne(id);
        if(!file.getContentType().equals("image/png")){
            throw  new FileStorageException("Файл должен быть с расширением png");
        }
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
        one.setPhotoUrl(uploadFileResponse.getFileDownloadUri());
        orderDao.save(one);
        return "redirect:/orders/" + id;
    }

    @PostMapping("/uploadMultipleFiles")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return uploadFile(files[0]);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
