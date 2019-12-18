package ru.nikolaev.photogallery.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ordertoprint")
public class OrderToPrint {

   private Long orderId;
   private LocalDate readyDate;
   private List<ServiceToPrint> serviceToPrints;

   @Id
   @Column(name = "id_orders")
   public Long  getOrderId() {
      return orderId;
   }

   public void setOrderId(Long orderId) {
      this.orderId = orderId;
   }

   @Basic
   @Column(name = "readydate")
   public LocalDate getReadyDate() {
      return readyDate;
   }

   public void setReadyDate(LocalDate readyDate) {
      this.readyDate = readyDate;
   }

   @ManyToMany
   @JoinTable(
           name = "containtoprint",
           joinColumns = @JoinColumn(name = "ordertoprint"),
           inverseJoinColumns = @JoinColumn(name = "servicetoprint")
   )
   public List<ServiceToPrint> getServiceToPrints() {
      return serviceToPrints;
   }

   public void setServiceToPrints(List<ServiceToPrint> serviceToPrints) {
      this.serviceToPrints = serviceToPrints;
   }

}
