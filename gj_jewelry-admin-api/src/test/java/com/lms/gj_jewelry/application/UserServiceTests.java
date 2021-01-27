package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.UserRepository;
import com.lms.gj_jewelry.interfaces.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import random.RandomUserInstanceGenerator;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUsers() {
        List<User> userList = RandomUserInstanceGenerator.generateRandomUserList(5);
        given(userRepository.findAll()).willReturn(userList);

        List<User> insertedUserList = userService.getUsers();

        verify(userRepository).findAll();

        for (int i = 0; i < userList.size(); ++i) {
            assertThat(insertedUserList.get(i).equals(userList.get(i)), is(true));
        }
    }

    @Test
    public void testGetUserById() {
        Optional<User> user = Optional.ofNullable(RandomUserInstanceGenerator.generateRandomUser());
        given(userRepository.findById(any())).willReturn(user);

        User resultUser = userService.getUserById(1L);

        assertThat(user.get().equals(resultUser), is(true));
    }
}