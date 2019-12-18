package ru.nikolaev.photogallery.repositories;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Service
public class CustomRepository {

   @PersistenceContext
   private EntityManager entityManager;

   private static final String CREATE_PRINT = "insert into ContainToPrint (OrderToPrint, ServiceToPrint) values (?, ?);";
   private static final String CREATE_PHOTO = "insert into ContainToPhoto (OrderToPhoto, ServiceToPhoto) values (?, ?);";
   private static final String REMOVE_PHOTO = "DELETE FROM ContainToPhoto WHERE OrderToPhoto=? and ServiceToPhoto=?";
   private static final String REMOVE_PRINT = "DELETE FROM ContainToPrint WHERE OrderToPrint=? and ServiceToPrint=?";



   public void addServiceToPrint(Long orderId, Long serviceToprintId) {
      entityManager.createNativeQuery(CREATE_PRINT).setParameter(1, orderId).setParameter(2, serviceToprintId).executeUpdate();
   }

   public void addServiceToPhoto(Long id, Long photoId) {
      entityManager.createNativeQuery(CREATE_PHOTO).setParameter(1, id).setParameter(2, photoId).executeUpdate();
   }

   public void removePrintService(Long id, Long serviceId) {
      entityManager.createNativeQuery(REMOVE_PRINT).setParameter(1, id).setParameter(2, serviceId).executeUpdate();
   }

   public void removePhotoService(Long id, Long serviceId) {
      entityManager.createNativeQuery(REMOVE_PHOTO).setParameter(1, id).setParameter(2, serviceId).executeUpdate();
   }
}
