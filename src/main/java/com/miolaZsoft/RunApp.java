package com.miolaZsoft;

import com.miolaZsoft.domain.Account;
import com.miolaZsoft.domain.Statement;

import static com.miolaZsoft.builderds.DateCreator.date;
import static com.miolaZsoft.domain.Amount.amountOf;

public class RunApp {
    public static void main(String[] args) {
        Account account = new Account(new Statement());

        account.deposit(amountOf(1000), date("10/01/2012"));
        account.deposit(amountOf(2000), date("13/01/2012"));
        account.withdrawal(amountOf(500), date("14/01/2012"));

        account.printStatement(System.out);
    }
}
