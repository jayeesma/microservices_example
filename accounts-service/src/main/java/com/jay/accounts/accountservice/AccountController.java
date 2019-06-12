package com.jay.accounts.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;


    @PostMapping(value = "/acc/{custId}", consumes = {"application/JSON"})
    public ResponseEntity createAccountByCustomerId(@PathVariable long custId, @RequestBody Account account) {
        try {
            Account newAccount = accountService.createAccountByCustId(custId, account);
            return ResponseEntity.status(200).body(newAccount);
        } catch (Exception e){
            return ResponseEntity.status(400).body("Invalid customer id");
        }
    }

    @PutMapping(value = "/acc/{accId}", consumes = {"application/JSON"})
    public ResponseEntity updateAccById(@PathVariable long accId, @RequestBody Account account) {
        try {
            Account updatedAcc = accountService.updateAccById(accId, account);
            return ResponseEntity.status(200).body(updatedAcc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/acc/all")
    public ResponseEntity getAllAccounts() {
        List<Account> accountList = accountService.getAllAccounts();
        return accountList != null ? ResponseEntity.status(200).body(accountList) : ResponseEntity.status(400).body("No accounts found");
    }


    @GetMapping(value = "/acc/{id}")
    public ResponseEntity getAccount(@PathVariable long id) {
        try {
            AccountDetails accountDetails = accountService.getAccount(id);

            return ResponseEntity.status(200).body(accountDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/acc/customer/{accId}")
    public ResponseEntity getCustomerByAccId(@PathVariable long accId) {
        try {
            Customer customer = accountService.getCustByAccId(accId);
            return ResponseEntity.status(200).body(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(value = "/acc/custId/{custId}")
    public ResponseEntity getAccListByCustId(@PathVariable long custId) {
        try {
            List<Account> accounts = accountService.getAccListByCustId(custId);
            return ResponseEntity.status(200).body(accounts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/acc/custId/{custId}")
    public ResponseEntity deleteAccByCustId(@PathVariable long custId) {
        try {
            List<Account>accounts = accountService.delAccListByCustId(custId);
            return ResponseEntity.status(200).body("Accounts Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
