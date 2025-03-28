package org.example.usuario;

public record DatosAutenticacionUsuario(String nombre,String contrasena) {


    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }
}
