package com.parcialTienda.Aplicativo.web.controller;

import com.parcialTienda.Aplicativo.web.model.Categoria;
import com.parcialTienda.Aplicativo.web.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

	/*Leer*/
	@GetMapping("categoria/{id}")
	public ResponseEntity<Categoria> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(categoriaService.getCategoriaById(id));
	}

	/*Crear*/
	@PostMapping("categorias")
	public ResponseEntity<Categoria> create (@Validated @RequestBody Categoria categoria){
		return new ResponseEntity<>(categoriaService.createCategoria(categoria), HttpStatus.CREATED);
	}

	/*Actualizar*/
	@PutMapping("categoria/{id}")
	public ResponseEntity<Categoria> update(@Validated @RequestBody Categoria categoria, @PathVariable Long id){
		return new ResponseEntity<>(categoriaService.updateCategoria(categoria, id), HttpStatus.OK);
	}

	/*Eliminar*/
	@DeleteMapping("categoria/{id}")
	public  ResponseEntity<String> delete(@PathVariable Long id){
		return new ResponseEntity(categoriaService.deleteCategoria(id), HttpStatus.NO_CONTENT);
	}

	/*Listar todo*/
	@GetMapping("categorias")
	public  ResponseEntity<List<Categoria>> findAll(){
		return ResponseEntity.ok(categoriaService.findAllCategoria());
	}

}
