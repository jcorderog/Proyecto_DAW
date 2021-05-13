package nuca.fabrienvaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nuca.fabrienvaf.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>{
	Material findByNombre (String nombre);
}
