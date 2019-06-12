package com.jay.customerservice;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerServiceProxy customerServiceProxy;

    public CustomerDetails getCustomerDetailsById(long id) throws Exception {
        Optional<Customer> customerOp = customerRepository.findById(id);
        if(customerOp.isPresent()) {
            Customer customer = customerOp.get();
            List<Account> accounts = customerServiceProxy.getAccListByCustId(customer.getId());
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setAccountList(accounts);
            customerDetails.setFirstName(customer.getFirstName());
            customerDetails.setLastName(customer.getLastName());
            customerDetails.setId(customer.getId());
            return customerDetails;
        }
        else throw new Exception("Invalid Id");
    }

    public Customer getCustomerById(long id) throws Exception {
        Optional<Customer> customerOp = customerRepository.findById(id);
        if(customerOp.isPresent()) return customerOp.get();
        else throw new Exception("Invalid Id");
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer customer1 = customerRepository.getOne(id);
        if(customer1!=null) {
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            return customerRepository.save(customer1);
        }
        else throw new Exception("Invalid id");
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomerById(long id) throws Exception {
        try {
            Customer customer = customerRepository.getOne(id);
            try {
                customerServiceProxy.deleteAccByCustId(id);
            } catch (FeignException e) {
                System.out.println("No Accounts are associated");
            }
            customerRepository.delete(customer);
            System.out.println("Customer Deleted");
        } catch (EntityNotFoundException e) {
            throw new Exception("Invalid Id");
        }
    }
}
