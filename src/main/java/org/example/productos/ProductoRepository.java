package org.example.productos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Procedure(procedureName = "AGREGARPRODUCTO")
    void agregarProducto(String nombre, String descripcion, Double precio, Integer cantidad);

    @Procedure(procedureName = "ACTUALIZARCANTIDADP")
    void actualizarCantidadProducto(Long id, Integer cantidad);

    @Procedure(procedureName = "OBTENERPRODUCTOID")
    Producto obtenerProductoPorId(Long id);
}
