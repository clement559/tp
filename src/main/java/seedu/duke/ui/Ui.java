package seedu.duke.ui;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class Ui {
    public Ui() {
    }

    public void printWelcomeMessage() {
        System.out.println("Hello, I am Duke and I will help you remember the tasks you need to do!");
        System.out.println("For now, you can do the following:");
        System.out.println("Add a task using: add <description> -d <deadline>");
        System.out.println("List all tasks using: list");
        System.out.println("Mark a task as done using: mark <index>");
        System.out.println("Mark a task as undone using: unmark <index>");
        System.out.println("Edit a task deadline using: editdeadline <index> -d <deadline>");
        System.out.println("Exit the program using: bye");
        System.out.println();
    }

    public void printGoodbyeMessage() {
        System.out.print("See you again, bye!");
    }

    public void printAddTaskNotification(Task task) {
        System.out.println("Got it, I have added the following task:");
        System.out.println(task.toString());
    }

    public void printMarkTaskNotification(Task task) {
        System.out.println("Got it, I have marked the following task as done:");
        System.out.println(task.toString());
    }

    public void printUnmarkTaskNotification(Task task) {
        System.out.println("Got it, I have marked the following task as undone:");
        System.out.println(task.toString());
    }

    public static void printEditDeadlineNotification(String taskItem) {
        System.out.println("Okay, I have edited the deadline for the following task:");
        System.out.println(taskItem);
    }

    public void printDeleteTaskNotification(TaskList taskList, int index) {
        System.out.println("Noted. I've removed this task:");
        Task task = taskList.getTask(index);
        System.out.println(task.toString());
    }

    public void listTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            printEmptyList();
        } else {
            System.out.println("Here are the tasks in your list:");
            taskList.sortTaskList();
            System.out.println(taskList);
        }
    }

    public void printEmptyList(){
        System.out.println("There are no tasks in the list.");
    }


    public void printErrorMessage() {
        System.out.println("Error encountered! Please type in a valid command!");
    }

    public void printDateTimeError() {
        System.out.println("The date provided must be of the following format: dd-MM-yyyy HH:MM.");
    }

    public static void printIndexError() {
        System.out.println("You provided an invalid index.");
    }
    public static void printParametersError() {
        System.out.println("You did not provide the correct parameters for this function.");
    }
    public void printSavingErrorMessage() {
        System.out.println("Error encountered while saving!");
    }

    public void printFileNotFoundMessage() {
        System.out.println("No save file was found. Creating a new list and save file 'data.txt' for you to use.");
    }

    public void printLoadingErrorMessage() {
        System.out.println("Error encountered while loading your saved file! Creating a new list for you to use.");
    }
}
