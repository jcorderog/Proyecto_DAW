package nuca.fabrienvaf.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.web.dto.UsuarioRegistroDto;

public interface UsuarioService extends UserDetailsService{
	Usuario save(UsuarioRegistroDto registroDto);
}
