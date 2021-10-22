package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.UserRepository;
import com.lms.gj_jewelry.interfaces.User;
import com.lms.gj_jewelry.exception.UserIdNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.lms.gj_jewelry.test.random.RandomUserGenerator.generateRandomUser;
import static com.lms.gj_jewelry.test.random.RandomUserGenerator.generateRandomUserList;
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
        List<User> userList = generateRandomUserList(5);
        given(userRepository.findAll()).willReturn(userList);

        List<User> insertedUserList = userService.getUsers();

        verify(userRepository).findAll();

        for (int i = 0; i < userList.size(); ++i) {
            assertThat(insertedUserList.get(i).equals(userList.get(i)), is(true));
        }
    }

    @Test
    public void testGetUserByIdIfExists() {
        Optional<User> user = Optional.ofNullable(generateRandomUser());

        given(userRepository.findById(any())).willReturn(user);

        User resultUser = userService.getUserById(1L);

        assertThat(user.get().equals(resultUser), is(true));
    }

    @Test(expected = UserIdNotFoundException.class)
    public void testGetUserByIdIfNotExists() {
        given(userRepository.findById(any())).willReturn(Optional.empty());
        userService.getUserById(10000L);
    }
}