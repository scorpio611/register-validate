package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import com.codegym.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    UserService userService = new UserServiceImpl();

    @GetMapping("/")
    public ModelAndView listCategories(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        } else {
            Iterable<User> users = userService.findAll();
            ModelAndView modelAndView = new ModelAndView("/result");
            modelAndView.addObject("users", users);
            return modelAndView;
        }

    }

    @GetMapping("/create")
    public ModelAndView createCategory() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("category") User category) {
        userService.save(category);

        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message", "New user created successfully");
        return modelAndView;
    }
}
