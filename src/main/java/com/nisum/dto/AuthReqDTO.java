package com.nisum.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.nisum.annotations.PasswordValidator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthReqDTO {
	@NotBlank
	@Email(message = "Formato de email no es valido", regexp = "^[a-z0-9+_.-]+@[a-z0-9.]+$")
	private String email;

	@NotBlank
	@PasswordValidator
	private String password;

}
