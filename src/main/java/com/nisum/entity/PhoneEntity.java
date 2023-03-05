package com.nisum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "phone")
@Data
public class PhoneEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "number")
    private String number;
    
    @Column(name = "citycode")
    private String citycode;
    
    @Column(name = "countrycode")
    private String countrycode;
    
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private UserEntity user;

}
