package nuca.fabrienvaf.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipoMaterial")
public class TipoMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	private String descripcion;
	
	@OneToMany(mappedBy="tipoMaterial", cascade = CascadeType.PERSIST)
	private Set<Material> materiales;

	public TipoMaterial(Long id, String nombre, String descripcion, Set<Material> materiales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.materiales = materiales;
	}

	public TipoMaterial() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(Set<Material> materiales) {
		this.materiales = materiales;
	}	
}
