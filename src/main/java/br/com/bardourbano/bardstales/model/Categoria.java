package br.com.bardourbano.bardstales.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categorias")
public class Categoria {

    private static final String CAMPO_OBRIGATÓRIO = "campo obrigatório, deve ser preenchido";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = CAMPO_OBRIGATÓRIO)
    private String titulo;

    @NotBlank(message = CAMPO_OBRIGATÓRIO)
    @Size(max = 29, message = "não deve exceder 29 characteres")
    private String cor;

    @Column(insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Timestamp created_at;

    @Column(insertable = false, updatable = false)
    private Timestamp updated_at;
}