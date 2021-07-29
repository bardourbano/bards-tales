package br.com.bardourbano.bardstales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bardourbano.bardstales.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {}
