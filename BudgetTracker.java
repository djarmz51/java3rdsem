import java.util.Scanner;

class Expense {
    String category;
    double amount;

    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
}

public class BudgetTracker {
    private static final int MAX_EXPENSES = 100;
    private static final int MAX_CATEGORIES = 10;
    private Expense[] expenses;
    private int numExpenses;
    private String[] categories;
    private double[] categoryTotals;

    public BudgetTracker() {
        expenses = new Expense[MAX_EXPENSES];
        numExpenses = 0;
        categories = new String[MAX_CATEGORIES];
        categoryTotals = new double[MAX_CATEGORIES];
    }

    public void addExpense(String category, double amount) {
        if (numExpenses < MAX_EXPENSES) {
            expenses[numExpenses++] = new Expense(category, amount);
            updateCategoryTotal(category, amount);
            System.out.println("Expense added successfully.");
        } else {
            System.out.println("Cannot add more expenses. Limit reached.");
        }
    }

    private void updateCategoryTotal(String category, double amount) {
        for (int i = 0; i < MAX_CATEGORIES; i++) {
            if (categories[i] == null) {
                categories[i] = category;
                categoryTotals[i] = amount;
                return;
            } else if (categories[i].equals(category)) {
                categoryTotals[i] += amount;
                return;
            }
        }
    }

    public void viewTotalExpenses() {
        double total = 0;
        for (int i = 0; i < numExpenses; i++) {
            total += expenses[i].amount;
        }
        System.out.println("Total expenses: $" + total);
    }

    public void viewExpenseBreakdown() {
        System.out.println("Expense Breakdown:");
        for (int i = 0; i < MAX_CATEGORIES && categories[i] != null; i++) {
            System.out.println(categories[i] + ": $" + categoryTotals[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BudgetTracker budgetTracker = new BudgetTracker();

        while (true) {
            System.out.println("\n1. Add Expense\n2. View Total Expenses\n3. View Expense Breakdown\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter expense category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter expense amount: ");
                    double amount = scanner.nextDouble();
                    budgetTracker.addExpense(category, amount);
                    break;
                case 2:
                    budgetTracker.viewTotalExpenses();
                    break;
                case 3:
                    budgetTracker.viewExpenseBreakdown();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
