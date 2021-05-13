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
@Table(name="tipoPalet")
public class TipoPalet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	private String medidas;
	private String descripcion;
	
	@OneToMany(mappedBy="tipoPalet", cascade = CascadeType.PERSIST)
	private Set<Producto> productos;
	
	public TipoPalet(Long id, String nombre, String medidas, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.medidas = medidas;
		this.descripcion = descripcion;
	}

	public TipoPalet() {
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

	public String getMedidas() {
		return medidas;
	}

	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
