package com.bank.dao;

import com.bank.model.Account;
import com.bank.model.User;
import com.bank.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.bank.dao.UserDAO.getUserIdByUsername;


//write AccountDAO class here
public class AccountDAO {
    public static List<Account> getAccountsByUser(User user) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, getUserIdByUsername(user.getUsername()));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account();
                    account.setBalance(rs.getDouble("balance"));
                    account.setType(rs.getString("type"));
                    account.setCurrency(rs.getString("currency"));
                    accounts.add(account);
                }
            }
        }
        return accounts;
    }
    //write deleteAccount method here
    public static void deleteAccount(Account account) throws SQLException {
        String sql = "DELETE FROM accounts WHERE type = ? AND balance = ? AND currency = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getType());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getCurrency());
            stmt.executeUpdate();
        }
    }
    //write updateAccount method here
    public static void updateAccount(Account account) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE type = ? AND currency = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getType());
            stmt.setString(3, account.getCurrency());
            stmt.executeUpdate();
        }
    }
    //write createAccount method here
    public static void createAccount(Account account, User user) throws SQLException {
        String sql = "INSERT INTO accounts (type, balance, currency, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getType());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getCurrency());
            stmt.setInt(4, getUserIdByUsername(user.getUsername()));
            stmt.executeUpdate();
        }
    }

    public static void deleteAccountsByUser(User user) throws SQLException {
        String sql = "DELETE FROM accounts WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, getUserIdByUsername(user.getUsername()));
            stmt.executeUpdate();
        }
    }
}