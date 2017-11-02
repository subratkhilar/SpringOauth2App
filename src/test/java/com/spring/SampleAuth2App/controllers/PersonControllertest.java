package com.spring.SampleAuth2App.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.SampleAuth2App.model.Person;
import com.spring.SampleAuth2App.repository.PersonRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class, secure = false)
public class PersonControllertest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PersonRepository personRepo;
	private Person person;

	@Before
	public void perpare() {
		person = new Person();
		person.setId(10l);
		person.setAge(25);
		person.setName("Test");

	}

	@Test
	public void getPerson() throws Exception {
		given(personRepo.findOne(10l)).willReturn(person);
		mockMvc.perform(get("/people/10").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(10))).andExpect(jsonPath("$.name", is("Test")))
				.andExpect(jsonPath("$.age", is(25)));
	}
}
