package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.GenericResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import usuario.DatosDetalleUsuario;
import usuario.Usuario;
import usuario.UsuarioRepository;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity<GenericResponseDto> insertUsuario (@RequestBody @Valid DatosDetalleUsuario crearUsuario,
                                                             UriComponentsBuilder uriComponentsBuilder){

        Usuario usuarioGuardado = usuarioRepository.save(new Usuario(crearUsuario));
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioGuardado.getId()).toUri();

        return ResponseEntity.created(uri).body(new GenericResponseDto("Operacion Exitosa", "Perfil creado exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleUsuario> retornaDatosUsuario (@PathVariable Long id){
        Usuario usuarioConsulta = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuarioConsulta.getNombre(),usuarioConsulta.getEmail(),
                usuarioConsulta.getContrasena()));
    }
}