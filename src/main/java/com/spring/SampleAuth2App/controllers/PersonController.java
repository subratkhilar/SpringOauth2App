package com.spring.SampleAuth2App.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SampleAuth2App.model.Party;
import com.spring.SampleAuth2App.model.Person;
import com.spring.SampleAuth2App.repository.PersonRepository;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * @author A669825
 *
 */
@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private PersonRepository personRepo;

	@RequestMapping(method = RequestMethod.GET)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "Authorization", paramType = "header", value = "bearer ", dataType = "string", required = true) })
	public ResponseEntity<Collection<Person>> getPeople() {
		return new ResponseEntity<>(personRepo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPerson(@PathVariable long id) {
		Person person = personRepo.findOne(id);

		if (person != null) {
			return new ResponseEntity<>(personRepo.findOne(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "Authorization", paramType = "header", value = "bearer ", dataType = "string", required = true) })
	public ResponseEntity<?> addPerson(@RequestBody Person person) {
		return new ResponseEntity<>(personRepo.save(person), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "Authorization", paramType = "header", value = "bearer ", dataType = "string", required = true) })
	public ResponseEntity<Void> deletePerson(@PathVariable long id, Principal principal) {
		Person currentPerson = personRepo.findByUsername(principal.getName());

		if (currentPerson.getId() == id) {
			personRepo.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/{id}/parties", method = RequestMethod.GET)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "Authorization", paramType = "header", value = "bearer ", dataType = "string", required = true) })
	public ResponseEntity<Collection<Party>> getPersonParties(@PathVariable long id) {
		Person person = personRepo.findOne(id);
		Set<Party> parties =null;
		if (person != null) {
			return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(parties, HttpStatus.NOT_FOUND);
		}
	}

}
