package nuca.fabrienvaf.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import nuca.fabrienvaf.model.Material;
import nuca.fabrienvaf.model.Producto;
import nuca.fabrienvaf.model.TipoMaterial;
import nuca.fabrienvaf.model.TipoPalet;
import nuca.fabrienvaf.model.TipoProducto;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.service.MaterialService;
import nuca.fabrienvaf.service.ProductoService;
import nuca.fabrienvaf.service.TipoMaterialService;
import nuca.fabrienvaf.service.TipoPaletService;
import nuca.fabrienvaf.service.TipoProductoService;
import nuca.fabrienvaf.service.UsuarioService;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoMaterialService tipoMaterialService;

    @GetMapping("/materiales")
    public String productos(Model model){
        model.addAttribute("materialList", materialService.findAll());
        return "materiales";
    }
    
    @GetMapping("/materiales/{id}")
    public String material(@PathVariable("id") Long id, Model model) {
    	int cantidadSumar = 0;
    	int cantidadRestar = 0;
    	Material material = materialService.findById(id).get();
    	model.addAttribute("material", material);
    	model.addAttribute("tipoMaterial", material.getTipoMaterial());
    	model.addAttribute("productos", material.getProductos());
    	return "material";
    }
    
    @PostMapping("/materiales/{id}/actualizarCantidad")
    public String actualizarCantidad(@PathVariable("id") Long id, @ModelAttribute Material material, @RequestParam("cantidadSumar") int cantidadSumar, @RequestParam("cantidadRestar") int cantidadRestar) {
		Material m = materialService.findById(id).get();
		
		materialService.actualizarCantidad(m, cantidadSumar, cantidadRestar);
	
    	return "redirect:/materiales/"+id;
    }

    @GetMapping("/materiales/crear")
    public String crearProducto(Model model) {
    	model.addAttribute("material", new Material());
    	model.addAttribute("productos",productoService.findAll());
    	model.addAttribute("usuarios",usuarioService.findAll());
    	model.addAttribute("tiposMateriales", tipoMaterialService.findAll());
    	return "crearMaterial";
    }
    
    @PostMapping("/materiales/crear/submit")
    public String crearProductoSubmit(@ModelAttribute Material material, @RequestParam("file") MultipartFile imagen) {
    	
    	materialService.crear(material, imagen);
    	
    	return "redirect:/materiales";
    }
    
    @GetMapping("/materiales/eliminar/{id}")
    public String removeMaterial(@PathVariable("id") Long id) {
    	materialService.remove(materialService.findById(id).get());
    	return "redirect:/materiales";
    }
    
    @GetMapping("/materiales/modificar/{id}")
    public String actualizar (Model model, @PathVariable("id") Long id) {
    	Material m = materialService.findById(id).get();
    	model.addAttribute("material", m);
    	model.addAttribute("productos",productoService.findAll());
    	model.addAttribute("usuarios",usuarioService.findAll());
    	model.addAttribute("tiposMateriales", tipoMaterialService.findAll());
    	return "actualizarMaterial";
    }
    
    @PostMapping("/materiales/modificar/{id}/submit")
    public String actualizarSubmit (@ModelAttribute Material materialM, @RequestParam("file") MultipartFile imagen) {
    	System.out.println("Entra");
    	materialService.actualizar(materialM, imagen);
    	
    	return "redirect:/materiales/"+materialM.getId();
    }
    
    
}
