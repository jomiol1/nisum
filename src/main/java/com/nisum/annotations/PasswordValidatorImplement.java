package com.nisum.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;

public class PasswordValidatorImplement implements 
ConstraintValidator<PasswordValidator, String>{
	
	@Value("${nisum.validation.password}")
	private String regexPassword;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(regexPassword);
	}

}
