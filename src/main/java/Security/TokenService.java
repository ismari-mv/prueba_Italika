package Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("italika")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            System.err.println("Error al generar el token: " + exception.getMessage());
            throw new RuntimeException("Error al generar el token JWT: " + exception.getMessage(), exception);
        }
    }


    public String getSubject(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("El token es nulo o está vacío.");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("italika")
                    .build()
                    .verify(token);
            String subject = verifier.getSubject();
            if (subject == null || subject.isBlank()) {
                throw new IllegalStateException("El token no contiene un sujeto válido.");
            }
            return subject;
        } catch (JWTVerificationException exception) {
            throw new IllegalArgumentException("El token no es válido.", exception);
        }
    }


    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(200).toInstant(ZoneOffset.of("-05:00"));
    }

}


