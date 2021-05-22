package com.nuvu.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = true, nullable = false)
	private Integer id;
	
	@Column(name = "fullname", length = 200, nullable = false)
	private String fullname;
	
	@Column(name = "identification", length = 200, nullable = false)
	private Long identification;
	
	@Column(name = "email", length = 60, nullable = false)
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", nullable = true)
	private Date birthday;
	
	@Column(name = "phone", length = 60, nullable = true)
	private String phone;
	
	@Column(name = "password", length = 60, nullable = true)
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date", nullable = false)
	private Date registration_date;
	
	@Column(name = "role_id", length = 6, nullable = false)
	private int roleId;

}
