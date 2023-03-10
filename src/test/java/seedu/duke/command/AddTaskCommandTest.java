package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.fail;

class AddTaskCommandTest {
    private static TaskList testList = new TaskList();
    private final  String DATE_EXAMPLE = "06-04-2000";
    private final  String TIME_EXAMPLE = "23:59";
    private final String KEYWORD = "add";
    private final String SPLITTER = "-d";
    private Ui ui = new Ui();
    @Test
    public void addTask_emptydescription_throwsException() {
        final String emptyDescription = "";
        String[] testString = {KEYWORD, emptyDescription, SPLITTER, DATE_EXAMPLE, TIME_EXAMPLE};
        assertConstructingEmptyDescriptionAddCmdThrowsException(testString);
    }

    @Test
    public void addTask_invaliddate_throwsException() {
        final String[] invalidDates = {"03/02/2032", "493430", "2013-12-22", "1023-43-22"};
        for (String date : invalidDates) {
            String[] testString = {KEYWORD, "0", SPLITTER, date, TIME_EXAMPLE};
            assertConstructingInvalidAddDateTimeCmdThrowsException(testString);
        }
    }

    @Test
    public void addTask_invalidtime_throwsException() {
        final String[] invalidTimes = {"1000", "493430", "9AM"};
        for (String time : invalidTimes) {
            String[] testString = {KEYWORD, "0", SPLITTER, DATE_EXAMPLE, time};
            assertConstructingInvalidAddDateTimeCmdThrowsException(testString);
        }
    }

    private void assertConstructingInvalidAddDateTimeCmdThrowsException(String[] testString) {
        try {
            Command testAdd = new AddTaskCommand(testString);
            testAdd.execute(testList, ui);
        } catch (NumberFormatException | DateTimeParseException e) {
            return;
        }
        String error = String.format("An edit command was successfully constructed with invalid input: %s %s %s ",
                testString[1], testString[3], testString[4]);
        fail(error);
    }

    private void assertConstructingEmptyDescriptionAddCmdThrowsException(String[] testString) {
        try {
            Command testAdd = new AddTaskCommand(testString);
            testAdd.execute(testList, ui);
        } catch (NullPointerException e) {
            return;
        }
        String error = String.format("An edit command was successfully constructed with invalid input: %s %s %s ",
                testString[1], testString[3], testString[4]);
        fail(error);
    }
}
