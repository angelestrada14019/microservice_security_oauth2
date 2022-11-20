package com.dh.usersms.repository.feign;


import com.dh.usersms.configuration.feign.OAuthFeignConfig;
import com.dh.usersms.model.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name= "bills-service", configuration = OAuthFeignConfig.class)
public interface FeignBillRepository {

    @GetMapping("/api/bills/findBy/{customer}")
    ResponseEntity<List<Bill>> findByCustomer(@PathVariable String customer);
}
