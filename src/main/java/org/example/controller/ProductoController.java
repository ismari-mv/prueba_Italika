package org.example.controller;


import org.example.productos.Producto;
import org.example.productos.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/productos")
public class ProductoController {


    @Autowired
    private ProductoService productoService;

    //agregar producto
    @PostMapping("/agregar")
    public void agregarProducto(@RequestBody Producto producto) {
        productoService.agregarProducto(producto.getNombre(), producto.getDescripcion(),
                producto.getPrecio(), producto.getCantidad());
    }

    // actualizar la cantidad de un producto
    @PutMapping("/actualizar/{id}")
    public void actualizarCantidadProducto(@PathVariable Long id, @RequestParam int cantidad) {
        productoService.actualizarCantidadProducto(id, cantidad);
    }

    //  obtener un producto por su ID
    @GetMapping("/obtener/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Si el producto no se encuentra
        }
    }
}
