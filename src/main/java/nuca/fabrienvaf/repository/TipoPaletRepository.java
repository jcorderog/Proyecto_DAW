package nuca.fabrienvaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nuca.fabrienvaf.model.TipoPalet;

@Repository
public interface TipoPaletRepository extends JpaRepository<TipoPalet, Long>{

}
