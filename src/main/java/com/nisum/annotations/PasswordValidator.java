package com.nisum.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidatorImplement.class)
@Target( { METHOD, FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Password debe tener minimo 5 caracteres, maximo 10, solo minusculas, mayusculas y numeros";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
