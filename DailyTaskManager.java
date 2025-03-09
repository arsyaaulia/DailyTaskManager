import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class DailyTaskManager{
    static Scanner input = new Scanner(System.in);
    static Stack<String> completedTasks = new Stack<>();
    static LinkedList<String> dynamicTasks = new LinkedList<>();

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    //Fungsi Show Tasks
    public static void showTasks(String[] taskList, int taskCount) {
        if (taskCount == 0) {
            System.out.println("\nNo tasks available.");
            return;
        }
        System.out.println("\nYour Daily Tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". [ ] " + taskList[i]); //gimana maksudnya?
        }
    }
    //Fungsi Update Tasks
    public static void updateTasks(String[] taskList, int taskCount) {
        if (taskCount == 0) {
            System.out.println("No tasks to update.");
            return;
        }
        showTasks(taskList, taskCount);
        System.out.print("\nEnter the task number to update: ");
        int index = input.nextInt() - 1;
        input.nextLine();

        if (index >= 0 && index < taskCount) {
            System.out.print("Enter the new task: ");
            taskList[index] = input.nextLine();
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    //Fungsi Complete Tasks
    public static void completeTasks(String[] taskList, int taskCount) {
        showTasks(taskList, taskCount);
        System.out.println("\nEnter the task number to mark as completeed: ");
        int index = input.nextInt() - 1;
        input.nextLine();

        if (index >= 0 && index < taskCount) {
            completedTasks.push(taskList[index]);
            taskList[index] = "[✓]" + taskList[index];
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    //Fungsi Undo Task Completion
    static void undoTaskCompletion(String[] taskList, int taskCount) {
        if (!completedTasks.isEmpty()) {
            String undoneTask = completedTasks.pop();
            System.out.println("Undo completed task: " +undoneTask);

            for (int i = 0; i < taskCount; i++){
                if (taskList[i].contains(undoneTask)){
                    taskList[i] = undoneTask;
                    break;
                }
            }
        } else {
            System.out.println("No completed tasks to undo");
        }
    }

    //Fungsi Remove Dynamic Task
    static void removeDynamicTask(){
        if (dynamicTasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }
        System.out.println("\nDynamic Tasks: ");
        for (int i = 0; i <dynamicTasks.size(); i++) {
            System.out.println(i + 1 + ". " + dynamicTasks.get(i));
        }

        System.out.println("\nEnter the task number to remove: ");
        int index = input.nextInt() - 1;
        input.nextLine();

        if (index >= 0 && index < dynamicTasks.size()){
            dynamicTasks.remove(index);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
    //Fungsi View Dynamic Tasks
    static void viewDynamicTasks() {
        System.out.println("\nDynamic Task List: ");
        if (dynamicTasks.isEmpty()){
            System.out.println("No tasks available");
        } else {
            for (String task : dynamicTasks){
                System.out.println("- " + task);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\nPreparing your workspace...");
        int totalBlocks = 25;
        for (int i = 0; i <= totalBlocks; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\r" + "█".repeat(i) + "▒".repeat(totalBlocks - i));
        }

        clearScreen();
        System.out.println("╔══════════════════════╗");
        System.out.println("║  Daily Task Manager  ║");
        System.out.println("╚══════════════════════╝");
        System.out.println("Hello! Welcome to Daily Task Manager!");
        System.out.println("Your productivity is our priority <3");

        int kapasitas = 5; //untuk array
        int taskCount = 0;
        String[] taskList = new String[kapasitas];

        while (true) {
            System.out.println("\nAvailable System");
            System.out.println("1. Array Management System (Max store task : 5)");
            System.out.println("2. Linked List Management System (Unlimited)");
            System.out.println("3. Quit");
            System.out.print("Please choose the system you want: ");
            int systemMenu = input.nextInt();
            //Array Menu
            if (systemMenu == 1) {
                while (true) {
                    System.out.println("\nArray System Menu");
                    System.out.println("1. Add new daily task");
                    System.out.println("2. Update Task");
                    System.out.println("3. Complete Tasks");
                    System.out.println("4. Undo Task Complete");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Choose menu: ");
                    int menu = input.nextInt();
                    input.nextLine();

                    if (menu == 1) {
                        System.out.println("\nInput Daily Task (type 'q' to stop)");
                        while (taskCount < kapasitas) {
                            System.out.print("Task " + (taskCount + 1) + ": ");
                            String dailyTask = input.nextLine();
                            if (dailyTask.equals("q")) break;
                            taskList[taskCount++] = dailyTask; //ini maksudnya apa??
                        }
                        showTasks(taskList, taskCount);
                    } else if (menu == 2) {
                        updateTasks(taskList, taskCount);
                    } else if (menu == 3){
                        completeTasks(taskList, taskCount);
                        showTasks(taskList, taskCount);
                    } else if(menu == 4){
                        undoTaskCompletion(taskList, taskCount);
                        showTasks(taskList, taskCount);
                    } else if (menu == 5) {
                        break;
                    } else {
                        System.out.println("Invalid input.");
                    }
                }
            } 
            //LinkedList Menu
            else if (systemMenu == 2) {
                while (true) {
                    System.out.println("\nLinked List Menu");
                    System.out.println("1. Add Task");
                    System.out.println("2. Remove Task");
                    System.out.println("3. Back to main menu"); 
                    System.out.print("Choose menu: ");
                    int menuLinkedList = input.nextInt();
                    input.nextLine();

                    if (menuLinkedList == 1 ){
                        System.out.println("\nInput Daily Task (type 'q' to stop)");

                        while (true) {
                            System.out.print("Task " + (taskCount + 1) + ": ");
                            String newTask = input.nextLine();
                            if (newTask.equals("q")) break;
                            dynamicTasks.add(newTask);
                            taskCount++;
                        }

                        System.out.println("Task added successfully!");
                        viewDynamicTasks();
                    } else if (menuLinkedList == 2){
                        removeDynamicTask();
                    }else if (menuLinkedList == 3){
                        break;
                    }else{
                        System.out.println("Invalid menu selection");
                    }
                }
            } 
            //Quit APP
            else if (systemMenu == 3) {
                System.out.println("Thank you for using this application! ^^\n");
                break;
            } 
            
            else {
                System.out.println("Invalid menu selection.");
            }
        }
        input.close();
    }
}
