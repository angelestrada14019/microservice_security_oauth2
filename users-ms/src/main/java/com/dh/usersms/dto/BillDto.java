package com.dh.usersms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Date billingDate;

    private BillDto customerBill;

    private Double totalPrice;
}
