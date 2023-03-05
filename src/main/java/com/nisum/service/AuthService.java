package com.nisum.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.nisum.constant.Constants;
import com.nisum.dto.AuthReqDTO;
import com.nisum.dto.AuthResDTO;
import com.nisum.entity.UserEntity;
import com.nisum.handler.exception.GeneralException;
import com.nisum.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
    private UserRepository usuarioRepository;

    public AuthResDTO login(AuthReqDTO request) {
    	String pwdEncode = Base64.getEncoder().encodeToString(request.getPassword().getBytes());
    	
    	UserEntity usuarioEntity= usuarioRepository.findByEmailAndPassword(request.getEmail(), pwdEncode).orElseThrow(
    			()-> new GeneralException(1, Constants.LOGIN_FAILED));
    	
    	usuarioEntity.setLastLogin(new Date());
    	usuarioRepository.save(usuarioEntity);
    	
    	return  AuthResDTO.builder().token(this.getJWTToken(usuarioEntity.getEmail())).build();

    }
    
	public String getJWTToken(String email) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("tenpoJWT").setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
