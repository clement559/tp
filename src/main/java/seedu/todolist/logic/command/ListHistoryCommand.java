package seedu.todolist.logic.command;

import seedu.todolist.task.Task;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

/**
 * Command for displaying the current task list.
 */
public class ListHistoryCommand extends Command {
    /**
     * Displays the current task list.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.printTaskList(taskList.size(), taskList.toString(Task.deadlineComparator));
    }
}