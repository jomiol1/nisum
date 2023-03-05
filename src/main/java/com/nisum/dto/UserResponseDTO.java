package com.nisum.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
	
	private Integer id;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private String token;
	private Boolean active;


}
