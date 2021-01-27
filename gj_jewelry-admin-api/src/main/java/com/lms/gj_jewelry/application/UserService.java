package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.UserRepository;
import com.lms.gj_jewelry.exception.UserIdNotFoundException;
import com.lms.gj_jewelry.interfaces.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        // TODO: need to apply pagination
        List<User> users = userRepository.findAll();

        return users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));
    }

}
