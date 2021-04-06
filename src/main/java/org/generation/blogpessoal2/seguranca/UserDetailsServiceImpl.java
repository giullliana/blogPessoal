package org.generation.blogpessoal2.seguranca;

import org.generation.blogpessoal2.model.Usuario;
import org.generation.blogpessoal2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sun.el.stream.Optional;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
private  UsuarioRepository userRepository;

@Override
public UserDetailsImpl loadUserByUsername(String userName) {
	java.util.Optional<Usuario> user =userRepository.findByUsuario(userName);
	user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));
	
	return user.map(UserDetailsImpl:: new).get();
}
}
