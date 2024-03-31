import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser a class to convert user commands into understandable command
 */
public class Parser {
    /**
     * getArray is a function to convert command into 2 arrays
     *
     * @param line command
     * @return array[0] and array[1], description and detail
     */
    public static String[] getArray(String line) {
        return line.split(" ");
    }

    /**
     * getScanner to read the commands entered by user
     * @param list list of task
     */
    public static void getScanner(ArrayList<Task> list) {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            line = in.nextLine();
            processCommand(line, list);
            if (line.equals("bye")) {
                break;
            }
        }
    }

    /**
     * processCommand processes user command and catches invalid cccommands
     * @param line the user command
     * @param list list of tasks
     */


    public static void processCommand(String line, ArrayList<Task> list) {
        if (!line.equals("bye")) {
            try {
                processUserInput(list, line);
            }
            catch (IllegalArgumentException e) {
                System.out.println("please enter a valid command");
            }
        }
    }

    /**
     * processUserInput runs function based on user command
     * @param list ther user command
     * @param line list of tasks
     */

    public static void processUserInput(ArrayList<Task> list, String line) {
        String[] array = getArray(line);
        if (array[0].startsWith("mark")) {
            TaskList.commandMark(array, list);
        } else if (array[0].startsWith("unmark")) {
            TaskList.commandUnmark(array, list);
        } else if (array[0].startsWith("todo")) {
            try {
                TaskList.commandTodo(list, line);
            } catch (DukeException e) {
                System.out.println("please enter description");
            }
        } else if (array[0].startsWith("deadline")) {
            TaskList.commandLine(list, line);
        } else if (array[0].startsWith("event")) {
            TaskList.commandEvent(list, line);
        } else if (line.equals("list")) {
            int n = list.size();
            TaskList.commandList(list, n);
        } else if (array[0].startsWith("delete")) {
            TaskList.commandDelete(array, list);
        } else if (array[0].startsWith("find")) {
            TaskList.commandFind(array, list);
        } else {
            throw new IllegalArgumentException();
        }
        Storage.saveTasks(list);
    }
}






