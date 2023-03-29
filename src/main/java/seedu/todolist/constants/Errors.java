package seedu.todolist.constants;

import seedu.todolist.storage.Storage;

/**
 * Enum that holds all the error messages to be displayed.
 */
public enum Errors {
    MISSING_ARGUMENT("Missing argument after: "),

    INVALID_COMMAND("You need to provide a valid command."),
    INVALID_FLAGS("Unknown or duplicate flags are not allowed: "),
    INVALID_ID("Invalid task id: "),
    INVALID_DATE("Invalid date format: "),
    INVALID_EMAIL("Invalid email address: "),
    INVALID_DURATION("Invalid repeat duration: "),
    INVALID_EDIT("At least one new parameter must be provided when editing a task."),

    FAILED_SAVE("Error when saving your data, please check that you have write permissions for "
            + Storage.DEFAULT_UNCOMPLETED_TASK_SAVE_PATH + " and " + Storage.DEFAULT_COMPLETED_TASK_SAVE_PATH),
    FAILED_LOAD("Error when loading your data, please check that you have read permissions for both"
            + Storage.DEFAULT_UNCOMPLETED_TASK_SAVE_PATH + System.lineSeparator()
            + Storage.DEFAULT_COMPLETED_TASK_SAVE_PATH + System.lineSeparator()
            + "and do not modify it yourself. A new task list will be created for you!");


    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
