package ru.nikolaev.photogallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikolaev.photogallery.model.ServiceToPhoto;

@Repository
public interface ServiceToPhotoRepository extends JpaRepository<ServiceToPhoto, Long> {}
