package net.youssfi.accountservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.youssfi.accountservice.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")

    Customer findCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    List<Customer> getAllCustomers();

    default Customer getDefaultCustomer(Long id, Exception e){
        return Customer.builder()
                .id(id)
                .firstName("Source not available")
                .lastName("Source Not Available")
                .build();
    }
    default List<Customer> getDefaultCustomers(Exception e){
        return List.of();
    }

}
