package com.epam.rymasheuski.userCatalogue.web;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.rymasheuski.userCatalogue.domain.User;
import com.epam.rymasheuski.userCatalogue.services.UsersService;

@RestController
@RequestMapping("/rest/users")
public class UsersController {

	private UsersService usersService;

	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<User> findAll() {
		return usersService.findAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User find(@PathVariable int id) {
		return usersService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public int create(@RequestBody User user) {
		return usersService.save(user);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public int updateAgent(@RequestBody User user, @PathVariable int id) throws NotFoundException {

		return usersService.update(id, user);
	}
}
