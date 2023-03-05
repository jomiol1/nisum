package com.nisum.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.dto.UserDTO;
import com.nisum.dto.UserResponseDTO;
import com.nisum.dto.model.Phone;
import com.nisum.service.UserService;


@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private UserService userService;
	
	UserDTO userDTO;
	Phone phone;
	List<Phone> phones;
	UserResponseDTO userResponseDTO;
	
	@BeforeEach
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
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
	@WithMockUser(username="admin",roles={"ADMIN"})
	void registryTest_OK() throws JsonProcessingException, Exception {
		
		when(userService.save(any())).thenReturn(userResponseDTO);
		
		mvc.perform(post("/user/registry").contentType(MediaType.APPLICATION_JSON).content(
				objectMapper.writeValueAsString(userDTO))).andExpect(status().isOk());
		
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void registryTest_INVALIDAD_PASSWORD() throws JsonProcessingException, Exception {
		userDTO.setPassword("123");
		
		when(userService.save(any())).thenReturn(userResponseDTO);
		
		mvc.perform(post("/user/registry").contentType(MediaType.APPLICATION_JSON).content(
				objectMapper.writeValueAsString(userDTO))).andExpect(status().isBadRequest());
		
	}
	
	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	void registryTest_INVALIDAD_EMAIL() throws JsonProcessingException, Exception {
		userDTO.setEmail("test");
		
		when(userService.save(any())).thenReturn(userResponseDTO);
		
		mvc.perform(post("/user/registry").contentType(MediaType.APPLICATION_JSON).content(
				objectMapper.writeValueAsString(userDTO))).andExpect(status().isBadRequest());
		
	}
	

}
