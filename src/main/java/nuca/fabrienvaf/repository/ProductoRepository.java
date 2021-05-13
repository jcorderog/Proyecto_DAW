package nuca.fabrienvaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nuca.fabrienvaf.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	Producto findByNombre (String nombre);
}
