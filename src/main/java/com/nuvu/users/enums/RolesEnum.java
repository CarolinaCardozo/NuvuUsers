package com.nuvu.users.enums;

import lombok.Getter;

@Getter
public enum RolesEnum {
	
	USER_ROLE(1,"USER"),
	ADMIN(2,"ADMIN");
	
	private final int roleId;
	private final String roleName;
	
	private RolesEnum(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	
}
