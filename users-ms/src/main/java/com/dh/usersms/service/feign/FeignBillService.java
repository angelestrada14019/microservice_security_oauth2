package com.dh.usersms.service.feign;


import com.dh.usersms.model.Bill;
import com.dh.usersms.repository.feign.FeignBillRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FeignBillService {
    private FeignBillRepository feignBillRepository;
    public FeignBillService(FeignBillRepository feignBillRepository) {
        this.feignBillRepository = feignBillRepository;
    }

    public List<Bill> findByCustomer(String customerBill){
        ResponseEntity<List<Bill>> response = feignBillRepository.findByCustomer(customerBill);
        return response.getBody();
    }


}
