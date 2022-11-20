package com.dh.usersms.service;

import com.dh.usersms.dto.UserBillDto;
import com.dh.usersms.dto.BillDto;
import com.dh.usersms.dto.UserDtoResponseNotAdmin;
import com.dh.usersms.model.Bill;
import com.dh.usersms.model.User;
import com.dh.usersms.repository.IUserRepository;
import com.dh.usersms.service.feign.FeignBillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final FeignBillService feignBillService;

    public UserService(IUserRepository userRepository, FeignBillService feignBillService) {
        this.userRepository = userRepository;
        this.feignBillService = feignBillService;
    }

    public UserBillDto findBilsByUserId(String userId) {
        List<Bill> bills = feignBillService.findByCustomer(userId);
        User user = userRepository.findById(userId);

        return new UserBillDto(user.getUsername(),user.getEmail(),user.getFirstName(),user.getLastName(),bills);

    }

    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }

    public User updateNationality(String id, String nationality) {
        return userRepository.updateNationality(id, nationality);
    }
    public User update(User user) {
        return userRepository.updateUser(user);
    }
    public List<UserDtoResponseNotAdmin> findUserNotAdmin(){
        return userRepository.findUserNotAdmin();
    }
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
