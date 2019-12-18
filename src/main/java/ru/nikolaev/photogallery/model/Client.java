package ru.nikolaev.photogallery.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Client {

   private Long id;
   private Long userRoleId;
   private UserRole userRole;
   private String name;
   private String password;
   private String email;
   private String phone;

   private LocalDate registrationDate;

   private List<Order> orderList;

   @Id
   @Column(name = "id_users")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Basic
   @Column(name = "userrole")
   public Long getUserRoleId() {
      return userRoleId;
   }

   public void setUserRoleId(Long userRoleId) {
      this.userRoleId = userRoleId;
   }

   @ManyToOne
   @JoinColumn(name = "userrole", referencedColumnName = "id_userrole", nullable = false, insertable = false, updatable = false)
   public UserRole getUserRole() {
      return userRole;
   }

   public void setUserRole(UserRole userRole) {
      this.userRole = userRole;
   }

   @Basic
   @Column(name = "name")
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Basic
   @Column(name = "password")
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Basic
   @Column(name = "email")
   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Basic
   @Column(name = "registrationdate")
   public LocalDate getRegistrationDate() {
      return registrationDate;
   }

   public void setRegistrationDate(LocalDate registrationDate) {
      this.registrationDate = registrationDate;
   }

   @OneToMany(mappedBy = "client")
   public List<Order> getOrderList() {
      return orderList;
   }

   public void setOrderList(List<Order> orderList) {
      this.orderList = orderList;
   }

   @Basic
   @Column(name = "phone")
   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Client client = (Client) o;
      return Objects.equals(id, client.id) && Objects.equals(userRole, client.userRole) && Objects.equals(name, client.name) && Objects.equals(password, client.password) && Objects.equals(
              email,
              client.email) && Objects.equals(registrationDate, client.registrationDate);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, userRole, name, password, email, registrationDate);
   }

   @Override
   public String toString() {
      return "Client{" + "id=" + id + ", userRole=" + userRole + ", name='" + name + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", registrationDate=" + registrationDate + '}';
   }
}
