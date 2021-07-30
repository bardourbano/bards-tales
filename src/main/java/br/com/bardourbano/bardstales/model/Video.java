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
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
@SQLDelete(sql = "UPDATE videos SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Video {

    /**
     *
     */
    private static final String CAMPO_OBRIGATÓRIO = "campo obrigatório, não deve estar em branco";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = CAMPO_OBRIGATÓRIO)
    @Size(max = 30, message = "não deve exceder 30 characteres")
    private String titulo;

    @NotBlank(message = CAMPO_OBRIGATÓRIO)
    private String descricao;

    @NotBlank(message = CAMPO_OBRIGATÓRIO)
    @URL(message = "deve ser uma url")
    private String url;

    @Column(insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Timestamp created_at = Timestamp.valueOf(LocalDateTime.now());

    @Column(insertable = false, updatable = false)
    private Timestamp updated_at;

    @Column(insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Timestamp deleted_at;
}
