/**
 * 
 */
package com.tweetapp.tweetservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.tweetservice.bean.UserResponse;
import com.tweetapp.tweetservice.entity.User;
import com.tweetapp.tweetservice.exception.UserException;
import com.tweetapp.tweetservice.service.UserService;
import com.tweetapp.tweetservice.util.LoggerConst;

import io.swagger.annotations.Api;

/**
 * @author Chanthanu
 *
 */
@Api(value = "UserServiceImpl")
@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/tweets/")
public class UserControllerImpl implements UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "users/all")
//	@ApiOperation(value = "get all user", response = UserResponse.class, tags = "Get All Users")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class),
//			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not Found"),
//			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@Override
	public ResponseEntity<UserResponse> getAllUsers() {
		LoggerConst.LOG.info("Get all User - Inside Controller");
		UserResponse res = new UserResponse();
		try {
			res.setUsers(userService.getAllUser());
			LoggerConst.LOG.info("Get all User - result fetched in controller ");
		} catch (UserException e) {
			LoggerConst.LOG.debug("Error occured at  get all user - in Controller" + e.getMessage());
			res.setStatus(false);
			res.setMessage("Failed to get All Users");
		}
		return ResponseEntity.ok().body(res);
	}

	@PostMapping(value = "login")
//	@ApiOperation(value = "login user", response = UserResponse.class, tags = "Login User")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class),
//			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not Found"),
//			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@Override
	public ResponseEntity<UserResponse> userLogin(@RequestBody User logInfo) {
		UserResponse res = new UserResponse();
		try {
			LoggerConst.LOG.info("User Login - Inside Controller");
			UserResponse info = userService.userLogin(logInfo.getUserName(), logInfo.getPassword());
			res.setUser(info.getUser());
			res.setStatus(info.isStatus());
			LoggerConst.LOG.info("User logger inSuccessfully - Inside Controller");
			res.setMessage(info.getMessage());
		} catch (UserException e) {
			LoggerConst.LOG.info("Error while user login - Inside Controller");
			res.setStatus(false);
			res.setMessage("Invalid userName or Password");
		}
		return ResponseEntity.ok().body(res);
	}

	@PostMapping(value = "register")
//	@ApiOperation(value = "register userr", response = UserResponse.class, tags = "Register User")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class),
//			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not Found"),
//			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@Override
	public ResponseEntity<UserResponse> registerUser(@RequestBody User user) {
		UserResponse res = new UserResponse();
		try {
			LoggerConst.LOG.info("register User - Inside Controller");
			res = userService.registerUser(user);
			LoggerConst.LOG.info("User registered successfully - Inside Controller");
		} catch (UserException e) {
			LoggerConst.LOG.info("Error while user registration - Inside Controller");
			res.setStatus(false);
			res.setMessage("Failed to Register user");
		}
		return ResponseEntity.ok().body(res);
	}

	@GetMapping(value = "user/search/{userName}")
//	@ApiOperation(value = "get user by user Name", response = UserResponse.class, tags = "Get user by User name")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class),
//			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not Found"),
//			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@Override
	public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String userName) {
		UserResponse res = new UserResponse();
		try {
			LoggerConst.LOG.info("Get User by userName - Inside Controller");
			User user = userService.getUserByUsername(userName);
			if (user == null) {
				LoggerConst.LOG.info("Error while Get User by username - Inside Controller");
				res.setStatus(false);
				res.setMessage("Failed to fetch by username");
			} else {
				res.setUser(user);
				LoggerConst.LOG.info("getUserByUserName - Successfull - Inside Controller");
			}
		} catch (UserException e) {
			LoggerConst.LOG.info("Error while Get User by username - Inside Controller");
			res.setStatus(false);
			res.setMessage("Failed to fetch by username");
		}
		return ResponseEntity.ok().body(res);
	}

	@PutMapping(value = "{userName}/forgot")
//	@ApiOperation(value = "forgot Password", response = UserResponse.class, tags = "Forgot Password")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class),
//			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not Found"),
//			@ApiResponse(code = 500, message = "Internal Server Error"), })

	@Override
	public ResponseEntity<UserResponse> forgotPassword(@PathVariable String userName, @RequestBody User userPassword) {
		UserResponse res = new UserResponse();
		try {
			LoggerConst.LOG.info("Forgot password - Inside Controller");
			res = userService.forgotPassword(userName, userPassword.getPassword());
			LoggerConst.LOG.info("Forgot password successfull - Inside Controller");
		} catch (UserException e) {
			LoggerConst.LOG.info("Error while forgot password - Inside Controller");
			res.setStatus(false);
			res.setMessage("Failed to update the password");
		}
		return ResponseEntity.ok().body(res);
	}

}
