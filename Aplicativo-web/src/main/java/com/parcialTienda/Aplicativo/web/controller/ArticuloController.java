package com.parcialTienda.Aplicativo.web.controller;

import com.parcialTienda.Aplicativo.web.model.Articulo;
import com.parcialTienda.Aplicativo.web.model.dto.ArticuloRequestMessage;
import com.parcialTienda.Aplicativo.web.service.ArticuloService;
import com.parcialTienda.Aplicativo.web.model.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
public class ArticuloController {
	@Autowired
	private ArticuloService articuloService;

	/* Leer */
	@GetMapping("articulo/{id}")
	public ResponseEntity<Articulo> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(articuloService.getArticuloById(id));
	}

	/* Crear */
	@PostMapping("articulo/{idCategoria}")
	public ResponseEntity<Response> create(@Validated @RequestBody ArticuloRequestMessage articuloRequestMessage, @PathVariable Long idCategoria) {
		Articulo articulo = articuloRequestMessage.getRequestMessage().getArticulo();
		Articulo createdArticulo = articuloService.createArticulo(articulo, idCategoria);
		return buildResponseEntity("Artículo creado exitosamente", HttpStatus.CREATED);
	}

	/* Actualizar */
	@PutMapping("articulo/{id}")
	public ResponseEntity<Response> update(@Validated @RequestBody ArticuloRequestMessage articuloRequestMessage, @PathVariable Long id) {
		Articulo articulo = articuloRequestMessage.getRequestMessage().getArticulo();
		Articulo updatedArticulo = articuloService.updateArticulo(articulo, id);
		return buildResponseEntity("Artículo actualizado exitosamente", HttpStatus.OK);
	}

	/* Eliminar */
	@DeleteMapping("articulo/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		articuloService.deleteArticulo(id);
		return new ResponseEntity<>("Artículo eliminado exitosamente", HttpStatus.NO_CONTENT);
	}

	/* Traer todo */
	@GetMapping("articulo")
	public ResponseEntity<List<Articulo>> findByAll() {
		return ResponseEntity.ok(articuloService.findAllArticulo());
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
