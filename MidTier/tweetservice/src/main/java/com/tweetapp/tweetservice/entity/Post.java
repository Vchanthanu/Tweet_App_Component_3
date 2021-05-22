/**
 * 
 */
package com.tweetapp.tweetservice.entity;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tweetapp.tweetservice.bean.Reply;

/**
 * @author Chanthanu
 *
 */
@DynamoDBTable(tableName="post")
@JsonInclude(Include.NON_NULL)
public class Post {

	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
	private String id;
	@DynamoDBAttribute
	private String tweet;
	@DynamoDBAttribute
	private String userName;
	@DynamoDBAttribute
	private String tags;
	@DynamoDBAttribute
	private int likes;
//	@DynamoDBAttribute
//	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss Z")
//	private Date createdAt;
	@DynamoDBAttribute
	private List<Reply> reply;
	
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//	public void setCreatedAt() {
//		this.createdAt = new Date();
//	}
	public List<Reply> getReply() {
		return reply;
	}
	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
	public Post(String id, String tweet, String userName, String tags, int likes, Date createdAt, List<Reply> reply) {
		super();
		this.id = id;
		this.tweet = tweet;
		this.userName = userName;
		this.tags = tags;
		this.likes = likes;
//		this.createdAt = createdAt;
		this.reply = reply;
	}
	
	public Post(String id, String tweet, String userName, String tags, int likes, Date createdAt) {
		super();
		this.id = id;
		this.tweet = tweet;
		this.userName = userName;
		this.tags = tags;
		this.likes = likes;
//		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", tweet=" + tweet + ", userName=" + userName + ", tags=" + tags + ", likes=" + likes
				+ ", reply=" + reply + "]";
	}
	
}
