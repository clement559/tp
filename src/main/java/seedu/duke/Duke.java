package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static boolean isInUse = true;

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList;
        ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        try {
            taskList = Storage.loadData("./data.txt", ui);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundMessage();
            taskList = new TaskList();
        } catch (ConversionErrorException e) {
            ui.printLoadingErrorMessage();
            taskList = new TaskList();
        }
        while (isInUse) {
            String userInput = in.nextLine();
            Command parsedCommand = CommandParser.parseCommand(userInput);
            parsedCommand.execute(taskList, ui);
            try {
                Storage.saveData("./data.txt", taskList, ui);
            } catch (IOException e) {
                ui.printSavingErrorMessage();
            }
            if (parsedCommand.isExit()) {
                in.close();
            }
            isInUse = !parsedCommand.isExit();
        }
    }
}

