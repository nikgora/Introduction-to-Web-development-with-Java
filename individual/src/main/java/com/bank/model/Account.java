package com.bank.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a bank account with a specific type and balance.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;      // Type of the account (e.g., Savings, Checking)
    private double balance;   // Current balance of the account
    private String currency;  // Currency of the account

    /**
     * Default constructor.
     */
    public Account() {
    }

    /**
     * Parameterized constructor to initialize Account with type, balance, and currency.
     *
     * @param type     The type of the account.
     * @param balance  The initial balance of the account.
     * @param currency The currency of the account.
     */
    public Account(String type, double balance, String currency) {
        this.type = type;
        this.balance = balance;
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "type='" + type + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        if (!Objects.equals(type, account.type)) return false;
        return Objects.equals(currency, account.currency);
    }

    @Override
    public int hashCode() {
        int result;
        result = type != null ? type.hashCode() : 0;
        result = 31 * result + Double.hashCode(balance);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}