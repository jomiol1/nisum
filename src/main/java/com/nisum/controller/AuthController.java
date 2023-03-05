package com.nisum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.dto.AuthReqDTO;
import com.nisum.dto.AuthResDTO;
import com.nisum.service.AuthService;


@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthResDTO> login(@RequestBody @Valid AuthReqDTO request){
		
		return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
		
	}
	

}
