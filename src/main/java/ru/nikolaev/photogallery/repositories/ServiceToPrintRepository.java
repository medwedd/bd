package ru.nikolaev.photogallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikolaev.photogallery.model.ServiceToPrint;

@Repository
public interface ServiceToPrintRepository extends JpaRepository<ServiceToPrint, Long> {}
