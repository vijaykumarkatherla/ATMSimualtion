package com.atmsimulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//ATM Simulation class with the main method
public class ATMSimulation {
  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Database db = new Database(); // Database instance for transaction storage

      // HashMap storing multiple ATM accounts with passwords
      Map<String, ATM> accounts = new HashMap<>();
      accounts.put("123456", new ATM("123456", "pass123", 5000.0));
      accounts.put("654321", new ATM("654321", "secure456", 7000.0));
      accounts.put("111111", new ATM("111111", "mypin789", 6000.0));

      // Prompting user to enter account number
      System.out.print("Enter Account Number: ");
      String accNo = scanner.next();

      if (!accounts.containsKey(accNo)) {
          System.out.println("Invalid Account Number!");
          return;
      }

      // Prompting user to enter password
      System.out.print("Enter Password: ");
      String password = scanner.next();

      ATM atm = accounts.get(accNo);
      if (!atm.validatePassword(password)) {
          System.out.println("Incorrect Password! Access Denied.");
          return;
      }

      // ATM menu loop for user interactions
      while (true) {
          System.out.println("\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Check Transactions\n5. Transfer\n6. Exit");
          System.out.print("Choose an option: ");
          int choice = scanner.nextInt();

          try {
              // Handling user choices
              switch (choice) {
                  case 1:
                      System.out.print("Enter Deposit Amount: ");
                      double dep = scanner.nextDouble();
                      atm.deposit(dep);
                      db.saveTransaction(accNo, "Deposited: " + dep);
                      break;
                  case 2:
                      System.out.print("Enter Withdrawal Amount: ");
                      double withdraw = scanner.nextDouble();
                      atm.withdraw(withdraw);
                      db.saveTransaction(accNo, "Withdrawn: " + withdraw);
                      break;
                  case 3:
                      atm.checkBalance();
                      break;
                  case 4:
                      atm.checkTransactions();
                      break;
                  case 5:
                      System.out.print("Enter Recipient Account Number: ");
                      String toAcc = scanner.next();
                      System.out.print("Enter Transfer Amount: ");
                      double transferAmount = scanner.nextDouble();
                      atm.transfer(toAcc, transferAmount, accounts);
                      db.saveTransaction(accNo, "Transferred: " + transferAmount + " to " + toAcc);
                      db.saveTransaction(toAcc, "Received: " + transferAmount + " from " + accNo);
                      break;
                  case 6:
                      System.out.println("Thank you for using the ATM!");
                      scanner.close();
                      return;
                  default:
                      System.out.println("Invalid Choice!");
              }
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage());
          }
      }
  }
}