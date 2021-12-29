package com.SAIB.IdeationPlatform.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comments")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Comment_ID", unique = true)
	private long commentId;
	@NotNull
	@NotBlank
	@Pattern(regexp = "[^/%']+", message = "The following characters is not allowed [/%']")
	@Column(name = "text")
	private String text;
	@Column(name = "p_id")
	private long pID;
	@Column(name = "u_id")
	private long uID;
	@CreationTimestamp
	@Column(name = "creation_date")
	private Date creationDate;

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comments(long commentId, String text, long pID, long uID, Date creationDate) {
		super();
		this.commentId = commentId;
		this.text = text;
		this.pID = pID;
		this.uID = uID;
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", text=" + text + ", pID=" + pID + ", uID=" + uID
				+ ", creationDate=" + creationDate + "]";
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getpID() {
		return pID;
	}

	public void setpID(long pID) {
		this.pID = pID;
	}

	public long getuID() {
		return uID;
	}

	public void setuID(long uID) {
		this.uID = uID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
