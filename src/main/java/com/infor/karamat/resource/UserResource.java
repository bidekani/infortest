package com.infor.karamat.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infor.karamat.handler.UserService;
import com.infor.karamat.model.User;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/v1/users")
@RestController
public class UserResource {
	@Autowired
	private UserService  userService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Register new user")
	public  User registerUser(@RequestBody User user){
		return userService.add(user);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value = "list All  users")
	protected List<User> listAll(){
		return userService.listAll();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete a user")
	protected boolean delete(@RequestParam("id") int userId){
		this.addUsers();
//		Init.addUsers();
		return userService.delete(userId);
	}
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = { "application/json" })
	@ApiOperation(value = "Update a user")
	protected User update(@RequestBody User user){
		
		return null;
	}
	
	public  void addUsers() {
		User user1 = new User(0,"user1", LocalDateTime.now(),"",LocalDateTime.now(),"");
		User user2 = new User(0,"user2",LocalDateTime.now(),"",LocalDateTime.now(),"");
		User user3 = new User(0,"user3",LocalDateTime.now(),"",LocalDateTime.now(),"");
		User user4 = new User(0,"user4",LocalDateTime.now(),"",LocalDateTime.now(),"");
		userService.add(user1);
		userService.add(user2);
		userService.add(user3);
		userService.add(user4);
			
	}
}


