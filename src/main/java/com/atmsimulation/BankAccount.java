package com.atmsimulation;
import java.util.Map;

//Abstract class representing a bank account
abstract class BankAccount {
  protected String accountNumber; // Unique identifier for the account
  protected String password; // Stores the account password
  protected double balance; // Stores the account balance
  protected Transactions transactionHistory; // Tracks transaction history

  // Constructor to initialize account details
  public BankAccount(String accountNumber, String password, double balance) {
      this.accountNumber = accountNumber;
      this.password = password;
      this.balance = balance;
      this.transactionHistory = new Transactions(); // Initializes transaction history
  }

  // Abstract methods to be implemented by subclasses
  public abstract void deposit(double amount);
  public abstract void withdraw(double amount);
  public abstract void checkBalance();
  public abstract void checkTransactions();
  public abstract void transfer(String toAccount, double amount, Map<String, ATM> accounts);
  
}