package com.atmsimulation;

import java.util.Map;

class ATM extends BankAccount {
    public ATM(String accountNumber, String password, double balance) {
        super(accountNumber, password, balance); // Calls parent constructor to initialize account
    }

    // Deposits a specified amount into the account
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // Increases balance
            transactionHistory.addTransaction("Deposited: " + amount); // Logs transaction
            System.out.println("Amount Deposited Successfully!");
        } else {
            System.out.println("Invalid Deposit Amount!");
        }
    }

    // Withdraws a specified amount from the account if balance is sufficient
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount; // Deducts from balance
            transactionHistory.addTransaction("Withdrawn: " + amount); // Logs transaction
            System.out.println("Amount Withdrawn Successfully!");
        } else {
            System.out.println("Insufficient Balance or Invalid Amount!");
        }
    }

    // Displays the current balance of the account
    @Override
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Displays transaction history
    @Override
    public void checkTransactions() {
        transactionHistory.displayTransactions();
    }

    // Transfers money to another account if valid
    @Override
    public void transfer(String toAccount, double amount, Map<String, ATM> accounts) {
        if (accounts.containsKey(toAccount) && amount > 0 && amount <= balance) {
            balance -= amount; // Deducts from sender's balance
            accounts.get(toAccount).balance += amount; // Adds to recipient's balance
            transactionHistory.addTransaction("Transferred: " + amount + " to " + toAccount);
            accounts.get(toAccount).transactionHistory.addTransaction("Received: " + amount + " from " + accountNumber);
            System.out.println("Transfer Successful!");
        } else {
            System.out.println("Invalid Transfer!");
        }
    }

    // Method to validate password
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}