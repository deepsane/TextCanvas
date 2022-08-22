public enum Command {
    CANVAS("C"),
    LINE("L"),
    QUIT("Q"),
    RECTANGLE("R"),
    UNDO("U");

    private String abbr;

    Command(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return this.abbr;
    }

    public static Command fromString(String text) {
        for (Command command : Command.values()) {
            if (command.getAbbr().equalsIgnoreCase(text)) {
                return command;
            }
        }
        return null;
    }
}
