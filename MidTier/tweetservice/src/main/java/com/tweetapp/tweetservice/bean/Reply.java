/**
 * 
 */
package com.tweetapp.tweetservice.bean;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

/**
 * @author Chanthanu
 *
 */
@DynamoDBDocument
public class Reply {

	@DynamoDBAttribute
	private String userName;
	@DynamoDBAttribute
	private String tags;
	@DynamoDBAttribute
	private String message;
//	@DynamoDBAttribute
//	private Date createdAt;

	public Reply(String userName, String tags, String message) {
		super();
		this.userName = userName;
		this.tags = tags;
		this.message = message;
//		this.createdAt = createdAt;
	}

	public Reply() {
		super();
		// TODO Auto-generated constructor stub
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

//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt() {
//		this.createdAt = new Date();
//	}

	@Override
	public String toString() {
		return "Reply [userName=" + userName + ", tags=" + tags + ", message=" + message+ "]";
	}

}
