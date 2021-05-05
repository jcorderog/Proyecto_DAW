package nuca.fabrienvaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nuca.fabrienvaf.service.UsuarioService;
import nuca.fabrienvaf.web.dto.UsuarioRegistroDto;

@Controller
@RequestMapping("/registration")
public class UsuarioRegistroController {
	
	private UsuarioService usuarioService;

	public UsuarioRegistroController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDto usuarioRegistroDto() {
		return new UsuarioRegistroDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registro";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("usuario") UsuarioRegistroDto registroDto) {
		usuarioService.save(registroDto);
		return "redirect:/registration?success";
	}
}
