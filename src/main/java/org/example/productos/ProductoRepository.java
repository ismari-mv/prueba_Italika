package org.example.productos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Procedure(name = "agregar_producto")
    void agregarProducto(String nombre, String descripcion, Double precio, Integer cantidad);

    @Procedure(name = "actualizar_cantidad_p")
    void actualizarCantidadProducto(Long id, Integer cantidad);

    @Procedure(name = "obtener_producto_id")
    Producto obtenerProductoPorId(Long id);
}
