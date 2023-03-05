package com.nisum.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByEmailAndPassword(String email, String pwd);
	
	Optional<UserEntity> findByEmailAndActiveIsTrue(String email);
	
	Optional<UserEntity> findByIdAndActiveIsTrue(Integer id);

}
