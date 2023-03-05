package com.nisum.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nisum.annotations.PasswordValidator;
import com.nisum.dto.model.Phone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Integer id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email(message = "Formato de email no es valido", regexp = "^[a-z0-9+_.-]+@[a-z0-9.]+$")
	private String email;
	
	@NotBlank
	@PasswordValidator
	private String password;
	
	private String token;
	
	@NotNull
	private List<Phone> phones;

}
