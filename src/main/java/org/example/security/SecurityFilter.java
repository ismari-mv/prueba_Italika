package org.example.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.example.usuario.UsuarioRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Value("${api.security.secret}")
    private String apiSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/usuario")) {
            String apiKey = request.getHeader("api-key");
            if (apiKey == null || !apiKey.equals(apiSecret)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "API Key inválida");
                return;
            }
        }else{
            // Obtener el token del header
            var authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                var token = authHeader.replace("Bearer ", "");
                var nombreUsuario = tokenService.getSubject(token); // extract username
                if (nombreUsuario != null) {
                    // Token valido
                    var usuario = usuarioRepository.findByNombre(nombreUsuario);
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                            usuario.getAuthorities()); // Forzamos un inicio de sesion
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

