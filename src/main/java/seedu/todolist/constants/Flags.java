package seedu.todolist.constants;

import java.util.HashMap;

/**
 * Enum that holds all the flags allowed in commands.
 */
public enum Flags {
    // Command flags
    COMMAND_EXIT("exit", true),
    COMMAND_LIST("list", true),
    COMMAND_HISTORY("history", true),
    COMMAND_PROGRESS("progress", true),
    COMMAND_CHECK("check", true),
    COMMAND_TAGS("tags", true),

    COMMAND_ADD("add", false),
    COMMAND_MARK("mark", false),
    COMMAND_UNMARK("unmark", false),
    COMMAND_EDIT("edit", false),
    COMMAND_DELETE("delete", false),
    COMMAND_GET_EMAIL("get_email", false),
    COMMAND_SET_EMAIL("set_email", false),

    // Argument flags
    DESCRIPTION("-desc", false),
    DEADLINE("-due", false),
    EMAIL("-email", false),
    REPEAT("-rep", false),
    TAG("-tag", false);

    private static final HashMap<String, Flags> map = new HashMap<>();
    private final String name;
    private final boolean canBeEmpty;

    /**
     * Constructs the flags enum.
     *
     * @param name Allowed flag in a command.
     * @param canBeEmpty Whether this flag expects an argument after it.
     *                   True means an argument can still be provided after the flag, but it may not be used.
     */
    Flags(String name, boolean canBeEmpty) {
        this.name = name;
        this.canBeEmpty = canBeEmpty;
    }

    public String getName() {
        return name;
    }

    public boolean canBeEmpty() {
        return canBeEmpty;
    }

    static {
        for (Flags flag : Flags.values()) {
            map.put(flag.name, flag);
        }
    }

    public static Flags fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }
}
