package com.nuvu.users.service.Imp;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.nuvu.users.model.User;
import com.nuvu.users.service.ITokenService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class TokenService implements ITokenService {
	
	@Value("${jwt.secret}")
	private String secret;

	@Override
	public String generateToken(Optional<User> user, List<GrantedAuthority> grantedAuthorities) {
		String jwt = "";
		Instant now = Instant.now();
		
		Map<String, Object> headerClaims = new HashMap<String, Object>();
		headerClaims.put("role", user.get().getRoleId());

		jwt = Jwts.builder().setSubject(String.valueOf(user.get().getIdentification()))
				.claim("claims", headerClaims)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
				.signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(secret)).compact();
		return jwt;
	}

}
