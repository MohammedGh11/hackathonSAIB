package com.SAIB.IdeationPlatform.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id" )
	private long userId;
	
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "email")
	private String email;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "creation_date")
	private Date creationDate;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(long userId, String firstName, String email, String lastName, String userType, Date creationDate) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.email = email;
		this.lastName = lastName;
		this.userType = userType;
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", email=" + email + ", lastName="
				+ lastName + ", userType=" + userType + ", creationDate=" + creationDate + "]";
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
	public String getMiddleName() {
		return email;
	}
	public void setMiddleName(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
