package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.UserRepository;
import com.lms.gj_jewelry.exception.UserAccountNotFoundException;
import com.lms.gj_jewelry.exception.UserEmailNotFoundException;
import com.lms.gj_jewelry.exception.UserPhoneNumberNotFoundException;
import com.lms.gj_jewelry.interfaces.User;
import com.lms.gj_jewelry.exception.UserIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    // TODO: Need to change this as pagination
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserEmailNotFoundException(email));
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserPhoneNumberNotFoundException(phoneNumber));
    }

    public User getUserByAccount(String account) {
        return userRepository.findByAccount(account)
                .orElseThrow(() -> new UserAccountNotFoundException(account));
    }

    public User updateUser(User user) {
        User newUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserIdNotFoundException(user.getId()));

        userRepository.save(user);

        return newUser;
    }

    public void deleteUser(Long id) {
        User deletedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));

        deletedUser.setDeleted(true);
        deletedUser.setDeletedAt(LocalDate.now());
    }
}
