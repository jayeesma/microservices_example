package com.jay.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customer",consumes = {"application/JSON"}, produces = {"application/JSON"})
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(200).body(customer);
    }

    @PutMapping(value = "/customer/{id}",consumes = {"application/JSON"}, produces = {"application/JSON"})
    public ResponseEntity updateCustomerById(@PathVariable Long id, @RequestBody Customer customer){
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            return ResponseEntity.status(200).body(updatedCustomer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/customer/all",produces = {"application/JSON"})
    public ResponseEntity getAllCustomers(){
        List<Customer> customerList = customerService.getAllCustomers();
        return (customerList!=null) ? ResponseEntity.status(200).body(customerList) : ResponseEntity.status(404).body("No Customers Found");
    }

    @GetMapping(value = "/customerDetails/{id}", produces = {"application/JSON"})
    public ResponseEntity getCustomerDetailsById(@PathVariable long id){
        try {
            CustomerDetails customer = customerService.getCustomerDetailsById(id);
            return ResponseEntity.status(200).body(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/customer/{id}", produces = {"application/JSON"})
    public ResponseEntity getCustomerById(@PathVariable long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.status(200).body(customer);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/customer/{id}",produces = {"application/JSON"})
    public ResponseEntity deleteCustomerById(@PathVariable long id){
        try {
            customerService.deleteCustomerById(id);
            return ResponseEntity.status(200).body("Customer Successfully deleted");
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
