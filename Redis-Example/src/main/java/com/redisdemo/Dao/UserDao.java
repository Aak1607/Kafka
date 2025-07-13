package com.redisdemo.Dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redisdemo.entity.User;

@Repository
public class UserDao {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String KEY = "User";
	
	public User save(User user) {
		redisTemplate.opsForHash().put(KEY, user.getUserID(), user);
		return user;
	}
	
	public User get(String userid) {
		return (User) redisTemplate.opsForHash().get(KEY, userid);
	}

	public Map<Object, Object> findAll(){
		return redisTemplate.opsForHash().entries(KEY);
	}
	
	public void delete(String userid) {
		 redisTemplate.opsForHash().delete( KEY, userid);
	}
	
	public User update(User user) {
		// Check if the user exists before updating
		if (redisTemplate.opsForHash().hasKey(KEY, user.getUserID())) {
			redisTemplate.opsForHash().put(KEY, user.getUserID(), user);
			return user;
		} else {
			throw new IllegalArgumentException("User with ID " + user.getUserID() + " does not exist.");
		}
	}

}
