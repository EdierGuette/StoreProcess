package com.parcialTienda.Aplicativo.web.service;

import com.parcialTienda.Aplicativo.web.model.Articulo;
import com.parcialTienda.Aplicativo.web.model.Categoria;
import com.parcialTienda.Aplicativo.web.repository.ArticuloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticuloServiceTest {
    @Mock
    private ArticuloRepository articuloRepository;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private ArticuloService articuloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createArticulo() {
        Articulo articulo = new Articulo();
        Categoria categoria = new Categoria();

        when(categoriaService.getCategoriaById(1L)).thenReturn(categoria);
        when(articuloRepository.save(articulo)).thenReturn(articulo);

        Articulo created = articuloService.createArticulo(articulo, 1L);

        assertNotNull(created);
        verify(categoriaService).getCategoriaById(1L);
        verify(articuloRepository).save(articulo);
    }

    @Test
    void getArticuloById() {
        Articulo articulo = new Articulo();
        when(articuloRepository.findById(1L)).thenReturn(Optional.of(articulo));

        Articulo found = articuloService.getArticuloById(1L);

        assertNotNull(found);
        verify(articuloRepository).findById(1L);
    }

    @Test
    void updateArticulo() {
        Articulo articulo = new Articulo();
        Articulo updatedArticulo = new Articulo();
        updatedArticulo.setNombre("Updated Name");
        updatedArticulo.setDescripcion("Updated Description");
        updatedArticulo.setStock("Updated Stock");
        updatedArticulo.setPrecio("Updated Price");
        updatedArticulo.setFechaIngreso(LocalDate.now());

        when(articuloRepository.findById(1L)).thenReturn(Optional.of(articulo));
        when(articuloRepository.save(articulo)).thenReturn(articulo);

        Articulo updated = articuloService.updateArticulo(updatedArticulo, 1L);

        assertNotNull(updated);
        assertEquals("Updated Name", articulo.getNombre());
        assertEquals("Updated Description", articulo.getDescripcion());
        assertEquals("Updated Stock", articulo.getStock());
        assertEquals("Updated Price", articulo.getPrecio());
        assertEquals(updatedArticulo.getFechaIngreso(), articulo.getFechaIngreso());
        verify(articuloRepository).findById(1L);
        verify(articuloRepository).save(articulo);
    }

    @Test
    void deleteArticulo() {
        Articulo articulo = new Articulo();
        when(articuloRepository.findById(1L)).thenReturn(Optional.of(articulo));
        doNothing().when(articuloRepository).delete(articulo);

        boolean result = articuloService.deleteArticulo(1L);

        assertTrue(result);
        verify(articuloRepository).findById(1L);
        verify(articuloRepository).delete(articulo);
    }

    @Test
    void findAllArticulo() {
        articuloService.findAllArticulo();
        verify(articuloRepository).findAll();
    }
}