package com.parcialTienda.Aplicativo.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "El nombre de la categoría no puede ser nulo")
	private String nombreCategoria;
	@NotBlank(message = "La descripcion de la categoría no puede ser nulo")
	private String descripcion;
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	List<Articulo> articuloList;
}
