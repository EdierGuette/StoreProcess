package com.parcialTienda.Aplicativo.web.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Articulo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private String stock;
	private String precio;
	private LocalDate fechaIngreso;

	@ManyToOne
	@JoinColumn(name = "articulo_id", referencedColumnName = "id")
	private Categoria categoria;
}
