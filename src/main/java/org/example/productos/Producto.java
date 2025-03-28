package org.example.productos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private  String descripcion;

    private Double precio;

    private int cantidad;

}
