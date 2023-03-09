package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class MarkTaskCommand extends Command{
    private int index;
    private boolean isDone;
    public static final String KEYWORD = "mark";

    public MarkTaskCommand(String[] command){
        index = Integer.parseInt(command[1]) - 1;
        isDone = command[0].equals("mark");
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.getTask(index);
        task.setDone(isDone);
        ui.printMarkTaskNotification(task);
    }
}
