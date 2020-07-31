package com.app.bank.controller;


import com.app.bank.domain.entity.UserEntity;
import com.app.bank.domain.model.UserDTO;
import com.app.bank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.create(userDTO);}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    /*
 @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser (@RequestBody User user){
        return userService.createUser(user);
    }
     */


}
