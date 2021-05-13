package nuca.fabrienvaf.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nuca.fabrienvaf.model.Rol;
import nuca.fabrienvaf.model.Usuario;
import nuca.fabrienvaf.repository.UsuarioRepository;
import nuca.fabrienvaf.web.dto.UsuarioRegistroDto;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario save(String nombre, String apellidos, String username, String password, String email, String descripcion,
			Collection<Rol> roles) {
		System.out.println(roles.toString());
		Usuario user = new Usuario(nombre, apellidos, username,
				passwordEncoder.encode(password), email, descripcion, roles);
		System.out.println(user.getRoles().toString());
		return usuarioRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		System.out.println(user.getUsername()+", "+user.getPassword()+", "+user.getEmail());
		if(user == null) {
			throw new UsernameNotFoundException("Usuario o contraseña invalida.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Rol> roles){
		
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
}
