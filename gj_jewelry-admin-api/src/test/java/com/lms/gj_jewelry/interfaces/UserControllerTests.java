package com.lms.gj_jewelry.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lms.gj_jewelry.application.UserService;
import com.lms.gj_jewelry.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static com.lms.gj_jewelry.test.data_check.MvcResultChecker.isMvcResultEqualTo;
import static com.lms.gj_jewelry.test.random.RandomUserGenerator.generateRandomUser;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUsers() throws Exception {
        List<User> users = new ArrayList<>();

        given(userService.getUsers()).willReturn(users);

        MvcResult result = mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(users, result, new TypeReference<List<User>>(){}), is(true));
    }

    @Test
    public void getUserById() throws Exception {
        User user = generateRandomUser();

        given(userService.getUserById(user.getId())).willReturn(user);

        MvcResult result = mvc.perform(get("/users/id/" + user.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(user, result, User.class), is(true));
    }
}