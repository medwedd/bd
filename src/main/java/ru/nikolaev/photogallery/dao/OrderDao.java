package ru.nikolaev.photogallery.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikolaev.photogallery.model.Order;
import ru.nikolaev.photogallery.repositories.OrderRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderDao {

	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public Order save(Order entity) {
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void delete(Order entity) {
		repository.delete(entity);
	}
	
	public void deleteAll(List<Order> entityList) {
		repository.deleteAll(entityList);
	}
	
}
