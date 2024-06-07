package net.youssfi.accountservice.controller;

import lombok.AllArgsConstructor;
import net.youssfi.accountservice.client.CustomerRestClient;
import net.youssfi.accountservice.entity.BankAccount;
import net.youssfi.accountservice.entity.Customer;
import net.youssfi.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountController {

    private  final BankAccountRepository bankAccountRepository;
    private  final CustomerRestClient customerRestClient;

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerID());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
