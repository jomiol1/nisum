package com.nisum.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateReq {
	
	@NotNull
	private Integer id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email(message = "Formato de email no es valido", regexp = "^[a-z0-9+_.-]+@[a-z0-9.]+$")
	private String email;

}
