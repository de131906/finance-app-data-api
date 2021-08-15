package de.gp.finance.data.model;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(doNotUseGetters = true)
public abstract class Account extends BaseDocument {

    @NonNull
    private String name;

    @NonNull
    private AccountType accountType;

    private String currencyId;

    private Currency currency;

    private double balance;

    private double openBalance;

    private LocalDate openDate;

    Account(String name, AccountType accountType) {
        this.name = name;
        this.accountType = accountType;
    }
    
}
