package nuca.fabrienvaf.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import nuca.fabrienvaf.model.Material;
import nuca.fabrienvaf.model.Producto;
import nuca.fabrienvaf.model.TipoPalet;
import nuca.fabrienvaf.model.TipoProducto;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MaterialService materialService;
	
    @Autowired
    private TipoPaletService tipoPaletService;
    @Autowired
    private TipoProductoService tipoProductoService;
	
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
	
	public void removeProducto(Producto p) {
		if(p != null) {
			Set<Material> materiales = p.getMateriales();
			for (Material m : materiales) {
				Set<Producto> productos = m.getProductos();
				Set<Producto> np = new HashSet<Producto>();
				for (Producto pro : productos) {
					if (pro.getId() != p.getId()) {
						np.add(pro);
					}
				}
				m.setProductos(np);
				materialService.save(m);
			}
			Usuario u = usuarioService.findById(p.getUsuario().getId());
			Set<Producto> ps = u.getProductos();
			Set<Producto> nps = new HashSet<Producto>();
			for(Producto producto : ps) {
				if(producto.getId() != p.getId()) {
					nps.add(producto);
				}
			}
			u.setProductos(nps);
			usuarioService.guardar(u);
			productoRepository.delete(p);
		}
	}
	
	public Producto crear (Producto producto, MultipartFile imagen) {
		TipoPalet tipoPalet = tipoPaletService.findByName(producto.getTipoPalet().getNombre());
		TipoProducto tipoProducto = tipoProductoService.findByName(producto.getTipoProducto().getNombre());
		Usuario usuario = usuarioService.findByUsername(producto.getUsuario().getUsername());
		Set<Material> materiales = new HashSet<Material>();
		 
		producto.setTipoPalet(tipoPalet);
		producto.setTipoProducto(tipoProducto);
		producto.setUsuario(usuario);
		 
		for(Material m : producto.getMateriales()) {
			Material material = materialService.findByName(m.getNombre());
			materiales.add(material);
			Set<Producto> productos = new HashSet<Producto>();
			productos = material.getProductos();
			productos.add(producto);
			material.setProductos(productos);
		}
		 
		producto.setMateriales(materiales);
		 
		if(!imagen.isEmpty()) {
			 
			 //De esta manera, lo guarda en la carpeta del proyecto, si es asi, habría que recargar el proyecto cada vez que se sube una foto.
			 Path directorioImagenes = Paths.get("src//main//resources//static//ImagenesSubidas/productos");
			 String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 
			 //De esta manera lo guarda en una carpeta local del server y no hace falta.
			//String rutaAbsoluta = "/home//jorge//recursos";
			 
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
						
				producto.setImagen(imagen.getOriginalFilename());
						
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			producto.setImagen("sinImagen.png");
		}
		productoRepository.save(producto);
		return producto;
	}
	
	public Producto actualizarCantidad (Producto p, int cantidadSumar, int cantidadRestar) {
		int numPalets = 0;
		int numBotes = 0;
		if (cantidadSumar >= 0 && p.getTipoPalet().getNombre().equals("Palet Normal")) {
			numPalets = p.getCantidadPalets();
			numPalets += cantidadSumar;
			numBotes = numPalets * 576;
		} else if (cantidadSumar >= 0 && p.getTipoPalet().getNombre().equals("Palet pequeño")) {
			numPalets = p.getCantidadPalets();
			numPalets += cantidadSumar;
			numBotes = numPalets * 150;
		}
		if (cantidadRestar >= 0 && p.getTipoPalet().getNombre().equals("Palet Normal")) {
			numPalets -= cantidadRestar;
			numBotes = numPalets * 576;
		} else if (cantidadRestar >= 0 && p.getTipoPalet().getNombre().equals("Palet pequeño")) {
			numPalets -= cantidadRestar;
			numBotes = numPalets * 150;
		}
		
		if (cantidadSumar >= 0 || cantidadRestar >= 0) {
			p.setCantidadPalets(numPalets);
			p.setCantidadBotes(numBotes);
			
			for (Material m : p.getMateriales()) {
				if (m.getTipoMaterial().getNombre().equals("Caja")) {
					int cantidad = cantidadSumar*72;
					m.setCantidad(cantidad);
				} else {
					int cantidad = cantidadSumar*576;
					m.setCantidad(m.getCantidad()-cantidad);
				}
			}
			
			productoRepository.save(p);
		}
		return p;
	}
	
	public Producto actualizarProducto (Producto producto, MultipartFile imagen) {
		if(!imagen.isEmpty()) {
			//De esta manera, lo guarda en la carpeta del proyecto, si es asi, habría que recargar el proyecto cada vez que se sube una foto.
			 Path directorioImagenes = Paths.get("src//main//resources//static//ImagenesSubidas/productos");
			 String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 
			 //De esta manera lo guarda en una carpeta local del server y no hace falta.
			//String rutaAbsoluta = "/home//jorge//recursos";
			 
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
						
				producto.setImagen(imagen.getOriginalFilename());
						
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			producto.setImagen("sinImagen.png");
		}
		productoRepository.save(producto);
		return producto;
	}
}
