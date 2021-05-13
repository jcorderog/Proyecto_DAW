package nuca.fabrienvaf.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nuca.fabrienvaf.model.Rol;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.service.RolService;
import nuca.fabrienvaf.service.UsuarioService;
import nuca.fabrienvaf.web.dto.UsuarioRegistroDto;

@Controller
@RequestMapping("/registration")
public class UsuarioRegistroController {
	
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;

	public UsuarioRegistroController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDto usuarioRegistroDto() {
		return new UsuarioRegistroDto();
	}
	
	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("allRoles", rolService.findAll());
		return "registro";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("usuario") UsuarioRegistroDto registroDto) {
		Collection<Rol> roles = new ArrayList();
		System.out.println("p1   "+registroDto.getRoles());
		Optional<Rol> r = rolService.findById(registroDto.getRoles());
		System.out.println("p2   "+r);
		roles.add(r.get());
		usuarioService.save(registroDto.getNombre(), registroDto.getApellidos(), registroDto.getUsername(), registroDto.getPassword(), registroDto.getEmail(), registroDto.getDescripcion(),
				roles);
		return "redirect:/registration?success";
	}
}
