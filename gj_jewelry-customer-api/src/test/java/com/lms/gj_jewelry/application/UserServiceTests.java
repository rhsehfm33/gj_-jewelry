package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.UserRepository;
import com.lms.gj_jewelry.exception.UserAccountNotFoundException;
import com.lms.gj_jewelry.exception.UserEmailNotFoundException;
import com.lms.gj_jewelry.exception.UserPhoneNumberNotFoundException;
import com.lms.gj_jewelry.interfaces.User;
import com.lms.gj_jewelry.exception.UserIdNotFoundException;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
        testUser = generateRandomUser();
    }

    @Test
    public void testCreateUser() {
        given(userRepository.save(any())).willReturn(testUser);

        User newUser = userService.createUser(testUser);

        assertThat(newUser.equals(testUser), is(true));
    }

    @Test
    public void testGetUsers() {
        List<User> users = generateRandomUserList(5);

        given(userRepository.findAll()).willReturn(users);

        List<User> searchedUsers = userService.getUsers();

        assertThat(searchedUsers.equals(users), is(true));
    }

    @Test
    public void testGetUserByIdIfExists() {
        given(userRepository.findById(testUser.getId())).willReturn(Optional.of(testUser));

        User searchedUser = userService.getUserById(testUser.getId());

        assertThat(searchedUser.equals(testUser), is(true));
    }

    @Test(expected = UserIdNotFoundException.class)
    public void testGetUserByIdIfNotExists() {
        given(userRepository.findById(any())).willReturn(Optional.empty());

        userService.getUserById(10000L);
    }

    @Test
    public void testGetUserByEmailIfExists() {
        given(userRepository.findByEmail(testUser.getEmail())).willReturn(Optional.of(testUser));

        User searchedUser = userService.getUserByEmail(testUser.getEmail());

        assertThat(searchedUser.equals(testUser), is(true));
    }

    @Test(expected = UserEmailNotFoundException.class)
    public void testGetUserByEmailIfNotExists() {
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        userService.getUserByEmail("NotExists");
    }

    @Test
    public void testGetUserByPhoneNumberIfExists() {
        given(userRepository.findByPhoneNumber(testUser.getPhoneNumber())).willReturn(Optional.of(testUser));

        User searchedUser = userService.getUserByPhoneNumber(testUser.getPhoneNumber());

        assertThat(searchedUser.equals(testUser), is(true));
    }

    @Test(expected = UserPhoneNumberNotFoundException.class)
    public void testGetUserByPhoneNumberIfNotExists() {
        given(userRepository.findByPhoneNumber(any())).willReturn(Optional.empty());

        userService.getUserByPhoneNumber("NotExists");
    }

    @Test
    public void testGetUserByAccountIfExists() {
        given(userRepository.findByAccount(testUser.getAccount())).willReturn(Optional.of(testUser));

        User searchedUser = userService.getUserByAccount(testUser.getAccount());

        assertThat(searchedUser.equals(testUser), is(true));
    }

    @Test(expected = UserAccountNotFoundException.class)
    public void testGetUserByAccountIfNotExists() {
        given(userRepository.findByAccount(any())).willReturn(Optional.empty());

        userService.getUserByAccount("NotExists");
    }

    @Test
    public void testUpdate() {
        given(userRepository.findById(any())).willReturn(Optional.of(testUser));

        User updatedTestUser = generateRandomUser();
        updatedTestUser.setId(testUser.getId());

        log.info("original user : " + testUser.toString());
        log.info("gonna change user like : " + updatedTestUser.toString());

        userService.updateUser(updatedTestUser);

        log.info("updated user : " + testUser.toString());

        assertThat(testUser.equals(updatedTestUser), is(true));
    }

    @Test
    public void testDelete() {
        given(userRepository.findById(testUser.getId())).willReturn(Optional.of(testUser));

        userService.deleteUser(testUser.getId());

        assertThat(testUser.isDeleted(), is(true));
    }
}