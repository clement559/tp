package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.Task;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

//@@author RuiShengGit
public class MarkTaskCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_MARK};

    private int index;

    public MarkTaskCommand(HashMap<Flags, String> args) throws InvalidIdException {
        index = ParserUtil.parseId(args.get(Flags.COMMAND_MARK));
        assert index >= 0 : "Invalid index contained in variable";
    }

    public void transferTask(TaskList uncompletedTaskList, TaskList completedTaskList, Ui ui) throws InvalidIdException {
        String description = uncompletedTaskList.getDescription(index);
        LocalDateTime deadline = uncompletedTaskList.getDeadline(index);
        String email = uncompletedTaskList.getEmail(index);
        HashSet<String> tags = uncompletedTaskList.getTags(index);
        completedTaskList.addTask(description, deadline, email, tags, 0);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        String taskString = taskList.deleteTask(index);
        ui.printMarkTaskMessage(taskString);

    }

}
