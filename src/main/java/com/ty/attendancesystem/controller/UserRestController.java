package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    public final UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    Optional<User> get(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @PostMapping
    User add(@RequestBody User user){
        if (user.getId() != null){
            throw new ValidateException("Id must be null");
        }
        return userService.save(user);
    }

    @PutMapping
    User update(@RequestBody User user){
        if(user.getId() == null){
            throw new ValidateException("Id must not be null");
        } else{
            Boolean checkExisted = userService.existById(user.getId());
            if(!checkExisted){
                throw new ValidateException("Id is not existed");
            }
        }
        return userService.save(user);
    }

    @DeleteMapping
    void delete(@RequestBody User user){
        userService.delete(user);
    }
}
