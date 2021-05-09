/**
 * 
 */
package com.tweetapp.tweetservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tweetapp.tweetservice.entity.User;
import com.tweetapp.tweetservice.exception.UserException;
import com.tweetapp.tweetservice.util.LoggerConst;

/**
 * @author Chanthanu
 *
 */

@Component
public class UserDaoImpl implements UserDao {

//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	MongoTemplate template;
	@Autowired
	DynamoDBMapper mapper;

	@Override
	public List<User> getAllUsers() throws UserException {
		LoggerConst.LOG.info("getAllUsers  - Inside Repository");
		return mapper.scan(User.class, new DynamoDBScanExpression());
	}

	@Override
	public String registerUser(User user) throws UserException {
		LoggerConst.LOG.info("registerUser  - Inside Repository");
		mapper.save(user);
		return " User Successfully Registered with UserName : " + user.getUserName();
	}

	@Override
	public User getUserByUsername(String userName) throws UserException {
		LoggerConst.LOG.info("getUserByUsername  - Inside Repository");
		return mapper.load(User.class,userName);
	}

	@Override
	public User getUserByEmail(String email) throws UserException {
		LoggerConst.LOG.info("getUserByEmail  - Inside Repository");
		return mapper.load(User.class,email);
	}

	@Override
	public String changePassword(User user) throws UserException {
		LoggerConst.LOG.info("changePassword  - Inside Repository");
		mapper.save(user);
		return "Password updated Successfully";
	}

//	@Override
//	public void pushNotifications(String userName) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("userName").exists(true));
//		Update update = new Update().push("notification", userName);
//		template.upsert(query, update, User.class);
//	}

}
