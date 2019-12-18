package ru.nikolaev.photogallery.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikolaev.photogallery.model.ServiceToPhoto;
import ru.nikolaev.photogallery.repositories.ServiceToPhotoRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ServiceToPhotoDao {

	private ServiceToPhotoRepository repository;
	
	public List<ServiceToPhoto> findAll() {
		return repository.findAll();
	}
	
	public ServiceToPhoto findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public ServiceToPhoto save(ServiceToPhoto entity) {
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void delete(ServiceToPhoto entity) {
		repository.delete(entity);
	}
	
	public void deleteAll(List<ServiceToPhoto> entityList) {
		repository.deleteAll(entityList);
	}
	
}
