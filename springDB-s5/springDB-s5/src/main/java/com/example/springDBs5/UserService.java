package com.example.springDBs5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public List<User> readAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser;
        optionalUser = userDAO.findById(id);
        return optionalUser.orElse(null);
    }

    public User addUser(User user) {
        return userDAO.save(user);
    }

    public void removeUser(Integer id) {
        userDAO.deleteById(id);
    }

    public User updateUser(Integer id, User user) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setFullName(user.getFullName());
            return userDAO.save(existingUser);
        }
        return null;
    }

    public User partialUpdateUser(Integer id, Map<String, Object> updates) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (updates.containsKey("email")) {
                existingUser.setEmail((String) updates.get("email"));
            }
            if (updates.containsKey("password")) {
                existingUser.setPassword((String) updates.get("password"));
            }
            if (updates.containsKey("fullName")) {
                existingUser.setFullName((String) updates.get("fullName"));
            }
            return userDAO.save(existingUser);
        } else {
            return null;
        }
    }
}
