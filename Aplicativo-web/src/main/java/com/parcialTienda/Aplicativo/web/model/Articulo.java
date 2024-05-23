package com.parcialTienda.Aplicativo.web.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
@Entity
public class Articulo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "El nombre no puede ser nulo")
	private String nombre;
	@NotBlank(message = "La descripcion puede ser nula")
	private String descripcion;
	@NotBlank(message = "El stock no puede ser nulo")
	private String stock;
	@NotBlank(message = "El precio no puede ser nulo")
	@Min(value = 1, message = "El precio debe ser mayor a 0")
	private String precio;
	@NotNull(message = "La fecha de ingreso no puede ser nula")
	private LocalDate fechaIngreso;

	@ManyToOne
	@JoinColumn(name = "articulo_id", referencedColumnName = "id")
	private Categoria categoria;
}
