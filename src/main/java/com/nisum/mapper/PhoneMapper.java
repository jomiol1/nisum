package com.nisum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nisum.dto.model.Phone;
import com.nisum.entity.PhoneEntity;
import com.nisum.entity.UserEntity;

@Mapper
public interface PhoneMapper {
	
	@Mapping(source = "user", target = "user")
	PhoneEntity toPhoneEntity(Phone phone, UserEntity user);

}
