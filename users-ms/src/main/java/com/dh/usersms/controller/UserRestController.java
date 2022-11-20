package com.dh.usersms.controller;

import com.dh.usersms.dto.UserBillDto;
import com.dh.usersms.dto.UserDtoResponseNotAdmin;
import com.dh.usersms.model.User;
import com.dh.usersms.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/firstname/{firstName}")
    public List<User> findByFirstName(@PathVariable String firstName) {
        return userService.findByFirstName(firstName);
    }

    @GetMapping("/id/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDtoResponseNotAdmin> findUsersNotAdmin() {
        return userService.findUserNotAdmin();
    }

    @GetMapping("/admin/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PutMapping("/update/nationality")
    public User updateNationality(@RequestParam String id, @RequestParam String nationality) {
        return userService.updateNationality(id, nationality);
    }
    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/bills/details/{username}")
    public UserBillDto findBilsByUserId (@PathVariable String username){
        return userService.findBilsByUserId(username);
    }

}
