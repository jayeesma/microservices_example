package com.jay.accounts.accountservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
@RibbonClient(name = "customer-service")
public interface AccountServiceProxy {

    @GetMapping(value="/customer/{id}")
    Customer getCustomer(@PathVariable long id);
}
