package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UpdateRequest;
import com.group.libraryapp.dto.user.request.UserRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;

import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }


    @PostMapping("/user")
    void storeUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);
    }

    @GetMapping("/user")
    List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UpdateRequest updateRequest) {
        userService.updateUser(updateRequest);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        System.out.println("name = " + name);
        userService.deleteUser(name);
    }
}
