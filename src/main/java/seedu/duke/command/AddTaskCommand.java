package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;



public class AddTaskCommand extends Command{
    public static final String KEYWORD = "add";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD, "-d"));
    private String description;
    private String deadline;


    public AddTaskCommand(String[] splitInput){

        HashMap<String, String> args = CommandParser.getArguments(splitInput, FLAGS);
        description = args.get(KEYWORD);
        deadline = CommandParser.formatDateTime(args.get("-d"));
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new NullPointerException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui){
        /*if (description.isEmpty()) {
            ui.printParametersError();
        }
        else if (deadline.isEmpty()) {
            ui.printDateTimeError();
        }
        else { */
        Task task = new Task(description, deadline);
        taskList.addTask(task);
        ui.printAddTaskNotification(task);
    }
}
