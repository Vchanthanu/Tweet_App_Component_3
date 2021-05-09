/**
 * 
 */
package com.tweetapp.tweetservice.bean;

import java.util.Date;

/**
 * @author Chanthanu
 *
 */
public class Reply {

	private String userName;
	private String tags;
	private String message;
	private Date createdAt;

	public Reply(String userName, String tags, String message, Date createdAt) {
		super();
		this.userName = userName;
		this.tags = tags;
		this.message = message;
		this.createdAt = createdAt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		this.createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Reply [userName=" + userName + ", tags=" + tags + ", message=" + message + ", createdAt=" + createdAt
				+ "]";
	}

}
