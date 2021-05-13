package nuca.fabrienvaf.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	private int cantidadPalets;
	private int cantidadBotes;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="tipoProductoId", nullable=true)
	private TipoProducto tipoProducto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="tipoPaletId", nullable=true)
	private TipoPalet tipoPalet;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="usuarioId", nullable=true)
	private Usuario usuario;
	
	@ManyToMany(mappedBy = "productos", cascade = CascadeType.PERSIST)
	private Set<Material> materiales;

	public Producto(String nombre, int cantidadPalets, int cantidadBotes, TipoProducto tipoProducto,
			TipoPalet tipoPalet, Usuario usuario, Set<Material> materiales) {
		super();
		this.nombre = nombre;
		this.cantidadPalets = cantidadPalets;
		this.cantidadBotes = cantidadBotes;
		this.tipoProducto = tipoProducto;
		this.tipoPalet = tipoPalet;
		this.usuario = usuario;
		this.materiales = materiales;
	}

	public Producto() {
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

	public int getCantidadPalets() {
		return cantidadPalets;
	}

	public void setCantidadPalets(int cantidadPalets) {
		this.cantidadPalets = cantidadPalets;
	}

	public int getCantidadBotes() {
		return cantidadBotes;
	}

	public void setCantidadBotes(int cantidadBotes) {
		this.cantidadBotes = cantidadBotes;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public TipoPalet getTipoPalet() {
		return tipoPalet;
	}

	public void setTipoPalet(TipoPalet tipoPalet) {
		this.tipoPalet = tipoPalet;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
