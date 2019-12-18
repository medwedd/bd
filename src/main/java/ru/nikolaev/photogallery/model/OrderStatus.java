package ru.nikolaev.photogallery.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "orderstatus")
public class OrderStatus {

   private Long id;
   private String title;
   private String description;

   @Id
   @Column(name = "id_orderstatus")
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

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      OrderStatus that = (OrderStatus) o;
      return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, title, description);
   }

   @Override
   public String toString() {
      return "OrderStatus{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + '}';
   }
}
