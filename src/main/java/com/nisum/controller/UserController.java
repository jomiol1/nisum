package com.nisum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.dto.UserDTO;
import com.nisum.dto.UserResponseDTO;
import com.nisum.dto.UserUpdateReq;
import com.nisum.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping(value = "/registry", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDTO> registry(@RequestBody @Valid UserDTO request){
		return new ResponseEntity<>(userService.save(request),HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateReq request){
		userService.update(request);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getById(@PathVariable Integer id){
		
		return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
