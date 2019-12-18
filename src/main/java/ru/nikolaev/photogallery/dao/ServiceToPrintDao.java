package ru.nikolaev.photogallery.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikolaev.photogallery.model.ServiceToPrint;
import ru.nikolaev.photogallery.repositories.ServiceToPrintRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ServiceToPrintDao {

   private ServiceToPrintRepository repository;

   public List<ServiceToPrint> findAll() {
      return repository.findAll();
   }

   public ServiceToPrint findOne(Long id) {
      return repository.findById(id).get();
   }

   public ServiceToPrint save(ServiceToPrint entity) {
      return repository.save(entity);
   }

   public void delete(Long id) {
      repository.deleteById(id);
   }

   public void delete(ServiceToPrint entity) {
      repository.delete(entity);
   }

   public void deleteAll(List<ServiceToPrint> entityList) {
      repository.deleteAll(entityList);
   }

}
