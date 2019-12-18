package ru.nikolaev.photogallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

   private Long id;
   private Client client;
   private OrderStatus orderStatus;
   private LocalDate creationDate;
   private Double discount;
   private Boolean immediatly;
   private String photoUrl;
   private OrderToPhoto orderToPhotos;
   private OrderToPrint orderToPrint;

   @Id
   @Column(name = "id_orders")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @ManyToOne
   @JoinColumn(name = "userid", referencedColumnName = "id_users", nullable = false  )
   @JsonIgnore
   public Client getClient() {
      return client;
   }

   public void setClient(Client client) {
      this.client = client;
   }

   @ManyToOne
   @JoinColumn(name = "orderstatus", referencedColumnName = "id_orderstatus", nullable = false )
   public OrderStatus getOrderStatus() {
      return orderStatus;
   }

   public void setOrderStatus(OrderStatus orderStatus) {
      this.orderStatus = orderStatus;
   }

   @Basic
   @Column(name = "createdate")
   public LocalDate getCreationDate() {
      return creationDate;
   }

   public void setCreationDate(LocalDate creationDate) {
      this.creationDate = creationDate;
   }

   @Basic
   @Column(name = "discount")
   public Double getDiscount() {
      return discount;
   }

   public void setDiscount(Double discount) {
      this.discount = discount;
   }

   @Basic
   @Column(name = "immediatly")
   public Boolean getImmediatly() {
      return immediatly;
   }

   public void setImmediatly(Boolean immediatly) {
      this.immediatly = immediatly;
   }

   @OneToOne
   @JoinColumn(name = "id_orders", referencedColumnName = "id_orders", nullable = false)
   public OrderToPhoto getOrderToPhotos() {
      return orderToPhotos;
   }

   public void setOrderToPhotos(OrderToPhoto orderToPhotos) {
      this.orderToPhotos = orderToPhotos;
   }

   @OneToOne
   @JoinColumn(name = "id_orders", referencedColumnName = "id_orders", nullable = false)
   public OrderToPrint getOrderToPrint() {
      return orderToPrint;
   }

   public void setOrderToPrint(OrderToPrint orderToPrint) {
      this.orderToPrint = orderToPrint;
   }

   @Basic
   @Column(name = "photourl")
   public String getPhotoUrl() {
      return photoUrl;
   }

   public void setPhotoUrl(String photoUrl) {
      this.photoUrl = photoUrl;
   }
}
