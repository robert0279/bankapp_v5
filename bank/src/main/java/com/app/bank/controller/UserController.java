package com.app.bank.controller;


import com.app.bank.domain.entity.UserEntity;
import com.app.bank.domain.model.UserDTO;
import com.app.bank.exception.UserNotFindException;
import com.app.bank.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "http_basic")
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findById(@PathVariable("id")long id){return userService.findById(id);}

    @GetMapping("/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findByName(@PathVariable("lastName")String lastName){
        return userService.findAllByName(lastName);
    }

   // @ExceptionHandler(UserNotFindException.class)
    //public void


}
