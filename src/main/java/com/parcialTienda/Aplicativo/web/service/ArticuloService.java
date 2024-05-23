package com.parcialTienda.Aplicativo.web.service;

import com.parcialTienda.Aplicativo.web.model.Articulo;
import com.parcialTienda.Aplicativo.web.model.Categoria;
import com.parcialTienda.Aplicativo.web.repository.ArticuloRepository;
import com.parcialTienda.Aplicativo.web.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {
	@Autowired
	private ArticuloRepository articuloRepository;

	@Autowired
	private CategoriaService categoriaService;

	/*Crear*/
	public Articulo createArticulo(Articulo articuloReq, Long idCategoria){
		Categoria categoria = categoriaService.getCategoriaById(idCategoria);
		articuloReq.setCategoria(categoria);
		return articuloRepository.save(articuloReq);
	}

	/*Buscar por id*/
	public Articulo getArticuloById(Long id){
		Optional<Articulo> articulo = articuloRepository.findById(id);
		return articulo.get();
	}

	/*Actualizar articulo*/
	public Articulo updateArticulo(Articulo articuloReq, Long id){
		Optional<Articulo> articuloBd = articuloRepository.findById(id);
		articuloBd.get().setNombre(articuloReq.getNombre());
		articuloBd.get().setDescripcion(articuloReq.getDescripcion());
		articuloBd.get().setStock(articuloReq.getStock());
		articuloBd.get().setPrecio(articuloReq.getPrecio());
		articuloBd.get().setFechaIngreso(articuloReq.getFechaIngreso());
		return articuloRepository.save(articuloBd.get());
	}

	/*Eliminar*/
	public boolean  deleteArticulo(Long id){
		Optional<Articulo> articuloBd = articuloRepository.findById(id);
		articuloRepository.delete(articuloBd.get());
		return true;
	}

	/*Traer todos los datos*/
	public List<Articulo> findAllArticulo(){
		return (List<Articulo>) articuloRepository.findAll();
	}
}
