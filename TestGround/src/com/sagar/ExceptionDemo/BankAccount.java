package com.sagar.ExceptionDemo;

/**
 * This is sample application class, that contains methods with
 * runtime and compile time exceptions.
 */
public class BankAccount {

    String accoutHolder ;
    int amount ;

    BankAccount(String nameAccountHolder, int initialAmount) throws InsufficientFundsException {
        if ( initialAmount < 0)
            throw new InsufficientFundsException("Amount cannot be negative");
        this.amount = initialAmount;
        this.accoutHolder = nameAccountHolder;
    }

    public void withdraw(int amountToWithdraw) {
        if ( amountToWithdraw > this.amount ) {
             throw new RuntimeException("Amount cannot be negative");
        }
    }

    public static void main(String[] args) {
        BankAccount sagarAccount = null;
        try {
            sagarAccount = new BankAccount("Sagar", 50);
        }
        catch (InsufficientFundsException e)
        {

        }
        if (null != sagarAccount)
            sagarAccount.withdraw(60);

    }
}
