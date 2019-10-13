package com.piotrek.apps.orderHaircutApp.controller;

import com.piotrek.apps.orderHaircutApp.dao.RoleRepo;
import com.piotrek.apps.orderHaircutApp.dto.UserDto;
import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.services.UserServiceImplementation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class RegistrationController {

    private UserServiceImplementation userService;

    private RoleRepo roleRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(UserServiceImplementation userService, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/showRegistrationForm")
    public String showUserRegistrationPage(@RequestParam(value = "userRole") String role, Model model) {
        model.addAttribute("userRole", role);
        model.addAttribute("userDto", new UserDto());
        return "register/registration-page";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult bindingResult,
            @ModelAttribute("userRole") String role, Model theModel) {

        if (bindingResult.hasErrors()) {
            return "register/registration-page";
        }

        String userName = userDto.getUserName();

        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("userDto", new UserDto());
            theModel.addAttribute("registrationError", "User name already exists.");

            System.out.println("User name already exists.");
            return "register/registration-page";
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (role.equals("client")) {
            userDto.setRole(Arrays.asList(roleRepo.findByRoleName("ROLE_CLIENT")));
        } else if (role.equals("owner")) {
            userDto.setRole(Arrays.asList(roleRepo.findByRoleName("ROLE_OWNER")));
        }

        userService.save(userDto);

        System.out.println("Successfully created user: " + userName);

        return "register/registration-confirmation";
    }

}
