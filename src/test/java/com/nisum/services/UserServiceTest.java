package com.nisum.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nisum.dto.UserDTO;
import com.nisum.dto.UserResponseDTO;
import com.nisum.dto.model.Phone;
import com.nisum.entity.UserEntity;
import com.nisum.handler.exception.GeneralException;
import com.nisum.repository.PhoneRepository;
import com.nisum.repository.UserRepository;
import com.nisum.service.AuthService;
import com.nisum.service.UserService;


@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private PhoneRepository phoneRepository;
	
	@MockBean
	private AuthService authService;
	
	UserDTO userDTO;
	Phone phone;
	List<Phone> phones;
	UserResponseDTO userResponseDTO;
	UserEntity userEntity;
	Optional<UserEntity> userEntityOpt;
	Optional<UserEntity> userEntityOptEmpty;
	
	
	@BeforeEach
	public void setUp() {
		userEntity = new UserEntity();
		userEntityOpt = Optional.of(userEntity);
		userEntityOptEmpty =  Optional.empty();
		 
		phone = new Phone();
		phones = new ArrayList<>();
		phone.setCitycode("1");
		phone.setCountrycode("1");
		phone.setNumber("123456");
		phones.add(phone);
		
		userDTO = new UserDTO();
		userDTO.setName("test");
		userDTO.setEmail("test@mail.com");
		userDTO.setPassword("12345");
		userDTO.setPhones(phones);
		
		userResponseDTO = new UserResponseDTO();
	}
	
	@Test
	void saveTest_OK() {
		
		when(userRepository.findByEmailAndActiveIsTrue(anyString())).thenReturn(userEntityOptEmpty);
		try {
			userService.save(userDTO);
		} catch (Exception e) {
			assertNull(e);
		}
		
	}
	
	@Test
	void saveTest_EMAIL_FOUND() {
		
		when(userRepository.findByEmailAndActiveIsTrue(anyString())).thenReturn(userEntityOpt);

		Exception exception = assertThrows(GeneralException.class, () -> {
			userService.save(userDTO);
	    });

	    assertTrue(exception.getMessage().contains("El usuario con email [test@mail.com] ya se encuentra registrado"));
		
	}
	

}
