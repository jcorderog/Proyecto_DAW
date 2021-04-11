package nuca.fabrienvaf.service;

import java.util.Arrays;
import java.util.Collection;
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
import nuca.fabrienvaf.web.dto.RegistroUsuarioDto;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private BCryptPasswordEncoder passwordEncorder;
	
	private UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario save(RegistroUsuarioDto registrarDto) {
		Usuario user = new Usuario(registrarDto.getNombre(),registrarDto.getApellidos(),
						registrarDto.getUsername(), registrarDto.getEmail() , 
						passwordEncorder.encode(registrarDto.getPassword()), registrarDto.getDescripcion(), 
						Arrays.asList(new Rol("ROLE_USER", "Rol Usuario")));
		
		return usuarioRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = usuarioRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}


	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Rol> roles){		
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
	
	}
	
}
