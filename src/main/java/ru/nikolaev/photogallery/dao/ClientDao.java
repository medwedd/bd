package ru.nikolaev.photogallery.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikolaev.photogallery.model.Client;
import ru.nikolaev.photogallery.repositories.ClientRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientDao {

	private ClientRepository repository;
	
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public Client findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public Client save(Client entity) {
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void delete(Client entity) {
		repository.delete(entity);
	}
	
	public void deleteAll(List<Client> entityList) {
		repository.deleteAll(entityList);
	}
	
}
