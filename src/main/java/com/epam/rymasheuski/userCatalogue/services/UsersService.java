package com.epam.rymasheuski.userCatalogue.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.rymasheuski.userCatalogue.domain.User;

@Service
public class UsersService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);
	private static final List<User> LIST = new CopyOnWriteArrayList <>();

	public UsersService() {
		LIST.add(new User("Denis", "Rym", "rym-dzianis", "rym@epam.com"));	
	}

	public List<User> findAll() {
		return LIST;
	}

	public User findById(int id) {
		LOGGER.info("User: " + id);
		return LIST.get(id);
	}

	public int save(User user) {
		LIST.add(user);
		LOGGER.info("User: " + user.getFirstName());
		return LIST.indexOf(user);
	}

	public int update(int id, User user) {
		LIST.remove(id);
		LIST.add(id, user);
		return id;
	}
	
	
}
