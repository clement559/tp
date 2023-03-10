package seedu.duke.command;
import org.junit.jupiter.api.Test;
import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.fail;

public class EditDeadlineCommandTest {
    private static TaskList testList = new TaskList();
    private final String DATE_EXAMPLE = "06-04-2000";
    private final String TIME_EXAMPLE = "23:59";
    private final String KEYWORD = "editdeadline";
    private final String SPLITTER = "-d";
    private Ui ui = new Ui();
    @Test
    public void editDeadline_charsindex_throwsException() {
        final String[] invalidIndexes = {"", "]", "wq"};
        generateTestList();
        for (String index : invalidIndexes) {
            String[] testString = {KEYWORD, index, SPLITTER, DATE_EXAMPLE, TIME_EXAMPLE};
            assertConstructingInvalidEditCmdThrowsException(testString);
        }
    }

    @Test
    public void editDeadline_invaliddate_throwsException() {
        final String[] invalidDates = {"03/02/2032", "493430", "2013-12-22", "1023-43-22"};
        generateTestList();
        for (String date : invalidDates) {
            String[] testString = {KEYWORD, "0", SPLITTER, date, TIME_EXAMPLE};
            assertConstructingInvalidEditCmdThrowsException(testString);
        }
    }

    @Test
    public void editDeadline_invalidtime_throwsException() {
        final String[] invalidTimes = {"1000", "493430", "9AM"};
        generateTestList();
        for (String time : invalidTimes) {
            String[] testString = {KEYWORD, "0", SPLITTER, DATE_EXAMPLE, time};
            assertConstructingInvalidEditCmdThrowsException(testString);
        }
    }


    /**
     * Asserts that attempting to construct an edit deadline command with invalid
     * index number or date or time throws an IndexOutOfBoundsException, DateTimeParseException
     */
    private void assertConstructingInvalidEditCmdThrowsException(String[] testString) {
        try {
            Command testEdit = new EditDeadlineCommand(testString);
            testEdit.execute(testList, ui);
        } catch (IndexOutOfBoundsException e) {
            return;
        } catch (NumberFormatException e) {
            return;
        }catch (DateTimeParseException e) {
            return;
        }
        String error = String.format("An edit command was successfully constructed with invalid input: %s %s %s ",
                testString[1], testString[3], testString[4]);
        fail(error);
    }

    private void generateTestList() {
        String[] testInput = {"add", "Test", "Input", "-d", "02-02-2023", "18:00"};
        Command addTask = new AddTaskCommand(testInput);
        addTask.execute(testList,ui);
    }

}
