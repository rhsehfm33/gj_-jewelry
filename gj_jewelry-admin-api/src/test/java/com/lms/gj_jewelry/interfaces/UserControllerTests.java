package com.lms.gj_jewelry.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lms.gj_jewelry.application.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import random.RandomUserInstanceGenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        // complete objectMapper for converting user object to json
        // This is used for checking equality
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(dateFormat);
    }

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void create() throws Exception {
        User user = RandomUserInstanceGenerator.generateRandomUser();
        String userJson = objectMapper.writeValueAsString(user);

        System.out.println(userJson);

        given(userService.getUserById(user.getId())).willReturn(user);

        mvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(userJson));
    }
}