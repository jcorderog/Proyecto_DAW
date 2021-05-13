package nuca.fabrienvaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nuca.fabrienvaf.model.TipoMaterial;

@Repository
public interface TipoMaterialRepository extends JpaRepository<TipoMaterial, Long>{

}
