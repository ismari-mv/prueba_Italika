package usuario;

public record DatosDetalleUsuario(
        String nombre,
        String email,
        String contrasena
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getContrasena()
        );
    }
}
