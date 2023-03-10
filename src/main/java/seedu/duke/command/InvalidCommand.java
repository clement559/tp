package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

/**
 * Command generated and run when there is an error with parsing the command.
 */
public class InvalidCommand extends Command {
    /**
     * Displays a generic error message.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.printErrorMessage();
    }
}
