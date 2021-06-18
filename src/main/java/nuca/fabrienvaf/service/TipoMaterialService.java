package nuca.fabrienvaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nuca.fabrienvaf.model.TipoMaterial;
import nuca.fabrienvaf.repository.TipoMaterialRepository;

@Service
public class TipoMaterialService {

	@Autowired
	private TipoMaterialRepository tipoMaterialRepository;
	
	public List<TipoMaterial> findAll () {
		return tipoMaterialRepository.findAll();
	}
	
	public TipoMaterial findByName (String nombre) {
		return tipoMaterialRepository.findByNombre(nombre);
	}
	
	public Optional<TipoMaterial> findById (Long id) {
		return tipoMaterialRepository.findById(id);
	}
	
	public TipoMaterial save (TipoMaterial tipo) {
		return tipoMaterialRepository.save(tipo);
	}
}
