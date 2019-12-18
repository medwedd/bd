package ru.nikolaev.photogallery.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikolaev.photogallery.model.OrderToPhoto;
import ru.nikolaev.photogallery.repositories.OrderToPhotoRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderToPhotoDao {

	private OrderToPhotoRepository repository;
	
	public List<OrderToPhoto> findAll() {
		return repository.findAll();
	}
	
	public OrderToPhoto findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public OrderToPhoto save(OrderToPhoto entity) {
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void delete(OrderToPhoto entity) {
		repository.delete(entity);
	}
	
	public void deleteAll(List<OrderToPhoto> entityList) {
		repository.deleteAll(entityList);
	}
	
}
