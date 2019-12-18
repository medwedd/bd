package ru.nikolaev.photogallery.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nikolaev.photogallery.dao.OrderDao;
import ru.nikolaev.photogallery.model.Order;

import java.util.List;

@RestController
@RequestMapping("/rest/order")
@AllArgsConstructor
public class OrderService { 

	private OrderDao dao;

	@RequestMapping(method=RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> readAll(){
		return dao.findAll();
	}
	
	@RequestMapping(value="/{id}",
		method=RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public Order read(@PathVariable("id") Long id){
		return dao.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON_VALUE, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public Order create(@RequestBody Order entity){
		return dao.save(entity);
	}
	
	@RequestMapping(method=RequestMethod.PUT, 
		produces = MediaType.APPLICATION_JSON_VALUE, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public Order update(@RequestBody Order entity){
		return dao.save(entity);
	}
	
	@RequestMapping(value="/{id}",
		method=RequestMethod.DELETE, 
		produces = MediaType.APPLICATION_JSON_VALUE, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean delete(@PathVariable("id") Long id){
		dao.delete(id);
		return true;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, 
		produces = MediaType.APPLICATION_JSON_VALUE, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteAll(@RequestBody List<Order> entityList){
		dao.deleteAll(entityList);
		return true;
	}

}
