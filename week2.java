import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {
    private ArrayList<String> todoList = new ArrayList<>();

    public void displayList() {
        if (todoList.isEmpty()) {
            System.out.println("Your to-do list is empty.");
        } else {
            System.out.println("Your to-do list:");
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println(i + 1 + ". " + todoList.get(i));
            }
        }
    }

    public void addTask(String task) {
        todoList.add(task);
        System.out.println("Task added: " + task);
    }

    public void removeTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < todoList.size()) {
            String removedTask = todoList.remove(taskIndex);
            System.out.println("Task removed: " + removedTask);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    public static void main(String[] args) {
        TodoListApp todoListApp = new TodoListApp();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display To-Do List");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        todoListApp.displayList();
                        break;
                    case 2:
                        System.out.print("Enter the task to add: ");
                        String newTask = scanner.nextLine();
                        todoListApp.addTask(newTask);
                        break;
                    case 3:
                        System.out.print("Enter the task index to remove: ");
                        int indexToRemove = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        todoListApp.removeTask(indexToRemove - 1);
                        break;
                    case 4:
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Consume the invalid input
                choice = 0; // Reset choice to prevent an infinite loop
            }

        } while (choice != 4);

        scanner.close();
    }
}
