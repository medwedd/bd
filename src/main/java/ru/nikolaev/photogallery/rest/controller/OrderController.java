package ru.nikolaev.photogallery.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nikolaev.photogallery.dao.ClientDao;
import ru.nikolaev.photogallery.dao.OrderDao;
import ru.nikolaev.photogallery.dao.OrderStatusDao;
import ru.nikolaev.photogallery.dao.OrderToPhotoDao;
import ru.nikolaev.photogallery.dao.ServiceToPhotoDao;
import ru.nikolaev.photogallery.dao.ServiceToPrintDao;
import ru.nikolaev.photogallery.files.controller.Util;
import ru.nikolaev.photogallery.model.Client;
import ru.nikolaev.photogallery.model.Order;
import ru.nikolaev.photogallery.model.OrderToPhoto;
import ru.nikolaev.photogallery.model.ServiceToPhoto;
import ru.nikolaev.photogallery.model.ServiceToPrint;
import ru.nikolaev.photogallery.repositories.CustomRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class OrderController {

   private ClientDao clientDao;
   private OrderDao orderDao;
   private OrderToPhotoDao orderToPhotoDao;
   private OrderStatusDao orderStatusDao;
   private ServiceToPrintDao serviceToPrintDao;
   private ServiceToPhotoDao serviceToPhotoDao;
   private CustomRepository customRepository;
   private Util util;

   @GetMapping("/orders")
   public String orders(Map<String, Object> model) {
      Collection<Order> results = orderDao.findAll();
//      Collection<Order> orders = orderRepository.findAll();
//      results.forEach(client -> client.setOrderList(orders.stream().filter(ord -> client.getId().equals(ord.getClient().getId())).collect(toList())));
      model.put("selections", results);
      return "orders/orderList";
   }

   @GetMapping("/orders/{id}")
   public String welcome(@PathVariable("id") Long id, Map<String, Object> model) {
      Order result = orderDao.findOne(id);
      util.getStringLongMap().put("id", id);
//      Collection<Order> orders = orderRepository.findAll();
//      results.forEach(client -> client.setOrderList(orders.stream().filter(ord -> client.getId().equals(ord.getClient().getId())).collect(toList())));
      if(result.getPhotoUrl() == null){
         result.setPhotoUrl("");
      }
      model.put("selections", result);
      if (result.getOrderToPrint() != null) {
         Set<Long> ids = result.getOrderToPrint().getServiceToPrints().stream().map(ServiceToPrint::getId).collect(Collectors.toSet());
         model.put("prints", serviceToPrintDao.findAll().stream().filter(serviceToPrint -> !ids.contains(serviceToPrint.getId())).collect(Collectors.toList()));
         model.put("info", result.getOrderToPhotos());
         return "orders/orderPrintDetails";
      } else {
         Set<Long> ids = result.getOrderToPhotos().getServiceToPhotos().stream().map(ServiceToPhoto::getId).collect(Collectors.toSet());
         model.put("photos", serviceToPhotoDao.findAll().stream().filter(serviceToPhoto -> !ids.contains(serviceToPhoto.getId())).collect(Collectors.toList()));
         model.put("info", result.getOrderToPhotos());
         return "orders/orderPhotoDetails";
      }
   }

   @GetMapping("/orders/{id}/edit")
   public String edit(@PathVariable("id") Long id, Map<String, Object> model) {
      Order order = orderDao.findOne(id);
      model.put("order", order);
      return "orders/updateOrderForm";
   }

   @GetMapping("/orders/{id}/new")
   public String newOrder(@PathVariable("id") Long id, Map<String, Object> model) {
      Order order = new Order();
      model.put("order", order);
      return "orders/createOrderForm";
   }

   @PostMapping("/orders/{id}/services/print/new")
   public String newPrint(@PathVariable("id") Long id, @Valid PrintDto printDto, Map<String, Object> model) {
      customRepository.addServiceToPrint(id, printDto.getPrintId());
      return "redirect:/orders/" + id;
   }

   @PostMapping("/orders/{id}/services/photo/new")
   public String newPhoto(@PathVariable("id") Long id, @Valid PhotoDto photoDto, Map<String, Object> model) {
      customRepository.addServiceToPhoto(id, photoDto.getPhotoId());
      return "redirect:/orders/" + id;
   }

   @GetMapping("/orders/{id}/service/print/{serviceId}/remove")
   public String removeServicePrintFromOrder(@PathVariable("id") Long id, @PathVariable("serviceId") Long serviceId, Map<String, Object> model) {
      customRepository.removePrintService(id, serviceId);
      return "redirect:/orders/" + id;
   }

   @GetMapping("/orders/{id}/service/photo/{serviceId}/remove")
   public String removeServicePhotoFromOrder(@PathVariable("id") Long id, @PathVariable("serviceId") Long serviceId, Map<String, Object> model) {
      customRepository.removePhotoService(id, serviceId);
      return "redirect:/orders/" + id;
   }

   @GetMapping("/clients/{id}/orders/photo/new")
   public String getPhotoOrder(@PathVariable("id") Long id, Map<String, Object> model) {
      ServicePhotoDto servicePhotoDto = new ServicePhotoDto();
      OrderToPhoto orderToPhoto = new OrderToPhoto();
      model.put("selections", clientDao.findOne(id));
      model.put("order", servicePhotoDto);
      model.put("date1", orderToPhoto.getPhotographingDate());
      model.put("photos", serviceToPhotoDao.findAll());
      return "orders/createOrderPhotoForm";
   }

   @PostMapping("/clients/{id}/orders/photo/new")
   public String newphotoOrder(@PathVariable("id") Long id, @Valid ServicePhotoDto photoDto, Map<String, Object> model) {
      Order order = new Order();
      order.setClient(clientDao.findOne(id));
      order.setCreationDate(LocalDate.now());
      order.setDiscount(photoDto.getDiscount());
      order.setImmediatly(false);
      order.setOrderStatus(orderStatusDao.findOne(1L));
      Order order1 = orderDao.save(order);
      OrderToPhoto orderToPhoto = new OrderToPhoto();
      orderToPhoto.setOrderId(order1.getId());
      orderToPhoto.setPhotographingDate(LocalDate.parse(photoDto.getADate()));
      orderToPhoto.setPersonCount(1);
      orderToPhotoDao.save(orderToPhoto);
//      customRepository.addServiceToPhoto(order1.getId(), photoDto.getServiceId());
      return "redirect:/orders/" + order1.getId();
   }

   @PostMapping("/clients/{id}/orders/print/new")
   public String newPrintOrder(@PathVariable("id") Long id, @Valid PhotoDto photoDto, Map<String, Object> model) {
      customRepository.addServiceToPhoto(id, photoDto.getPhotoId());
      return "redirect:/orders/" + id;
   }

   @GetMapping("/orders/{id}/finish")
   public String finishOrder(@PathVariable("id") Long id) {
      Order order = orderDao.findOne(id);
      order.setOrderStatus(orderStatusDao.findOne(2L));
      orderDao.save(order);
      return "redirect:/orders/" + id;
   }

   @GetMapping("/services/photo")
   public String photo(Map<String, Object> model) {
      Collection<ServiceToPhoto> results = serviceToPhotoDao.findAll();
//      Collection<Order> orders = orderRepository.findAll();
//      results.forEach(client -> client.setOrderList(orders.stream().filter(ord -> client.getId().equals(ord.getClient().getId())).collect(toList())));
      model.put("selections", results);
      return "orders/servicePhotoList";
   }

   @GetMapping("/services/print")
   public String print(Map<String, Object> model) {
      Collection<ServiceToPrint> results = serviceToPrintDao.findAll();
//      Collection<Order> orders = orderRepository.findAll();
//      results.forEach(client -> client.setOrderList(orders.stream().filter(ord -> client.getId().equals(ord.getClient().getId())).collect(toList())));
      model.put("selections", results);
      return "orders/servicePrintList";
   }

   @GetMapping("/report")
   public String report(Map<String, Object> model) {
      Collection<Client> results = clientDao.findAll();
//      Collection<Order> orders = orderRepository.findAll();
//      results.forEach(client -> client.setOrderList(orders.stream().filter(ord -> client.getId().equals(ord.getClient().getId())).collect(toList())));
      model.put("selections", results);
      return "wer";
   }
}
