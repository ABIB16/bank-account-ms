package net.youssfi.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;
import net.youssfi.accountservice.enums.AccountType;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Long customerID;
    @Transient
    private Customer customer;

}
