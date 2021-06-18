package nuca.fabrienvaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nuca.fabrienvaf.model.Producto;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.service.RolService;
import nuca.fabrienvaf.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		
		model.addAttribute("usuarioList", usuarioService.findAll());
		return "usuarios";
	}
	
	@GetMapping("/usuarios/eliminar/{username}")
	public String remove(@PathVariable("username") String username) {
		usuarioService.eliminarUsuario(usuarioService.findByUsername(username));
		
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/modificar/{username}")
	public String actaulizar(@PathVariable("username") String username, Model model) {
		Usuario u = usuarioService.findByUsername(username);
		model.addAttribute("usuario", u);
		return "actualizarUsuario";
	}
	
	@PostMapping("/usuarios/modificar/{username}/submit")
	public String actualizarSubmit (@PathVariable("username") String username, 
									@RequestParam("name") String nombre, @RequestParam("lastname") String apellidos,
									@RequestParam("password") String password, @RequestParam("correo") String correo,
									@RequestParam("descripcion") String descripcion, @RequestParam("username") String un) {
		Usuario u = usuarioService.findByUsername(username);
		usuarioService.actualizar(u, nombre, apellidos, password, correo, descripcion, un);
		
		return "redirect:/usuarios";
	}
	
}