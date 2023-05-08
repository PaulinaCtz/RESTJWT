/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filtros;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * @author Paulina Cortez Alamilla.
 */
@Provider
public class JwtFilter implements ContainerRequestFilter {

    private static final String SECRET_KEY = "your-secret-key"; // Clave secreta utilizada para firmar los tokens JWT
    private static final String AUTH_HEADER = "Authorization";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        if (path.contains("login")) {
            // Permitir la solicitud de inicio de sesión sin verificación de token
            return;
        }

        String token = requestContext.getHeaderString(AUTH_HEADER);
        if (token == null || !token.startsWith("Bearer ")) {
            // Si no se proporciona un token o el encabezado no comienza con "Bearer ", enviar una respuesta de error
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        token = token.substring("Bearer ".length()).trim();

        try {
            // Verificar y validar el token JWT
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            // Agregar las reclamaciones del token a la solicitud para que puedan ser utilizadas por los recursos protegidos
            requestContext.setProperty("jwt.claims", claims.getBody());
        } catch (SignatureException | MalformedJwtException e) {
            // Si el token no es válido o está mal formado, enviar una respuesta de error
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}