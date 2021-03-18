package org.generation.blogpessoal2.repository;

import java.util.List;

import org.generation.blogpessoal2.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostagemRepository extends JpaRepository<Postagem , Long>{
	public List<Postagem>findAllByTituloContainingIgnoreCase(String titulo);
	

}
