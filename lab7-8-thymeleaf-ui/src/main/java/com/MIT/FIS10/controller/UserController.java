package com.MIT.FIS10.controller;

import com.MIT.FIS10.service.UserService;

import jakarta.validation.Valid;

import com.MIT.FIS10.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/users")
    public String getUsers(
            @RequestParam(defaultValue = "1") int page,
            Model model) {
        int pageSize = 2; // Number of users per page
        Page<User> userPage = userService.getPaginatedUsers(page - 1, pageSize);

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("pageTitle", "User List");
        return "users";
    }


    @GetMapping("/listusers")
    public String listUsers(Model model, @RequestParam(defaultValue = "1") int page) {
        Page<User> usersPage = userService.findPaginated(page, 10);  // 10 users per page
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        return "userlist";  // Thymeleaf view
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("pageTitle", "Add User");
        model.addAttribute("user", new User());
        return "userform";
    }

    @PostMapping("/save")
    public String saveUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {

        // Check for validation errors
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Add User");
            return "userform"; // Return to the form with errors
        }

        // Save the user
        userService.saveUser(user);

        // Redirect to the user list page after successful save
        return "redirect:/users";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("message", "User deleted successfully!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);  // Fetch user from the database
        model.addAttribute("user", user);  // Add user data to the model
        return "userupdate";  // Return the userform.html page for updating
    }

    // Method to handle the update form submission
    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Update User");
            return "userupdate";  // If there are validation errors, return to the form
        }
        userService.updateUser(id, user);  // Call the service to update the user
        return "redirect:/users";  // Redirect to the list of users after updating
    }
}
