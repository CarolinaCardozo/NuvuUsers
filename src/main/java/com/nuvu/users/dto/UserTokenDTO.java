package com.nuvu.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDTO {
	
	private Long id;
	private String email;
	private Long identification;
	private String token;	

}
