package br.com.bardourbano.bardstales.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bardourbano.bardstales.exception.CategoriaNotFoundException;
import br.com.bardourbano.bardstales.model.Categoria;
import br.com.bardourbano.bardstales.repository.CategoriaRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = {"/categorias"})
public class CategoriaController {

    private CategoriaRepository repository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.repository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> index() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Categoria> show(@PathVariable long id) {
        Categoria categoria = repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public Categoria store(@Valid @RequestBody Categoria categoria) {
        categoria = repository.save(categoria);
        return repository.findById(categoria.getId()).get();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> putUpdate(@PathVariable long id, @Valid @RequestBody Categoria categoria) {
        return repository.findById(id)
                .map(record -> {
                    record.setTitulo(categoria.getTitulo());
                    record.setCor(categoria.getCor());

                    Categoria updatedCategoria = repository.save(record);
                    updatedCategoria.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));

                    return ResponseEntity.ok().body(updatedCategoria);
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    @PatchMapping(path = {"/{id}"})
    public ResponseEntity<?> patchUpdate(@PathVariable long id, @RequestBody Categoria categoria) {
        return repository.findById(id)
                .map(record -> {
                    if (
                        categoria.getTitulo() != null &&
                        categoria.getTitulo().length() > 0 &&
                        !categoria.getTitulo().equals(record.getTitulo())
                    ) {
                        record.setTitulo(categoria.getTitulo());
                    }

                    if (
                        categoria.getCor() != null &&
                        categoria.getCor().length() > 0 &&
                        !categoria.getCor().equals(record.getCor())
                    ) {
                        record.setCor(categoria.getCor());
                    }

                    Categoria updatedCategoria = repository.save(record);
                    updatedCategoria.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));

                    return ResponseEntity.ok().body(updatedCategoria);
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);

                    return ResponseEntity.ok().body("Categoria \"" + record.getTitulo() + "\" deletada");
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }
}
