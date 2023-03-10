package seedu.duke.command;

import seedu.duke.ui.Ui;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }

    public String[] parseCommand(String userInput) throws IndexOutOfBoundsException {
        String userCommand = userInput.split(" ", 2)[0];
        String providedDescription = "";
        String providedDeadline = "";
        try {
            switch (userCommand) {
            case "list":
                // Fallthrough
            case "bye":
                break;
            case "mark":
                // Fallthrough
            case "unmark":
                providedDescription = userInput.split(" ", 2)[1];
                break;
            case "add":
                String tempInfo = userInput.split(" ", 2)[1];
                providedDescription = tempInfo.split(" /by ")[0];
                providedDeadline = formatDateTime(tempInfo.split(" /by ")[1]);
                break;
            case "editdeadline":
                providedDescription = userInput.split(" ", 3)[1];
                providedDeadline = formatDateTime(userInput.split(" ", 3)[2]);
                break;
            default:
                userCommand = "unknown command";
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            userCommand = "param error";
        }
        String[] parsedCommand = {userCommand, providedDescription, providedDeadline};
        return parsedCommand;
    }

    // Adapted from Clement559 iP
    public static String formatDateTime(String date) throws DateTimeParseException {
        try {
            DateTimeFormatter dateTimeFormatterInput = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
            DateTimeFormatter dateTimeFormatterOutput = DateTimeFormatter.ofPattern("LLL dd uuuu HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatterInput);
            String formattedDateTime = dateTime.format(dateTimeFormatterOutput);
            return formattedDateTime;
        } catch (DateTimeParseException e) {
            ui.printDateTimeError();
            return "";
        }
    }
}
