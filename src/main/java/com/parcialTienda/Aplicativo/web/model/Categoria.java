package com.parcialTienda.Aplicativo.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombreCategoria;
	private String descripcion;
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	List<Articulo> articuloList;
}
