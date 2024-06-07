package net.youssfi.customerservice;

import net.youssfi.customerservice.entity.Customer;
import net.youssfi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

   // @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            List<Customer> customerList = List.of(Customer.builder()
                            .firstName("billel")
                            .lastName("abib")
                            .email("abib.billel@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("aghiles")
                            .lastName("abib")
                            .email("abib.aghiles@gmail.com")
                            .build());

            customerRepository.saveAll(customerList);
        };
    }


}
