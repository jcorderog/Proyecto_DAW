package nuca.fabrienvaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nuca.fabrienvaf.model.TipoPalet;
import nuca.fabrienvaf.repository.TipoPaletRepository;

@Service
public class TipoPaletService {
	
	@Autowired
	private TipoPaletRepository tipoPaletRepository;
	
	public Optional<TipoPalet> findById(Long id) {
		return tipoPaletRepository.findById(id);
	}
	
	public TipoPalet findByName(String name) {
		return tipoPaletRepository.findByNombre(name);
	}
	
	public List<TipoPalet> findAll(){
		return tipoPaletRepository.findAll();
	}
}
