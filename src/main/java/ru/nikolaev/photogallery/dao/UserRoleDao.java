package ru.nikolaev.photogallery.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikolaev.photogallery.model.UserRole;
import ru.nikolaev.photogallery.repositories.UserRoleRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserRoleDao {

	private UserRoleRepository repository;
	
	public List<UserRole> findAll() {
		return repository.findAll();
	}
	
	public UserRole findById(Long id) {
		return repository.findById(id).get();
	}
	
	public UserRole save(UserRole entity) {
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void delete(UserRole entity) {
		repository.delete(entity);
	}
	
	public void deleteAll(List<UserRole> entityList) {
		repository.deleteAll(entityList);
	}
	
}
