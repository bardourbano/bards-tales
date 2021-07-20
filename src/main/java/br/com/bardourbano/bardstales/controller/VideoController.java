package br.com.bardourbano.bardstales.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bardourbano.bardstales.model.Video;
import br.com.bardourbano.bardstales.repository.VideoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping({"/videos"})
public class VideoController {

    private VideoRepository repository;

    public VideoController(VideoRepository videoRepository) {
        this.repository = videoRepository;
    }

    @GetMapping
    public List<Video> index() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Video> show(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Video store(@RequestBody Video video) {
        return repository.save(video);
    }

    /**
     * @TODO rever map para caso de parâmetro faltando.
     * @param id
     * @param video
     * @return
     */
    @PutMapping(path = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putUpdate(@PathVariable long id, @RequestBody Video video) {
        return repository.findById(id)
                .map(record -> {
                    record.setTitulo(video.getTitulo());
                    record.setDescricao(video.getDescricao());
                    record.setUrl(video.getUrl());

                    Video updatedVideo = repository.save(record);

                    return ResponseEntity.ok().body(updatedVideo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(path = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchUpdate(@PathVariable long id, @RequestBody Video video) {
        return repository.findById(id)
                .map(record -> {
                    if (!(video.getTitulo().isEmpty() || video.getTitulo() == record.getTitulo())) {
                        record.setTitulo(video.getTitulo());
                    }

                    if (!(video.getDescricao().isEmpty() || video.getDescricao() == record.getDescricao())) {
                        record.setDescricao(video.getDescricao());
                    }

                    if (!(video.getUrl().isEmpty() || video.getUrl() == record.getUrl())) {
                        record.setUrl(video.getUrl());
                    }

                    Video updatedVideo = repository.save(record);

                    return ResponseEntity.ok().body(updatedVideo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);

                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
