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

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long postId;
	@NotNull
	@NotBlank
	@Column(name = "idea")
	private String idea;
	@Column(name = "vote_up")
	private long voteUp;
	@Column(name = "vote_down")
	private long voteDown;
	@Column(name = "status")
	private String status;
	@Column(name = "cost_reviewed_by")
	private long costReviewedBy;
	@Column(name = "cost_score")
	private long cost_score;
	@Column(name = "feasibility_reviewed_by")
	private long feasibilityReviewedBy;
	@Column(name = "feasibility_score")
	private long feasibilityScore;
	@Column(name = "app_reviewed_by")
	private long appReviewedBy;
	@Column(name = "app_reviewed_score")
	private long appReviewedScore;
	@Column(name = "score")
	private long score;
	@NotNull
	@Column(name = "u_id")
	private long uId;
	@Column(name = "creation_date")
	private Date creationDate;
	@NotNull
	@Column(name = "c_id")
	private long cId;
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(long postId, String idea, long voteUp, long voteDown, String status, long costReviewedBy,
			long cost_score, long feasibilityReviewedBy, long feasibilityScore, long appReviewedBy,
			long appReviewedScore, long score, long uId, Date creationDate, long cId) {
		super();
		this.postId = postId;
		this.idea = idea;
		this.voteUp = voteUp;
		this.voteDown = voteDown;
		this.status = status;
		this.costReviewedBy = costReviewedBy;
		this.cost_score = cost_score;
		this.feasibilityReviewedBy = feasibilityReviewedBy;
		this.feasibilityScore = feasibilityScore;
		this.appReviewedBy = appReviewedBy;
		this.appReviewedScore = appReviewedScore;
		this.score = score;
		this.uId = uId;
		this.creationDate = creationDate;
		this.cId = cId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public long getVoteUp() {
		return voteUp;
	}

	public void setVoteUp(long voteUp) {
		this.voteUp = voteUp;
	}

	public long getVoteDown() {
		return voteDown;
	}

	public void setVoteDown(long voteDown) {
		this.voteDown = voteDown;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCostReviewedBy() {
		return costReviewedBy;
	}

	public void setCostReviewedBy(long costReviewedBy) {
		this.costReviewedBy = costReviewedBy;
	}

	public long getCost_score() {
		return cost_score;
	}

	public void setCost_score(long cost_score) {
		this.cost_score = cost_score;
	}

	public long getFeasibilityReviewedBy() {
		return feasibilityReviewedBy;
	}

	public void setFeasibilityReviewedBy(long feasibilityReviewedBy) {
		this.feasibilityReviewedBy = feasibilityReviewedBy;
	}

	public long getFeasibilityScore() {
		return feasibilityScore;
	}

	public void setFeasibilityScore(long feasibilityScore) {
		this.feasibilityScore = feasibilityScore;
	}

	public long getAppReviewedBy() {
		return appReviewedBy;
	}

	public void setAppReviewedBy(long appReviewedBy) {
		this.appReviewedBy = appReviewedBy;
	}

	public long getAppReviewedScore() {
		return appReviewedScore;
	}

	public void setAppReviewedScore(long appReviewedScore) {
		this.appReviewedScore = appReviewedScore;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", idea=" + idea + ", voteUp=" + voteUp + ", voteDown=" + voteDown
				+ ", status=" + status + ", costReviewedBy=" + costReviewedBy + ", cost_score=" + cost_score
				+ ", feasibilityReviewedBy=" + feasibilityReviewedBy + ", feasibilityScore=" + feasibilityScore
				+ ", appReviewedBy=" + appReviewedBy + ", appReviewedScore=" + appReviewedScore + ", score=" + score
				+ ", uId=" + uId + ", creationDate=" + creationDate + ", cId=" + cId + "]";
	}
	
	
	
	
		
}
