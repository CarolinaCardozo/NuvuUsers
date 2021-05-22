package com.nuvu.users.security;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuvu.users.enums.ErrorEnum;
import com.nuvu.users.enums.RolesEnum;
import com.nuvu.users.exceptions.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.TextCodec;

public class JWTValidator {

	
	private static String secret = "a6e21884876f43819523c31033fa697e";
	private static final String CUSTOM_CLAIMS = "claims";
	private static final String ROLE = "role";
	private final static String PREFIX = "Bearer ";

	private static Claims validateToken(String token)  {
		String onlyToken = token.replace(PREFIX, "");	
		return Jwts
				.parser()
				.setSigningKey(TextCodec.BASE64.encode(secret))
				.parseClaimsJws(onlyToken)
				.getBody();
	}
	
	public static void validateRoleAdmin(String token)  {
		String roleName=RolesEnum.USER_ROLE.getRoleName();
		Claims claims = validateToken(token);
		System.out.println(claims);
		Object customClaims = claims.get(CUSTOM_CLAIMS);
		TypeReference<HashMap<String, Object>> typeRefMap = new TypeReference<HashMap<String, Object>>() {};
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> claimsAsMap = mapper.convertValue(customClaims, typeRefMap);
		int roleId = Integer.parseInt(claimsAsMap.get(ROLE).toString());
		System.out.println("rol:" + roleId);
		for(RolesEnum role : RolesEnum.values()) {
			if(role.getRoleId() == roleId) {
				roleName = role.name();
			}
		}

		if(!RolesEnum.valueOf(roleName).equals(RolesEnum.ADMIN)){
			throw new CustomException(HttpStatus.FORBIDDEN, ErrorEnum.FORBIDDEN);
		}
	}
}
