package com.nuvu.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "creditCard")
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = true, nullable = false)
	private Integer id;
	
	@Column(name = "card_Number", length = 100, nullable = false)
	private String cardNumber;
			
	@Temporal(TemporalType.DATE)
	@Column(name = "expiration_date", nullable = false)
	private Date expirationDate;
	
	@Column(name = "available_quota", length = 100, nullable = false)
	private Float availableQuota;
	
	@Column(name = "maximum_quota", length = 100, nullable = false)
	private Float maximumQuota;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date", nullable = false)
	private Date registrationDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

}
