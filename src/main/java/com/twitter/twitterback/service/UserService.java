package com.twitter.twitterback.service;

import java.util.Calendar;
import java.util.List;
import com.twitter.twitterback.entity.User;
import com.twitter.twitterback.repository.UserRepository;
import com.twitter.twitterback.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	Calendar calendar = Calendar.getInstance();
	@Autowired
	UserRepository userRepositoryImpl;

	private UserService(){}

	public List<User> getAllUsers (){
		return (List<User>) userRepositoryImpl.findAll();
	}

	public User getUser(long id){
		return userRepositoryImpl.findById(id).get();
	}

	public User getUserByUsername(String userName){
		return userRepositoryImpl.findByUserName(userName).get();
	}

	public void registerUser(User user) {
		user.setPassword(GeneralUtils.stringToMd5(user.getPassword()));
		if (userRepositoryImpl.existsByUserName(user.getUsername())){
			//todo throw error
		} else {
			userRepositoryImpl.save(user);
		}
	}

	//todo check if username exists before registering
}
