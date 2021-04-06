package org.generation.blogpessoal2.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogpessoal2.model.UserLogin;
import org.generation.blogpessoal2.model.Usuario;
import org.generation.blogpessoal2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
 @Autowired
 private UsuarioRepository repository;
 
 public Usuario CadastrarUsuario(Usuario usuario) {
	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	 
	 String senhaEncoder = encoder.encode(usuario.getSenha());
	 usuario.setSenha(senhaEncoder);
	 
	 return repository.save(usuario);
 }
 public Optional<UserLogin> Logar(Optional<UserLogin>user){
	 BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
	 Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
	 
	 if(usuario.isPresent()) {
		 if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
		 
		 String auth = user.get().getUsuario() + ":" + user.get().getSenha();
		 byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		 String authHeader = "Basic" + new String (encodeAuth);
		 
		
		 user.get().setToken(authHeader);
		 user.get().setNome(usuario.get().getNome());
		 user.get().setSenha(usuario.get().getSenha());
		 
		 return user;
	 }
 }
 return null;
}
}

