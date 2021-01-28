package com.lms.gj_jewelry.domain;

import com.lms.gj_jewelry.interfaces.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static com.lms.gj_jewelry.test.random.RandomUserInstanceGenerator.generateRandomUser;
import static com.lms.gj_jewelry.test.random.RandomUserInstanceGenerator.generateRandomUserList;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    final int INSERTED_USERS = 5;

    @Autowired
    UserRepository userRepository;

    List<User> dummyUserList;

    @Before
    public void setUp() {
        dummyUserList = new ArrayList<>();
        dummyUserList = generateRandomUserList(INSERTED_USERS);

        userRepository.saveAll(dummyUserList);
    }

    @Test
    public void testFindAll() {
        List<User> newUserList = userRepository.findAll();

        assertThat(newUserList.size(), is(INSERTED_USERS));
        for (int i = 0; i < newUserList.size(); ++i) {
//            log.info("{}", newUserList.get(i).toString());
            assertThat(newUserList.get(i).equals(dummyUserList.get(i)), is(true));
        }
    }

    @Test
    public void testFindByEmail() {
        // Query about emails that exist. should be true
        for (User insertedUser : dummyUserList) {
            assertThat(userRepository.findByEmail(insertedUser.getEmail()).isPresent(), is(true));
        }

        // Query about emails that doesn't exist. should be false
        assertThat(userRepository.findByEmail("wrongData!!@#$%^&").isPresent(), is(false));
    }

    @Test
    public void testFindByPhoneNumber() {
        // Query about phone numbers that exist. should be true
        for (User insertedUser : dummyUserList) {
            assertThat(userRepository.findByPhoneNumber(insertedUser.getPhoneNumber()).isPresent(), is(true));
        }

        // Query about phone numbers that doesn't exist. should be false
        assertThat(userRepository.findByPhoneNumber("!@#!@$!@").isPresent(), is(false));
    }

    @Test
    public void testFindById() {
        User anyUserInDB = userRepository.findByEmail(dummyUserList.get(0).getEmail()).get();

        // Query about id that exist. should be true
        assertThat(userRepository.findById(anyUserInDB.getId()).isPresent(), is(true));

        // Query about id that doesn't exist. should be false
        assertThat(userRepository.findById(9999999999L).isPresent(), is(false));
    }

    @Test
    public void testFindByAccount() {
        // Query about accounts that exist. should be true
        for (User insertedUser : dummyUserList) {
            assertThat(userRepository.findByAccount(insertedUser.getAccount()).isPresent(), is(true));
        }

        // Query about accounts that doesn't exist. should be false
        assertThat(userRepository.findByAccount("!@#!@$!@").isPresent(), is(false));
    }

    @Test
    public void testDelete() {
        User user = generateRandomUser();

        user.setDeleted(true);
        user.setDeletedAt(LocalDate.now());

        userRepository.save(user);

        assertThat(userRepository.findByEmail(user.getEmail()).isPresent(), is(false));
    }
}