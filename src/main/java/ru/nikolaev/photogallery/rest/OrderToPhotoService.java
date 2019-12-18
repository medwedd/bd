package ru.nikolaev.photogallery.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nikolaev.photogallery.dao.OrderToPhotoDao;
import ru.nikolaev.photogallery.model.OrderToPhoto;

import java.util.List;

@RestController
@RequestMapping("/rest/ordertophoto")
@AllArgsConstructor
public class OrderToPhotoService { 

	private OrderToPhotoDao dao;

	@RequestMapping(method=RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderToPhoto> readAll(){
		return dao.findAll();
	}
	
	@RequestMapping(value="/{id}",
		method=RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderToPhoto read(@PathVariable("id") Long id){
		return dao.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, 
		produces = MediaType.APPLICATION_JSON_VALUE, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderToPhoto create(@RequestBody OrderToPhoto entity){
		return dao.save(entity);
	}
	
	@RequestMapping(method=RequestMethod.PUT, 
		produces = MediaType.APPLICATION_JSON_VALUE, 
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderToPhoto update(@RequestBody OrderToPhoto entity){
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
	public boolean deleteAll(@RequestBody List<OrderToPhoto> entityList){
		dao.deleteAll(entityList);
		return true;
	}

}
