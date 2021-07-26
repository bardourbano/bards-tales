package br.com.bardourbano.bardstales.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
@SQLDelete(sql = "UPDATE videos SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "não deve estar em branco")
    private String titulo;

    @NotBlank(message = "não deve estar em branco")
    private String descricao;

    @NotBlank(message = "não deve estar em branco")
    private String url;

    @Column(insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Timestamp created_at = Timestamp.valueOf(LocalDateTime.now());

    @Column(insertable = false, updatable = false)
    private Timestamp updated_at;

    private Timestamp deleted_at;
}
