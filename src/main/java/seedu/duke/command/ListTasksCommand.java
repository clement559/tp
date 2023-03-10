package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    public static final String KEYWORD = "list";

    /**
     * Displays the current task list.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.listTasks(taskList);
    }
}
