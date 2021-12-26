package com.SAIB.IdeationPlatform.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id")
	private long catID;
	
	@Column(name = "cat_name")
	private String catName;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(long catID, String catName, Date creationDate) {
		super();
		this.catID = catID;
		this.catName = catName;
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "Category [catID=" + catID + ", catName=" + catName + ", creationDate=" + creationDate + "]";
	}
	public long getCatID() {
		return catID;
	}
	public void setCatID(long catID) {
		this.catID = catID;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
	
	
}
