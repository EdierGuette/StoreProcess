package com.parcialTienda.Aplicativo.web.service;

import com.parcialTienda.Aplicativo.web.model.Categoria;
import com.parcialTienda.Aplicativo.web.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria created = categoriaService.createCategoria(categoria);

        assertNotNull(created);
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void getCategoriaById() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria found = categoriaService.getCategoriaById(1L);

        assertNotNull(found);
        verify(categoriaRepository).findById(1L);
    }

    @Test
    void updateCategoria() {
        Categoria categoria = new Categoria();
        Categoria updatedCategoria = new Categoria();
        updatedCategoria.setNombreCategoria("Updated Name");
        updatedCategoria.setDescripcion("Updated Description");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria updated = categoriaService.updateCategoria(updatedCategoria, 1L);

        assertNotNull(updated);
        assertEquals("Updated Name", categoria.getNombreCategoria());
        assertEquals("Updated Description", categoria.getDescripcion());
        verify(categoriaRepository).findById(1L);
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void deleteCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        doNothing().when(categoriaRepository).delete(categoria);

        boolean result = categoriaService.deleteCategoria(1L);

        assertTrue(result);
        verify(categoriaRepository).findById(1L);
        verify(categoriaRepository).delete(categoria);
    }

    @Test
    void findAllCategoria() {
        categoriaService.findAllCategoria();
        verify(categoriaRepository).findAll();
    }
}