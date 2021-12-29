package com.SAIB.IdeationPlatform.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true)
	private long userId;
	@NotNull
	@NotBlank
	@Pattern(regexp = "[^/%']+", message = "The following characters is not allowed [/%']")
	@Column(name = "first_name")
	private String firstName;
	@NotNull
	@NotBlank
	@Pattern(regexp = "[^/%']+", message = "The following characters is not allowed [/%']")
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Pattern(regexp = "[^/%']+", message = "The following characters is not allowed [/%']")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;
	@NotNull
	@Column(name = "password")
	private String password;
	@Column(name = "user_type")
	private String userType;
	@CreationTimestamp
	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	public User() {
		super();
	}

	public User(long userId, String firstName, String lastName, String email, String password, String userType,
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
