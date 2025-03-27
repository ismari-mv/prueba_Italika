package org.example.productos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;

    public void agregarProducto(String nombre, String descripcion, Double precio, Integer cantidad) {
        productoRepository.agregarProducto(nombre, descripcion, precio, cantidad);
    }

    public void actualizarCantidadProducto(Long id, Integer cantidad) {
        productoRepository.actualizarCantidadProducto(id, cantidad);
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.obtenerProductoPorId(id);
    }
    public void eliminarProducto(Long id) {
        // se verifica el id antes de eliminarlo
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con el id: " + id);
        }
    }
}