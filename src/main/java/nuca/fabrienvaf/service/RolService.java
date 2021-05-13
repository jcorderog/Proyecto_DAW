package nuca.fabrienvaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nuca.fabrienvaf.model.Rol;
import nuca.fabrienvaf.repository.RolRepository;

@Service
public class RolService {
	
	@Autowired
	private RolRepository rolRepository;
	
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}
	
	public Rol findByName(String name) {
		return rolRepository.findByName(name);
	}
	
	public Optional<Rol> findById (Long id) {
		return rolRepository.findById(id);
	}
	
}
