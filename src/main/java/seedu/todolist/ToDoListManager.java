package seedu.todolist;

import seedu.todolist.exception.FailedLoadException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Parser;
import seedu.todolist.logic.command.*;
import seedu.todolist.storage.Storage;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.List;

public class ToDoListManager {
    private boolean isRunning = true;
    private Parser parser = new Parser();
    private Storage dataStorage = new Storage(Storage.DEFAULT_UNCOMPLETED_TASK_SAVE_PATH);
    private Storage historyStorage = new Storage(Storage.DEFAULT_COMPLETED_TASK_SAVE_PATH);
    private TaskList uncompletedTaskList = new TaskList();
    private TaskList completedTaskList = new TaskList();
    private Ui ui = new Ui();

    public ToDoListManager() {
        ui.printWelcomeMessage();

        if (dataStorage.isNewSave() && historyStorage.isNewSave()) {
            ui.printNewSaveMessage();
            return;
        }
        if (!dataStorage.isNewSave()) {
            try {
                uncompletedTaskList = dataStorage.loadData();
                uncompletedTaskList.checkRepeatingTasks();
                ui.printLoadSaveMessage(uncompletedTaskList.size());
                new ProgressBarCommand().execute(uncompletedTaskList, ui);
            } catch (FailedLoadException e) {
                ui.printError(e);
            }
        }
        if (!historyStorage.isNewSave()) {
            try {
                completedTaskList = historyStorage.loadData();
                ui.printLoadSaveMessage(completedTaskList.size());
            } catch (FailedLoadException e) {
                ui.printError(e);
            }
        }
    }

    public void run() {
        while (isRunning) {
            String inputCommand = ui.getUserInput();
            try {
                Command command = parser.parseCommand(inputCommand);
                if (command instanceof MarkTaskCommand) {
                    ((MarkTaskCommand) command).transferTask(uncompletedTaskList, completedTaskList);
                    command.execute(uncompletedTaskList, ui);
                    dataStorage.saveData(uncompletedTaskList);
                    historyStorage.saveData(completedTaskList);
                } else if (command instanceof UnmarkTaskCommand) {
                    ((UnmarkTaskCommand) command).transferTask(uncompletedTaskList, completedTaskList);
                    command.execute(completedTaskList, ui);
                    dataStorage.saveData(uncompletedTaskList);
                    historyStorage.saveData(completedTaskList);
                } else if (command instanceof ListHistoryCommand) {
                    command.execute(completedTaskList, ui);
                } else {
                    command.execute(uncompletedTaskList, ui);
                    dataStorage.saveData(uncompletedTaskList);
                }
                isRunning = !command.shouldExit();
            } catch (ToDoListException e) {
                ui.printError(e);
            }
        }
        ui.close();
    }
}
