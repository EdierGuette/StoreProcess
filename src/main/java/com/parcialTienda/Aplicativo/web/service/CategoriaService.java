package com.parcialTienda.Aplicativo.web.service;

import com.parcialTienda.Aplicativo.web.model.Categoria;
import com.parcialTienda.Aplicativo.web.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	/*Crear*/
	public Categoria createCategoria(Categoria categoriaReq){
		return categoriaRepository.save(categoriaReq);
	}

	/*Listar por id*/
	public Categoria getCategoriaById(Long id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.get();
	}

	/*Actualizar*/
	public Categoria updateCategoria(Categoria categoriaReq, Long id){
		Optional<Categoria> categoriaBd= categoriaRepository.findById(id);
		categoriaBd.get().setNombreCategoria(categoriaReq.getNombreCategoria());
		categoriaBd.get().setDescripcion(categoriaReq.getDescripcion());
		return categoriaRepository.save(categoriaBd.get());
	}

	/*Eliminar*/
	public boolean deleteCategoria(Long id){
		Optional<Categoria> categoriaBd= categoriaRepository.findById(id);
		categoriaRepository.delete(categoriaBd.get());
		return true;
	}

	/*Listar todo*/
	public List<Categoria> findAllCategoria(){
		return (List<Categoria>) categoriaRepository.findAll();
	}
}
