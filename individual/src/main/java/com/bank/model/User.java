package com.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with a username and a list of bank accounts.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;               // Username of the user
    private List<Account> accounts;        // List of the user's bank accounts
    private String email;                  // Email of the user
    private String password;               // Password of the user
    /**
     * Default constructor initializing the accounts list.
     */
    public User() {
        this.accounts = new ArrayList<>();
        this.email = "";
        this.password = "";
        this.username = "";
    }

    /**
     * Parameterized constructor to initialize User with username and accounts.
     *
     * @param username The username of the user.
     * @param accounts The list of accounts associated with the user.
     */
    public User(String username, List<Account> accounts, String email, String password) {
        this.username = username;
        this.accounts = accounts;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username to set for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the list of accounts associated with the user.
     *
     * @return The list of user's accounts.
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Sets the list of accounts associated with the user.
     *
     * @param accounts The list of accounts to set.
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Adds an account to the user's account list.
     *
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Removes an account from the user's account list.
     *
     * @param account The account to remove.
     * @return True if the account was removed, else false.
     */
    public boolean removeAccount(Account account) {
        return this.accounts.remove(account);
    }

    /**
     * Provides a string representation of the User object.
     *
     * @return A string detailing the username and accounts.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
