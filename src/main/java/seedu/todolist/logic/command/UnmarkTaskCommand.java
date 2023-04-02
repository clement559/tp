package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

//@@author RuiShengGit
public class UnmarkTaskCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_UNMARK};

    private int index;

    public UnmarkTaskCommand(HashMap<Flags, String> args) throws InvalidIdException {
        index = ParserUtil.parseId(args.get(Flags.COMMAND_UNMARK));
        assert index >= 0 : "Invalid index contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        String taskString = taskList.deleteTask(index);
        ui.printUnmarkTaskMessage(taskString);
    }
    //@@author clement559
    public void transferTask(TaskList uncompletedTaskList, TaskList completedTaskList) throws InvalidIdException {
        String description = completedTaskList.getDescription(index);
        LocalDateTime deadline = completedTaskList.getDeadline(index);
        String email = completedTaskList.getEmail(index);
        HashSet<String> tags = completedTaskList.getTags(index);
        uncompletedTaskList.addTask(description, deadline, email, tags, 0);
    }
}
