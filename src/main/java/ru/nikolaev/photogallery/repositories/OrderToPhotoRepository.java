package ru.nikolaev.photogallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikolaev.photogallery.model.OrderToPhoto;

@Repository
public interface OrderToPhotoRepository extends JpaRepository<OrderToPhoto, Long> {}
