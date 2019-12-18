package ru.nikolaev.photogallery.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ordertophoto")
public class OrderToPhoto implements Serializable {

   private Long orderId;
   private LocalDate photographingDate;
   private Integer personCount;
   private List<ServiceToPhoto> serviceToPhotos;

   @Id
   @Column(name = "id_orders")
   public Long  getOrderId() {
      return orderId;
   }

   public void setOrderId(Long orderId) {
      this.orderId = orderId;
   }

   @Basic
   @Column(name = "photographingdate")
   public LocalDate getPhotographingDate() {
      return photographingDate;
   }

   public void setPhotographingDate(LocalDate photographingDate) {
      this.photographingDate = photographingDate;
   }

   @Basic
   @Column(name = "personcount")
   public Integer getPersonCount() {
      return personCount;
   }

   public void setPersonCount(Integer personCount) {
      this.personCount = personCount;
   }

   @ManyToMany
   @JoinTable(
           name = "containtophoto",
           joinColumns = @JoinColumn(name = "ordertophoto"),
           inverseJoinColumns = @JoinColumn(name = "servicetophoto")
   )
   public List<ServiceToPhoto> getServiceToPhotos() {
      return serviceToPhotos;
   }

   public void setServiceToPhotos(List<ServiceToPhoto> serviceToPhotos) {
      this.serviceToPhotos = serviceToPhotos;
   }
}
