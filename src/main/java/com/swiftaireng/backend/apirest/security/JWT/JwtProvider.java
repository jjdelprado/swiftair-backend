package com.swiftaireng.backend.apirest.security.JWT;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import io.jsonwebtoken.*;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.swiftaireng.backend.apirest.security.UsuarioPrincipal;


@Component
public class JwtProvider {

	private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
    private int expiration;
	
	public String generateToken(Authentication authentication) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("tokenformado " +e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("tokenoportado " +e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("tokenrado " +e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("tokeno " +e.getMessage());
        } catch (SignatureException e) {
            logger.error("errora firma " +e.getMessage());
        }
        return false;
    }
}
