package ru.nikolaev.photogallery.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nikolaev.photogallery.dao.ClientDao;
import ru.nikolaev.photogallery.dao.OrderDao;
import ru.nikolaev.photogallery.dao.UserRoleDao;
import ru.nikolaev.photogallery.model.Client;
import ru.nikolaev.photogallery.rest.ClientDto;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ClientController {

   private ClientDao clientDao;
   private OrderDao orderRepository;
   private UserRoleDao userRoleDao;

   @GetMapping("/clients")
   public String welcome(Map<String, Object> model) {
      Collection<Client> results = clientDao.findAll();
//      Collection<Order> orders = orderRepository.findAll();
//      results.forEach(client -> client.setOrderList(orders.stream().filter(ord -> client.getId().equals(ord.getClient().getId())).collect(toList())));
      model.put("selections", results);
      return "clients/clientList";
   }

   @GetMapping("/clients/{id}")
   public String welcome(@PathVariable("id") Long id, Map<String, Object> model) {
      Client result = clientDao.findOne(id);
//      Collection<Order> orders = orderRepository.findAll();
//      results.forEach(client -> client.setOrderList(orders.stream().filter(ord -> client.getId().equals(ord.getClient().getId())).collect(toList())));
      model.put("selections", result);
      return "clients/clientDetails";
   }

   @GetMapping("/clients/new")
   public String initCreationForm(Map<String, Object> model) {
      Client owner = new Client();
      model.put("client", owner);
      return "clients/createClientForm";
   }

   @PostMapping("/clients/new")
   public String processCreationForm(@Valid ClientDto client, BindingResult result) {
      if (result.hasErrors()) {
         return "clients/createClientForm";
      } else {

         Client clientEntity = new Client();
         clientEntity.setUserRoleId(1L);
         clientEntity.setName(client.getName());
         clientEntity.setEmail(client.getEmail());
         clientEntity.setPassword("123");
         clientEntity.setRegistrationDate(LocalDate.now());
         clientEntity.setPhone(client.getPhone());
         clientDao.save(clientEntity);
         return "redirect:/clients/" + clientEntity.getId();
      }
   }

   @GetMapping("/clients/{id}/edit")
   public String updateForm(@PathVariable("id") Long id, Map<String, Object> model) {
      Client client = clientDao.findOne(id);
      model.put("client", client);
      return "clients/updateClientForm";
   }
}
