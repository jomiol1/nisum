package com.nisum.service;

import java.util.Base64;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.constant.Constants;
import com.nisum.dto.UserDTO;
import com.nisum.dto.UserResponseDTO;
import com.nisum.dto.UserUpdateReq;
import com.nisum.entity.UserEntity;
import com.nisum.handler.exception.GeneralException;
import com.nisum.handler.exception.UserNotFoundException;
import com.nisum.mapper.PhoneMapper;
import com.nisum.mapper.UserMapper;
import com.nisum.repository.PhoneRepository;
import com.nisum.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private AuthService authService;
	
	private UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
	private PhoneMapper phoneMapper = Mappers.getMapper(PhoneMapper.class);
	
	@Transactional
	public UserResponseDTO save(UserDTO user) {
		if(userRepository.findByEmailAndActiveIsTrue(user.getEmail()).isPresent())
			throw new GeneralException(1, String.format(Constants.USER_IS_REGISTERED, user.getEmail()));
		
		UserEntity userEntity = mapper.toUserEntity(user);
		userEntity.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		userRepository.save(userEntity);
	
		
		user.getPhones().forEach(phone->
			phoneRepository.save(phoneMapper.toPhoneEntity(phone, userEntity))
		);
		
		return mapper.toUserResponseDTO(userEntity, authService.getJWTToken(user.getEmail()));
	}
	
	public void update(UserUpdateReq user) {
		UserEntity userEntity = userRepository.findByIdAndActiveIsTrue(user.getId()).orElseThrow(
				()-> new UserNotFoundException(1, Constants.USER_NOT_FOUND));
		
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		
		userRepository.save(userEntity);
	}
	
	public UserDTO getById(Integer id) {
		UserEntity userEntity = userRepository.findByIdAndActiveIsTrue(id).orElseThrow(
				()-> new UserNotFoundException(1, Constants.USER_NOT_FOUND));
		
		return mapper.toUserDTO(userEntity);
	}
	
	public void delete(Integer id) {
		UserEntity userEntity = userRepository.findByIdAndActiveIsTrue(id).orElseThrow(
				()-> new UserNotFoundException(1, Constants.USER_NOT_FOUND));
		
		userEntity.setActive(Boolean.FALSE);
		userRepository.save(userEntity);
	}

}
