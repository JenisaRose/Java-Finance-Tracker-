import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Transaction {
    private String description;
    private double amount;
    private String type;

    public Transaction(String description, double amount, String type) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + ": " + description + " (" + (type.equals("Expense") ? "-" : "+") + String.format("%.2f", amount) + ")";
    }
}

public class FinanceTracker {
    private List<Transaction> transactions = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addTransaction(String type) {
        System.out.println("Enter description:");
        String description = scanner.nextLine();
        System.out.println("Enter amount:");
        double amount = 0;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Transaction aborted.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive. Transaction aborted.");
            return;
        }

        Transaction transaction = new Transaction(description, amount, type);
        transactions.add(transaction);
        System.out.println(type + " added successfully.");
    }

    public void viewBalance() {
        double balance = 0;
        for (Transaction t : transactions) {
            if (t.getType().equals("Income")) {
                balance += t.getAmount();
            } else if (t.getType().equals("Expense")) {
                balance -= t.getAmount();
            }
        }
        System.out.println("\n--- Financial Summary ---");
        System.out.println("Total Balance: $" + String.format("%.2f", balance));
        System.out.println("-------------------------\n");
    }

    public void viewHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }
        System.out.println("\n--- Transaction History ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("---------------------------\n");
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Balance");
            System.out.println("4. View History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTransaction("Income");
                    break;
                case "2":
                    addTransaction("Expense");
                    break;
                case "3":
                    viewBalance();
                    break;
                case "4":
                    viewHistory();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting Finance Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        FinanceTracker tracker = new FinanceTracker();
        tracker.run();
    }
}
