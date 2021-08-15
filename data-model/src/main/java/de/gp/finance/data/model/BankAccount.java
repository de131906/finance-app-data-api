package de.gp.finance.data.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true, doNotUseGetters = true)
public class BankAccount extends Account {

    private String institutionId;

    private Institution institution;
    
    @NonNull
    private String iban;

    public BankAccount(String name, AccountType type) {
        super(name, type);
    }
    
}
