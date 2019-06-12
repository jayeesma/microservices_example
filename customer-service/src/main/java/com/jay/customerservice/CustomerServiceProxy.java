package com.jay.customerservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="account-service")
@RibbonClient(name="account-service")
public interface CustomerServiceProxy {
    @GetMapping(value = "/acc/custId/{custId}")
    List<Account> getAccListByCustId(@PathVariable long custId);

    @DeleteMapping(value = "/acc/custId/{custId}")
    List<Account> deleteAccByCustId(@PathVariable long custId);
}
