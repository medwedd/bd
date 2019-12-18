package ru.nikolaev.photogallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "servicetophoto")
public class ServiceToPhoto {

   private Long id;
   private String title;
   private String description;
   private Integer cost;
   private Integer durability;
   private List<OrderToPhoto> orderToPhotoList;

   @Id
   @Column(name = "id_servicetophoto")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Basic
   @Column(name = "title")
   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   @Basic
   @Column(name = "description")
   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Basic
   @Column(name = "cost")
   public Integer getCost() {
      return cost;
   }

   public void setCost(Integer cost) {
      this.cost = cost;
   }

   @Basic
   @Column(name = "durability")
   public Integer getDurability() {
      return durability;
   }

   public void setDurability(Integer durability) {
      this.durability = durability;
   }

   @ManyToMany
   @JsonIgnore
   public List<OrderToPhoto> getOrderToPhotoList() {
      return orderToPhotoList;
   }

   public void setOrderToPhotoList(List<OrderToPhoto> orderToPhotoList) {
      this.orderToPhotoList = orderToPhotoList;
   }
}
