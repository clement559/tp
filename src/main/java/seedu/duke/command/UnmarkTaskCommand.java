package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class UnmarkTaskCommand extends Command{
    public static final String KEYWORD = "unmark";
    private int index;
    private boolean isDone;

    public UnmarkTaskCommand(String[] command){
        index = Integer.parseInt(command[1]) - 1;
        isDone = command[0].equals("mark");
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.getTask(index);
        task.setDone(isDone);
        ui.printUnmarkTaskNotification(task);
    }
}
