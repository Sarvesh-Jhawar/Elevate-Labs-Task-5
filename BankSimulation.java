import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: " + amount + ", New Balance: " + balance);
        System.out.println(amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrawn: " + amount + ", New Balance: " + balance);
        System.out.println(amount + " withdrawn successfully.");
    }

    public void showBalance() {
        System.out.println("Current balance for account " + accountNumber + " is: " + balance);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction history for account " + accountNumber + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully for " + account.getAccountNumber());
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine();
                    Account newAccount = new Account(name, accountNumber, initialBalance);
                    bank.addAccount(newAccount);
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    String depAccNo = scanner.nextLine();
                    Account depAccount = bank.findAccount(depAccNo);
                    if (depAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depAmount = scanner.nextDouble();
                        scanner.nextLine();
                        depAccount.deposit(depAmount);
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    String witAccNo = scanner.nextLine();
                    Account witAccount = bank.findAccount(witAccNo);
                    if (witAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double witAmount = scanner.nextDouble();
                        scanner.nextLine();
                        witAccount.withdraw(witAmount);
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    String balAccNo = scanner.nextLine();
                    Account balAccount = bank.findAccount(balAccNo);
                    if (balAccount != null) {
                        balAccount.showBalance();
                    }
                    break;

                case 5:
                    System.out.print("Enter account number: ");
                    String histAccNo = scanner.nextLine();
                    Account histAccount = bank.findAccount(histAccNo);
                    if (histAccount != null) {
                        histAccount.printTransactionHistory();
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the Bank Account Simulation. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
