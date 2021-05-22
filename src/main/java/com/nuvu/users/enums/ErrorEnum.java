package com.nuvu.users.enums;

public enum ErrorEnum {

	DEFAULT_EXCEPTION(1, "Ocurrio un error interno, por favor inténtelo más tarde o comuníquese con soporte técnico"),
	INPUT_REQUEST_VALIDATION(2, "El json ingresado no contiene los campos mínimos para realizar la operación, error: %s "),
	PASSWORD_INCORRECT(3, "Contraseña incorrecta, por favor revise la información"),
	USER_NOT_FOUND(4, "El usuario o contraseña no son válidos, por favor revise la información"),
	USER_ALREADY_REGISTERED(5, "Ya existe un usuario registrado con el mismo número de identificación"),
	INVALID_ROLE(6, "Número de role invalido"),
	USER_NOT_REGISTERED(7, "El usuario no existe"),
	FORBIDDEN(8,"Usted no posee los permisos suficientes"),
	CARD_NOT_FOUND(9, "La tarjeta no existe")

	;

	public final int code;
	public final String description;

	private ErrorEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

}
