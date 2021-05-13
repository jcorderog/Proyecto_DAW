package nuca.fabrienvaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nuca.fabrienvaf.model.Producto;
import nuca.fabrienvaf.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}
	
	public Producto findByNombre(String nombre) {
		return productoRepository.findByNombre(nombre);
	}
	
	public Optional<Producto> findById (Long id) {
		return productoRepository.findById(id);
	}
	
	public Producto save(Producto p) {
		return productoRepository.save(p);
	}
	
	public Producto addCantidad(Producto p, int cantidadBotesSumar) {
		/*p.setCantidadBotes(p.getCantidadBotes()+cantidadBotesSumar);
		int cantidadPalets;
		for(int i = 0;i< cantidadBotesSumar; i++) {
			if(i==) {
				
			}
		}
		return productoRepository.save(p);*/
		//Preguntar si v aintroducir los palets o los botes.
	}
}
