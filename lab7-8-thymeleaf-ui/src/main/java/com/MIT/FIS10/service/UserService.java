package com.MIT.FIS10.service;

import com.MIT.FIS10.entity.User;
import com.MIT.FIS10.exception.ResourceNotFoundException;
import com.MIT.FIS10.repository.UserRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getPaginatedUsers(int page, int pageSize) {
        return userRepository.findAll(PageRequest.of(page, pageSize));
    }

    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    public void saveUser(@Valid User user) {
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
    }

    // Method to find paginated users
    public Page<User> findPaginated(int page, int size) {
        // Create a Pageable object using PageRequest.of
        PageRequest pageable = PageRequest.of(page - 1, size);  // Page starts at 0, so subtract 1
        Page<User> usersPage = userRepository.findAll(pageable);  // Fetch paginated users
        return (usersPage);  // Wrap it in PageTable
    }

    public void updateUser(Long id, User user) {
        // Check if the user exists in the database
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Update user details
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        // Save the updated user
        userRepository.save(existingUser);
    }

    // Method to find a user by id
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
