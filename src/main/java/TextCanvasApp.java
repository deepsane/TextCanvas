import java.util.Scanner;
import java.io.Console;

public class TextCanvasApp {
    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.out.println("Console is not supported in IJ");
            System.exit(1);
        }
        Drawer drawer = Drawer.getInstance();
        Scanner scanner = new Scanner(System.in);
        CommandHandler handler = CommandHandler.getInstance(console, scanner, drawer);
        handler.start();
    }
}
