package ru.nikolaev.photogallery.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikolaev.photogallery.model.OrderStatus;
import ru.nikolaev.photogallery.repositories.OrderStatusRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderStatusDao {

	private OrderStatusRepository repository;
	
	public List<OrderStatus> findAll() {
		return repository.findAll();
	}
	
	public OrderStatus findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public OrderStatus save(OrderStatus entity) {
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void delete(OrderStatus entity) {
		repository.delete(entity);
	}
	
	public void deleteAll(List<OrderStatus> entityList) {
		repository.deleteAll(entityList);
	}
	
}
