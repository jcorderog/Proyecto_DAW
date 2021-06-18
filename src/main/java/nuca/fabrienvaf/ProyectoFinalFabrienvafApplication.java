package nuca.fabrienvaf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nuca.fabrienvaf.model.Material;
import nuca.fabrienvaf.model.Producto;
import nuca.fabrienvaf.model.Rol;
import nuca.fabrienvaf.model.TipoMaterial;
import nuca.fabrienvaf.model.TipoPalet;
import nuca.fabrienvaf.model.TipoProducto;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.repository.MaterialRepository;
import nuca.fabrienvaf.repository.ProductoRepository;
import nuca.fabrienvaf.repository.RolRepository;
import nuca.fabrienvaf.repository.TipoMaterialRepository;
import nuca.fabrienvaf.repository.TipoPaletRepository;
import nuca.fabrienvaf.repository.TipoProductoRepository;
import nuca.fabrienvaf.repository.UsuarioRepository;

@SpringBootApplication
public class ProyectoFinalFabrienvafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalFabrienvafApplication.class, args);
	}
	
	/*@Bean
	CommandLineRunner initData(	ProductoRepository productoRepository, 
								MaterialRepository materialRepository,
								UsuarioRepository usuarioRepository,
								TipoProductoRepository tipoProductoRepository,
								TipoPaletRepository tipoPaletRepository,
								TipoMaterialRepository tipoMaterialRepository,
								RolRepository rolRepository) {
		return (args) -> {
						
			TipoPalet paletNormal = new TipoPalet();
			paletNormal.setNombre("Palet Normal");
			paletNormal.setMedidas("220x180 cm");
			paletNormal.setDescripcion("Este es el tipo de palet normal, que se usa para la mayoría de clientes.");
			
			TipoPalet paletPequeño = new TipoPalet();
			paletPequeño.setNombre("Palet pequeño");
			paletPequeño.setMedidas("100x70 cm");
			paletPequeño.setDescripcion("Este es el tipo de palet pequeño, que se usa para Aldi.");
			
			tipoPaletRepository.save(paletNormal);
			tipoPaletRepository.save(paletPequeño);
			
			TipoProducto desengrasante = new TipoProducto();
			desengrasante.setNombre("Desengrasante");
			desengrasante.setDescripcion("Este es el tipo para los productos de quitar grasa.");
			
			TipoProducto limpiaSuelo = new TipoProducto();
			limpiaSuelo.setNombre("Limpia Suelos");
			limpiaSuelo.setDescripcion("Este es el tipo para los productos limpia suelos.");
			
			TipoProducto oxigenadorRopa = new TipoProducto();
			oxigenadorRopa.setNombre("Oxigeno Activo Ropa");
			oxigenadorRopa.setDescripcion("Este es el tipo para los productos de oxigeno activo para la ropa.");
			
			TipoProducto limpiaHornos = new TipoProducto();
			limpiaHornos.setNombre("Limpia Hornos");
			limpiaHornos.setDescripcion("Este es el tipo para los productos de limpia hornos.");
			
			TipoProducto limpiaCachimbas = new TipoProducto();
			limpiaCachimbas.setNombre("Limpia Cachimbas");
			limpiaCachimbas.setDescripcion("Este es el tipo para los productos de limpia cachimbas.");
			
			tipoProductoRepository.save(desengrasante);
			tipoProductoRepository.save(limpiaSuelo);
			tipoProductoRepository.save(oxigenadorRopa);
			tipoProductoRepository.save(limpiaHornos);
			tipoProductoRepository.save(limpiaCachimbas);
			
			TipoMaterial tapon = new TipoMaterial();
			tapon.setNombre("Tapon");
			tapon.setDescripcion("Tapones");
			
			TipoMaterial spray = new TipoMaterial();
			spray.setNombre("Pistola Spray");
			spray.setDescripcion("Pistola Spray.");
			
			TipoMaterial bote = new TipoMaterial();
			bote.setNombre("Bote");
			bote.setDescripcion("Botes.");
			
			TipoMaterial garrafa = new TipoMaterial();
			garrafa.setNombre("Garrafa");
			garrafa.setDescripcion("Garrafas.");
			
			TipoMaterial botella = new TipoMaterial();
			botella.setNombre("Botella");
			botella.setDescripcion("Botellas.");
			
			
			TipoMaterial tapadera = new TipoMaterial();
			tapadera.setNombre("Tapadera");
			tapadera.setDescripcion("Tapaderas.");
			
			TipoMaterial etiqueta = new TipoMaterial();
			etiqueta.setNombre("Etiqueta");
			etiqueta.setDescripcion("Etiquetas.");
			
			TipoMaterial caja = new TipoMaterial();
			caja.setNombre("Caja");
			caja.setDescripcion("Cajas.");
			
			tipoMaterialRepository.save(tapon);
			tipoMaterialRepository.save(spray);
			tipoMaterialRepository.save(bote);
			tipoMaterialRepository.save(garrafa);
			
			tipoMaterialRepository.save(tapadera);
			tipoMaterialRepository.save(etiqueta);
			tipoMaterialRepository.save(caja);
			
			Material botella1lMilagrito = new Material();
			botella1lMilagrito.setNombre("Botella 1L El Milagrito");
			botella1lMilagrito.setCantidad(0);
			botella1lMilagrito.setDescripcion("Botella de 1 Litro para El Milagrito.");
			botella1lMilagrito.setTipoMaterial(botella);
			
			Set<Material> materiales = new HashSet<Material>();
			materiales.add(botella1lMilagrito);
			botella.setMateriales(materiales);
			
			tipoMaterialRepository.save(botella);
			
			materialRepository.save(botella1lMilagrito);
			
			Rol admin = new Rol();
			admin.setName("Administrador");
			admin.setDescripcion("Usuario administrador");
			
			rolRepository.save(admin);
		};
	}*/
}
