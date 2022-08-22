import java.io.Console;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public class CommandHandler {
    private static CommandHandler instance;
    private Stack<Character[][]> stack;
    final private Drawer drawer;
    final private Console console;
    final private Scanner scanner;

    private CommandHandler(Console console, Scanner scanner, Drawer drawer) {
        this.console = console;
        this.scanner = scanner;
        this.drawer = drawer;
        this.stack = new Stack<>();
    };

    public static CommandHandler getInstance(Console console, Scanner scanner, Drawer drawer) {
        if (instance == null) {
            instance = new CommandHandler(console, scanner, drawer);
        }
        return instance;
    }

    public void start() {
        String input = null;
        do {
            console.printf("enter command: ");
            input = scanner.nextLine();
            if (input == null || input.isEmpty()) {
                continue;
            }
            String[] commands = input.split("\s+");
            if (!isValidInputFormat(commands)) {
                console.printf("No valid command found; please retry.");
                continue;
            }
            Command command = Command.fromString(commands[0]);
            try {
                switch (Objects.requireNonNull(command)) {
                    case CANVAS -> {
                        handleCreateCanvas(commands);
                    }
                    case LINE -> {
                        handleDrawLine(commands);
                    }
                    case RECTANGLE -> {
                        handleDrawRectangle(commands);
                    }
                    case UNDO -> {
                        handleUndo();
                    }
                }
            } catch (NumberFormatException e) {
                console.printf("No parsable number found; please retry.");
            }
        } while (!Objects.equals(input, Command.QUIT.getAbbr()));
    }

    private void handleDrawRectangle(String[] commands) {
        displayCanvas(drawer.drawRectangle(parseInt(commands[1]), parseInt(commands[2]),
                                           parseInt(commands[3]), parseInt(commands[4])), true);
    }

    private void handleDrawLine(String[] commands) {
        displayCanvas(drawer.drawLine(parseInt(commands[1]), parseInt(commands[2]),
                                      parseInt(commands[3]), parseInt(commands[4]),
                        false), true);
    }

    private void handleCreateCanvas(String[] commands) {
        displayCanvas(drawer.createCanvas(parseInt(commands[1]), parseInt(commands[2])), true);
    }

    private static boolean isValidInputFormat(String[] commands) {
        return (commands.length == 1 && commands[0].equals("Q") ||
                commands.length == 3 && commands[0].equals("C") ||
                commands.length == 5 && (commands[0].equals("L") || commands[0].equals("R")) ||
                commands.length == 1 && commands[0].equals("U"));
    }

    private void displayCanvas(Character[][] canvas, boolean stackIt) {
        if (canvas == null) {
            return;
        }
        for (Character[] element : canvas) {
            for (int j = 0; j < canvas[0].length; j++) {
                console.printf(element[j] + (j == canvas[0].length - 1 ? "\n" : ""));
            }
        }
        if (stackIt) {
            stack.push(deepCopy(canvas));
        }
    }

    private void handleUndo() {
        if (stack.size() > 1) {
            stack.pop();
            drawer.setCanvas(stack.peek());
            displayCanvas(stack.peek(), false);
        } else if (stack.size() == 1) {
            stack.pop();
            drawer.reset();
        }
    }

    private <T> T[][] deepCopy(T[][] matrix) {
        return stream(matrix).map(e -> e.clone()).toArray($ -> matrix.clone());
    }
}
