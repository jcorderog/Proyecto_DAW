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
import org.springframework.web.multipart.MultipartFile;

import nuca.fabrienvaf.model.Material;
import nuca.fabrienvaf.model.Producto;
import nuca.fabrienvaf.model.TipoMaterial;
import nuca.fabrienvaf.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TipoMaterialService tipoMaterialService;

	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	public Optional<Material> findById(Long id) {
		return materialRepository.findById(id);
	}

	public Material findByName(String nombre) {
		return materialRepository.findByNombre(nombre);
	}

	public Material save(Material material) {
		return materialRepository.save(material);
	}

	public void remove(Material m) {
		if (m != null) {
			Set<Producto> productos = m.getProductos();
			for (Producto p : productos) {
				Set<Material> materiales = p.getMateriales();
				Set<Material> nm = new HashSet<Material>();

				for (Material ma : materiales) {
					if (ma.getId() != m.getId()) {
						nm.add(ma);
					}
				}
				p.setMateriales(nm);
				productoService.save(p);
			}
			materialRepository.delete(m);
		}
	}

	public Material crear(Material material, MultipartFile imagen) {
		Set<Material> materiales = new HashSet<Material>();

		TipoMaterial tipo = tipoMaterialService.findById(material.getTipoMaterial().getId()).get();
		material.setTipoMaterial(tipo);
		for (Material m : tipo.getMateriales()) {
			Material mate = materialRepository.findByNombre(m.getNombre());
			materiales.add(mate);
		}

		if (!imagen.isEmpty()) {

			//De esta manera, lo guarda en la carpeta del proyecto, si es asi, habría que recargar el proyecto cada vez que se sube una foto.
			 Path directorioImagenes = Paths.get("src//main//resources//static//ImagenesSubidas/materiales");
			 String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 
			 //De esta manera lo guarda en una carpeta local del server y no hace falta.
			
			 //String rutaAbsoluta = "/home//jorge//recursos";

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				material.setImagen(imagen.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			material.setImagen("sinImagen.png");
		}

		materiales.add(material);
		tipo.setMateriales(materiales);

		materialRepository.save(material);
		tipoMaterialService.save(tipo);
		return material;
	}

	public Material actualizarCantidad(Material m, int cantidadSumar, int cantidadRestar) {
		int cantidad = 0;
		if (cantidadSumar >= 0) {
			cantidad = m.getCantidad();
			cantidad += cantidadSumar;
		}
		if (cantidadRestar >= 0) {
			cantidad -= cantidadRestar;
		}

		if (cantidadSumar >= 0 || cantidadRestar >= 0) {
			m.setCantidad(cantidad);

			materialRepository.save(m);
		}
		return m;
	}

	public Material actualizar(Material material, MultipartFile imagen) {

		if (!imagen.isEmpty()) {

			//De esta manera, lo guarda en la carpeta del proyecto, si es asi, habría que recargar el proyecto cada vez que se sube una foto.
			 Path directorioImagenes = Paths.get("src//main//resources//static//ImagenesSubidas/materiales");
			 String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			 
			 //De esta manera lo guarda en una carpeta local del server y no hace falta.
			
			 //String rutaAbsoluta = "/home//jorge//recursos";

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				material.setImagen(imagen.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			material.setImagen("sinImagen.png");
		}
		System.out.print("Entra en service");
		materialRepository.save(material);
		return material;
	}
}
