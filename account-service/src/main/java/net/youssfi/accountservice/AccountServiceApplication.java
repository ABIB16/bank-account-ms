package net.youssfi.accountservice;

import net.youssfi.accountservice.client.CustomerRestClient;
import net.youssfi.accountservice.entity.BankAccount;
import net.youssfi.accountservice.entity.Customer;
import net.youssfi.accountservice.enums.AccountType;
import net.youssfi.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    // @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository,
                                        CustomerRestClient customerRestClient) {
        return args -> {
            List<Customer> allCustomers = customerRestClient.getAllCustomers();
            allCustomers.forEach(customer -> {
                for (AccountType accountType : AccountType.values()) {
                    BankAccount bankAccount = BankAccount.builder()
                            .accountId(UUID.randomUUID().toString())
                            .balance(Math.random() * 80000)
                            .createAt(LocalDate.now())
                            .type(accountType)
                            .currency("MAD")
                            .customerID(customer.getId())
                            .build();
                    bankAccountRepository.save(bankAccount);
                }
            });
        };
    }
}
