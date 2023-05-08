/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 *
 * @author Paulina Cortez Alamilla.
 */
@Path("auth")
public class AuthService {

    private static final String SECRET_KEY = "your-secret-key"; // Clave secreta utilizada para firmar los tokens JWT

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        // Verificar las credenciales del usuario aquí.
        // Si las credenciales son válidas, generar un token JWT
        if (isValidCredentials(username, password)) {
            String token = generateToken(username);
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Lógica para verificar las credenciales del usuario.
        // Devuelve true si las credenciales son válidas y false en caso contrario
        return "admin".equals(username) && "password".equals(password);
    }

    private String generateToken(String username) {
        // Generar un token JWT con el nombre de usuario como reclamación
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) // 15 minutos de duración del token
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);

        return builder.compact();
    }
}
