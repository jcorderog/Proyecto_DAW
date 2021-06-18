package nuca.fabrienvaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nuca.fabrienvaf.model.TipoProducto;
import nuca.fabrienvaf.repository.TipoProductoRepository;

@Service
public class TipoProductoService {
	
	@Autowired
	private TipoProductoRepository tipoProductoRepository;
	
	public Optional<TipoProducto> findById(Long id) {
		return tipoProductoRepository.findById(id);
	}
	
	public TipoProducto findByName(String name) {
		return tipoProductoRepository.findByNombre(name);
	}
	
	public List<TipoProducto> findAll(){
		return tipoProductoRepository.findAll();
	}
}
