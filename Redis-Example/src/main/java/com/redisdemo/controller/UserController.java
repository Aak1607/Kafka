package com.redisdemo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redisdemo.Dao.UserDao;
import com.redisdemo.entity.User;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		user.setUserID(UUID.randomUUID().toString());
		return userDao.save(user);
	}
	
	// Get user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userDao.get(id);
    }

    @GetMapping
    public List<User> getAll() {
        Map<Object, Object> all = userDao.findAll(); // Assuming this returns a Map
        Collection<Object> values = all.values();    // Extract all values from the map
        List<User> collect = values.stream()
                                   .map(value -> (User) value) // Cast each value to User
                                   .collect(Collectors.toList());
        return collect;
    }


    // Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userDao.delete(id);
    }

    // Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        user.setUserID(id); // Ensure path ID is used as the actual user ID
        return userDao.update(user);
    }

}
