import java.util.ArrayList;

/**
 * Avocado class is The main function for chatbot Avocado
 */
public class Avocado {
    public static ArrayList<Task> list = new ArrayList<>();

    /**
     * main class to print chatbot
     * @param args argument in
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcomeMessage();
        TaskList tasklist = new TaskList(list);
    }
}