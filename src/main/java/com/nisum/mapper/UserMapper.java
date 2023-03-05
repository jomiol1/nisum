package com.nisum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nisum.dto.UserDTO;
import com.nisum.dto.UserResponseDTO;
import com.nisum.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	@Mapping(target = "token", source = "token")
	UserResponseDTO toUserResponseDTO(UserEntity userEntity, String token);
	
	UserDTO toUserDTO(UserEntity userEntity);
	
	
	@Mapping(target = "phones", ignore = true)
	@Mapping(target = "active", source="token", defaultValue = "true")
	UserEntity toUserEntity(UserDTO userDTO);

}
