package nuca.fabrienvaf.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import nuca.fabrienvaf.model.TipoPalet;
import nuca.fabrienvaf.model.TipoProducto;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.service.MaterialService;
import nuca.fabrienvaf.service.ProductoService;
import nuca.fabrienvaf.service.TipoPaletService;
import nuca.fabrienvaf.service.TipoProductoService;
import nuca.fabrienvaf.service.UsuarioService;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private TipoPaletService tipoPaletService;
    @Autowired
    private TipoProductoService tipoProductoService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/productos")
    public String productos(Model model){
        model.addAttribute("productoList", productoService.findAll());
        return "productos";
    }
    
    @GetMapping("/productos/{id}")
    public String producto(@PathVariable("id") Long id, Model model) {
    	int cantidadSumar = 0;
    	int cantidadRestar = 0;
    	Producto product = productoService.findById(id).get();
    	model.addAttribute("producto", product);
    	model.addAttribute("tipoProducto", product.getTipoProducto());
    	model.addAttribute("tipoPalet", product.getTipoPalet());
    	if(product.getUsuario() == null) {
    		model.addAttribute("usuario", "Usuario Vacio");
    	} else {
    		model.addAttribute("usuario", product.getUsuario().getUsername());
    	}
    	model.addAttribute("materiales", product.getMateriales());
    	return "producto";
    }
    
    @PostMapping("/productos/{id}/actualizarPalets")
    public String actualizarPalets(@PathVariable("id") Long id, @ModelAttribute Producto producto, @RequestParam("cantidadSumar") int cantidadSumar, @RequestParam("cantidadRestar") int cantidadRestar) {
		Producto p = productoService.findById(id).get();
		productoService.actualizarCantidad(p, cantidadSumar, cantidadRestar);
	
    	return "redirect:/productos/"+id;
    }
    
    @GetMapping("/productos/crear")
    public String crearProducto(Model model) {
    	model.addAttribute("producto", new Producto());
    	model.addAttribute("materiales",materialService.findAll());
    	model.addAttribute("usuarios",usuarioService.findAll());
    	model.addAttribute("tiposProductos",tipoProductoService.findAll());
    	model.addAttribute("tiposPalets", tipoPaletService.findAll());
    	return "crearProducto";
    }
    
    @PostMapping("/productos/crear/submit")
    public String crearProductoSubmit(@ModelAttribute Producto producto, @RequestParam("file") MultipartFile imagen) {
    	 
    	 productoService.crear(producto, imagen);
    	 
    	 return "redirect:/productos";
    }
    
    
   @GetMapping("/productos/modificar/{id}")
	public String actaulizar(@PathVariable("id") Long id, Model model) {
		Producto p = productoService.findById(id).get();
		model.addAttribute("producto", p);
		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("tiposProductos", tipoProductoService.findAll());
		model.addAttribute("tiposPalets", tipoPaletService.findAll());
		model.addAttribute("materiales", materialService.findAll());
		return "actualizarProducto";
	}
    
    @PostMapping("/productos/modificar/{id}/submit")
	public String actualizarSubmit(@ModelAttribute Producto producto, @RequestParam("file") MultipartFile imagen) {
    	productoService.actualizarProducto(producto, imagen);
    	return "redirect:/productos/"+producto.getId();
    }
    
   @GetMapping("/productos/eliminar/{id}")
    public String removeProducto(@PathVariable("id") Long id) {
    	productoService.removeProducto(productoService.findById(id).get());
    	return "redirect:/productos";
    }
}
