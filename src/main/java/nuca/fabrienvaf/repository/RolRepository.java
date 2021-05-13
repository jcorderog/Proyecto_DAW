package nuca.fabrienvaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nuca.fabrienvaf.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	Rol findByName(String name);
}
