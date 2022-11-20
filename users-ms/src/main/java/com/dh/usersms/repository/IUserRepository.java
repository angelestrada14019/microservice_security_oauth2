package com.dh.usersms.repository;

import com.dh.usersms.dto.UserDtoResponseNotAdmin;
import com.dh.usersms.model.User;

import java.util.List;

public interface IUserRepository {

    List<User> findByFirstName(String name);

    List<User> findByUsername(String username);
    User updateUser(User user);

    List<UserDtoResponseNotAdmin> findUserNotAdmin();



    User findById(String id);

    User updateNationality(String id, String nationality);
}
