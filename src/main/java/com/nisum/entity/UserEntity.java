package com.nisum.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "active")
    private boolean active;

    @CreationTimestamp
    @Column(name = "created")
    private Date created;
    
    @UpdateTimestamp
    @Column(name = "modified")
    private Date modified;
    
    @CreationTimestamp
    @Column(name = "last_login")
    private Date lastLogin;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<PhoneEntity> phones;

}
