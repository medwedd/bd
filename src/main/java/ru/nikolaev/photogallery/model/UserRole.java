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
@Table(name = "userrole")
public class UserRole {

   private Long id;
   private String title;
   private String description;

   @Id
   @Column(name = "id_userrole")
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
      UserRole userRole = (UserRole) o;
      return Objects.equals(id, userRole.id) && Objects.equals(title, userRole.title) && Objects.equals(description, userRole.description);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, title, description);
   }

   @Override
   public String toString() {
      return "UserRole{" + "id=" + id + ", title='" + title + '\'' + ", description='" + description + '\'' + '}';
   }
}
