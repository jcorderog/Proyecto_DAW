package nuca.fabrienvaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nuca.fabrienvaf.service.UsuarioService;
import nuca.fabrienvaf.web.dto.RegistroUsuarioDto;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
	
	private UsuarioService usuarioService;

	public RegistroUsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public RegistroUsuarioDto registroUsuarioDto () {
		return new RegistroUsuarioDto();
	}
	
	@GetMapping()
	public String regitroFormulario() {
		return "register";
	}
	
	@PostMapping()
	public String registrarUsuario(@ModelAttribute("usuario") RegistroUsuarioDto registroUsuarioDto) {
		
		usuarioService.save(registroUsuarioDto);
		return "redirect:/register?success";
		
	}
}
