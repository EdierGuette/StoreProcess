package com.parcialTienda.Aplicativo.web.controller;

import com.parcialTienda.Aplicativo.web.model.Categoria;
import com.parcialTienda.Aplicativo.web.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.parcialTienda.Aplicativo.web.model.dto.Response;
import com.parcialTienda.Aplicativo.web.model.dto.CategoriaRequestMessage;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

	/* Leer */
	@GetMapping("categoria/{id}")
	public ResponseEntity<Categoria> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(categoriaService.getCategoriaById(id));
	}

	/* Crear */
	@PostMapping("categorias")
	public ResponseEntity<Response> create(@Validated @RequestBody CategoriaRequestMessage categoriaRequest) {
		Categoria categoria = categoriaRequest.getRequestMessage().getCategoria();
		Categoria createdCategoria = categoriaService.createCategoria(categoria);
		return buildResponseEntity("Categoría creada exitosamente", HttpStatus.CREATED);
	}

	/* Actualizar */
	@PutMapping("categoria/{id}")
	public ResponseEntity<Response> update(@Validated @RequestBody CategoriaRequestMessage categoriaRequest, @PathVariable Long id) {
		Categoria categoria = categoriaRequest.getRequestMessage().getCategoria();
		Categoria updatedCategoria = categoriaService.updateCategoria(categoria, id);
		return buildResponseEntity("Categoría actualizada exitosamente", HttpStatus.OK);
	}

	/* Eliminar */
	@DeleteMapping("categoria/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		categoriaService.deleteCategoria(id);
		return new ResponseEntity<>("Categoría eliminada exitosamente", HttpStatus.NO_CONTENT);
	}

	/* Listar todo */
	@GetMapping("categorias")
	public ResponseEntity<List<Categoria>> findAll() {
		return ResponseEntity.ok(categoriaService.findAllCategoria());
	}

	private ResponseEntity<Response> buildResponseEntity(String message, HttpStatus status) {
		Response response = Response.builder()
				.responseMessage(Response.ResponseMessage.builder()
						.date(LocalDate.now())
						.message(Collections.singletonList(message))
						.statusCode(status.value())
						.build())
				.build();
		return new ResponseEntity<>(response, status);
	}
}
