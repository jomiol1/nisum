package com.nisum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.entity.PhoneEntity;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneEntity, Integer>{

}
