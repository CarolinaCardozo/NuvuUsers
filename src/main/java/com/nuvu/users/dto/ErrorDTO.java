package com.nuvu.users.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int code;
	private String description;
}
