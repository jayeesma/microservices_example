package com.jay.accounts.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountServiceProxy accountServiceProxy;


    public AccountDetails getAccount(long id) throws Exception{
        Optional<Account> accOp = accountRepository.findById(id);
        if(accOp.isPresent()){
            Account account = accOp.get();
            Customer customer = new Customer();
            customer.setFirstName(accountServiceProxy.getCustomer(account.getCustomerId()).getFirstName());
            customer.setLastName(accountServiceProxy.getCustomer(account.getCustomerId()).getLastName());
            customer.setId(accountServiceProxy.getCustomer(account.getCustomerId()).getId());
            AccountDetails accountDetails = new AccountDetails();
            accountDetails.setBalance(account.getBalance());
            accountDetails.setCustomer(customer);
            accountDetails.setId(account.getId());
            accountDetails.setType(account.getType());
            return accountDetails;
        }
        else throw new Exception("Invalid id");
    }

    public Customer getCustByAccId(long id) throws Exception {
        Optional<Account> accOp = accountRepository.findById(id);
        if(accOp.isPresent()) {
            Account account = accOp.get();
            Customer customer = accountServiceProxy.getCustomer(account.getCustomerId());
            return customer;
        } else throw new Exception("Invalid id");
    }

    public List<Account> getAccListByCustId(long id) throws Exception {
        Optional<List<Account>> accountList = accountRepository.findByCustomerId(id);
        if(accountList.isPresent()) return accountList.get();
        else throw new Exception("Invalid id");
    }

    public Account createAccountByCustId(long custId,Account account) throws Exception {
        try{
        Customer customer = accountServiceProxy.getCustomer(custId);
        account.setCustomerId(custId);
        return accountRepository.save(account);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Account updateAccById(long accId, Account account) throws Exception {
        Account existingAcc = accountRepository.getOne(accId);
        if(existingAcc!=null) {
            existingAcc.setBalance(account.getBalance());
            return accountRepository.save(existingAcc);
        } else throw new Exception("Invalid Id");
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> delAccListByCustId(long custId) throws Exception{
        Optional<List<Account>> existingAccOp = accountRepository.findByCustomerId(custId);
        if(existingAccOp.isPresent()) {
            List<Account> accounts = existingAccOp.get();
            accountRepository.deleteAll(existingAccOp.get());
            return accounts;
        }
        else throw new Exception("No account associated with the customer id");
    }
}
