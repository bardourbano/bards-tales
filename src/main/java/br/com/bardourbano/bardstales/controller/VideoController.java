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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bardourbano.bardstales.exception.VideoNotFoundException;
import br.com.bardourbano.bardstales.model.Video;
import br.com.bardourbano.bardstales.repository.VideoRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = {"/videos"})
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
        Video video = repository.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
        return ResponseEntity.ok(video);
    }

    @PostMapping
    public Video store(@Valid @RequestBody Video video) {
        video = repository.save(video);
        return repository.findById(video.getId()).get();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> putUpdate(@PathVariable long id, @Valid @RequestBody Video video) {
        return repository.findById(id)
                .map(record -> {
                    record.setTitulo(video.getTitulo());
                    record.setDescricao(video.getDescricao());
                    record.setUrl(video.getUrl());

                    Video updatedVideo = repository.save(record);
                    updatedVideo.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));

                    return ResponseEntity.ok().body(updatedVideo);
                })
                .orElseThrow(() -> new VideoNotFoundException(id));
    }

    @PatchMapping(path = {"/{id}"})
    public ResponseEntity<?> patchUpdate(@PathVariable long id, @RequestBody Video video) {
        return repository.findById(id)
                .map(record -> {
                    if (
                        video.getTitulo() != null &&
                        video.getTitulo().length() > 0 &&
                        !video.getTitulo().equals(record.getTitulo())
                    ) {
                        record.setTitulo(video.getTitulo());
                    }

                    if (
                        video.getDescricao() != null &&
                        video.getDescricao().length() > 0 &&
                        !video.getDescricao().equals(record.getDescricao())
                    ) {
                        record.setDescricao(video.getDescricao());
                    }

                    if (
                        video.getUrl() != null &&
                        video.getUrl().length() > 0 &&
                        !video.getUrl().equals(record.getUrl())
                    ) {
                        record.setUrl(video.getUrl());
                    }

                    Video updatedVideo = repository.save(record);
                    updatedVideo.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));

                    return ResponseEntity.ok().body(updatedVideo);
                })
                .orElseThrow(() -> new VideoNotFoundException(id));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);

                    return ResponseEntity.ok().body("Video \"" + record.getTitulo() + "\" deletado");
                })
                .orElseThrow(() -> new VideoNotFoundException(id));
    }

}
