package com.atmsimulation;
import java.util.*;

//Class to manage transactions
class Transactions {
 private List<String> transactions; // List to store transaction history

 public Transactions () {
     this.transactions = new ArrayList<>();
 }

 // Method to add a transaction to history
 public void addTransaction(String detail) {
     transactions.add(detail);
 }

 // Method to display transaction history
 public void displayTransactions() {
     if (transactions.isEmpty()) {
         System.out.println("No transactions available.");
     } else {
         for (String transaction : transactions) {
             System.out.println(transaction);
         }
     }
 }
}



