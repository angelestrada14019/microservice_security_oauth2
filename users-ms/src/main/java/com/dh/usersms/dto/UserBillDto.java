package com.dh.usersms.dto;

import com.dh.usersms.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBillDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<Bill> billDtoList;


}
