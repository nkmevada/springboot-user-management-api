package com.nk.usermanagement.controller;

import com.nk.usermanagement.dto.request.UserRequest;
import com.nk.usermanagement.dto.response.UserResponse;
import com.nk.usermanagement.entity.User;
import com.nk.usermanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse savedUser = userService.createUser(userRequest);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

//    @GetMapping
//    public ResponseEntity<List<User>> getAllUser(){
//        return ResponseEntity.ok(userService.getAllUser());
//    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(userService.getAllUser(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @PathVariable Long id, @RequestBody UserRequest userRequest){
        UserResponse updatedUser = userService.updateUser(id,userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUser(@RequestParam String keyword){
        return ResponseEntity.ok(userService.searchUser(keyword));
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<UserResponse>> getAllUser(@RequestParam(defaultValue = "id") String sortBy,
                                                         @RequestParam(defaultValue = "asc") String sortDir){
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        return ResponseEntity.ok(userService.getAllUser(sort));
    }
}
