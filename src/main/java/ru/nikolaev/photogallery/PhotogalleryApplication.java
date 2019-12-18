package ru.nikolaev.photogallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.nikolaev.photogallery.files.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class PhotogalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotogalleryApplication.class, args);
	}

}
