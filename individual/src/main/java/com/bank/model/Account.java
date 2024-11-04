package com.bank.model;

import java.io.Serializable;

/**
 * Represents a bank account with a specific type and balance.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;      // Type of the account (e.g., Savings, Checking)
    private double balance;   // Current balance of the account

    /**
     * Default constructor.
     */
    public Account() {
    }

    /**
     * Parameterized constructor to initialize Account with type and balance.
     *
     * @param type    The type of the account.
     * @param balance The initial balance of the account.
     */
    public Account(String type, double balance) {
        this.type = type;
        this.balance = balance;
    }

    /**
     * Gets the type of the account.
     *
     * @return The account type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the account.
     *
     * @param type The account type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the current balance of the account.
     *
     * @return The account balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the current balance of the account.
     *
     * @param balance The account balance to set.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Provides a string representation of the Account object.
     *
     * @return A string detailing the account type and balance.
     */
    @Override
    public String toString() {
        return "Account{" +
                "type='" + type + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * Checks equality based on account type and balance.
     *
     * @param o The object to compare with.
     * @return True if both accounts have the same type and balance, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        return type != null ? type.equals(account.type) : account.type == null;
    }

    /**
     * Generates a hash code based on account type and balance.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type != null ? type.hashCode() : 0;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
