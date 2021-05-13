package nuca.fabrienvaf.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import nuca.fabrienvaf.model.Rol;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.web.dto.UsuarioRegistroDto;

public interface UsuarioService extends UserDetailsService{
	Usuario save(String nombre, String apellidos, String username, String password, String email, String descripcion,
			Collection<Rol> roles);
	List<Usuario> findAll();
}
