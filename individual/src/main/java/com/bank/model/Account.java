package com.bank.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String currency;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    // Конструкторы
    public Account() { }
    public Account(String type, double balance, String currency) {
        this.type = type;
        this.balance = balance;
        this.currency = currency;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
