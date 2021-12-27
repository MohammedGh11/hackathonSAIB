package com.SAIB.IdeationPlatform.model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id" )
	private long userId;
	@NotNull
	@Column(name = "first_name")
	private String firstName;
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;
	@NotNull
	@Column(name = "password")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$@–[{}]:;',?/*~$^+=<>]).{8,15}$")
	private String password;
	@NotNull
	@Column(name = "user_type")
	private String userType;
	@CreatedDate
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	

	
	
	
	public User() {
		super();
	}




	public User(long userId, String firstName,String lastName, String email,  String password, String userType,
			LocalDateTime creationDate) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.creationDate = creationDate;
		
	}




	public long getUserId() {
		return userId;
	}




	public void setUserId(long userId) {
		this.userId = userId;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getUserType() {
		return userType;
	}




	public void setUserType(String userType) {
		this.userType = userType;
	}




	public LocalDateTime getCreationDate() {
		return creationDate;
	}




	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}




	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", email=" + email + ", lastName=" + lastName
				+ ", password=" + password + ", userType=" + userType + ", creationDate=" + creationDate + "]";
	}
	
	
	
	
	
	
	
}
