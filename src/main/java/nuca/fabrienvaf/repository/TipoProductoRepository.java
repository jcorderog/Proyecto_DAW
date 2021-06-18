package nuca.fabrienvaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nuca.fabrienvaf.model.TipoProducto;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long>{
	TipoProducto findByNombre(String nombre);
}
