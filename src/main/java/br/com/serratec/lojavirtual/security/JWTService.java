package br.com.serratec.lojavirtual.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.serratec.lojavirtual.model.cliente.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {

	
	private static final String chavePrivadaJWT = "serratec"; 
	
	
	public String gerarToken(Authentication authentication) {
		
		int tempoExpiracao = 86400000;
		
		
		Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
		
		Cliente usuario =  (Cliente) authentication.getPrincipal();
		
		
		return Jwts.builder()
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS512, chavePrivadaJWT)
				.compact();
	}
	

	public Optional<Long> obterIdDoUsuario(String token){
		try {
			
			Claims claims = parse(token).getBody();
		
			return Optional.ofNullable(Long.parseLong(claims.getSubject()));
						
		} catch (Exception e) {
			
			return Optional.empty();
		}
	}
	

	private Jws<Claims> parse(String token){
		return Jwts.parser().setSigningKey(chavePrivadaJWT).parseClaimsJws(token);
	}
	
	
	
}
