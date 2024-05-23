package com.parcialTienda.Aplicativo.web.controller;

import com.parcialTienda.Aplicativo.web.model.Articulo;
import com.parcialTienda.Aplicativo.web.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticuloController {
	@Autowired
	private ArticuloService articuloService;

	/*Leer*/
	@GetMapping("articulo/{id}")
	public ResponseEntity<Articulo> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(articuloService.getArticuloById(id));
	}

	/*Crear*/
	@PostMapping("articulo/{idCategoria}")
	public ResponseEntity<Articulo> create (@Validated @RequestBody Articulo articulo, @PathVariable Long idCategoria){
		return new ResponseEntity<>(articuloService.createArticulo(articulo, idCategoria), HttpStatus.CREATED);
	}

	/*Actualizar*/
	@PutMapping("articulo/{id}")
	public ResponseEntity<Articulo> update(@Validated @RequestBody Articulo articulo, @PathVariable Long id){
		return new ResponseEntity<>(articuloService.updateArticulo(articulo, id), HttpStatus.OK);
	}

	/*Eliminar*/
	@DeleteMapping("articulo/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id){
		return new ResponseEntity(articuloService.deleteArticulo(id),HttpStatus.NO_CONTENT);
	}

	/*Traer todo*/
	@GetMapping("articulo")
	public ResponseEntity<List<Articulo>> findByAll(){
		return ResponseEntity.ok(articuloService.findAllArticulo());
	}
}
