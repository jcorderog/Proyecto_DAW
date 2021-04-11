package nuca.fabrienvaf.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.web.dto.RegistroUsuarioDto;

public interface UsuarioService extends UserDetailsService{
	
	Usuario save(RegistroUsuarioDto registrarDto);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	
}
