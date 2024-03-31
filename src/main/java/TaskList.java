import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * TaskList controls list
 */

public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;
    private Parser parser;

    /**
     * TaskList takes in the list of items and stores them in storage as well as reads command
     * @param list item
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        Storage.newFile(list);
        Parser.getScanner(list);
    }


    /**
     * as X
     * @param array the command split by space
     * @param list list of items
     */
    public static void commandMark(String[] array, ArrayList<Task> list) {
        list.get(Integer.parseInt(array[1]) - 1).setDone();
        System.out.print(list.get(Integer.parseInt(array[1]) - 1).taskDescription());
        System.out.println("");
    }

    /**
     * undo x
     * @param array the command split by spacce
     * @param list list
     */

    public static void commandUnmark(String[] array, ArrayList<Task> list) {
        list.get(Integer.parseInt(array[1]) - 1).setNotDone();
        System.out.print(list.get(Integer.parseInt(array[1]) - 1).taskDescription());
        System.out.println("");
    }

    /**
     * commandTodo an item as todo
     * @param list list of item
     * @param line command
     * @throws DukeException when todo description not provoded
     */

    public static void commandTodo(ArrayList<Task> list, String line)  throws DukeException {
        try {
            String valid = new String (line.substring(6));
            ToDo newtodo = new ToDo(line.substring(5));
            list.add(newtodo);
            System.out.println(newtodo);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("please enter a description");
        }
    }


    /**
     * Line due
     * @param list lsit of items
     * @param line command
     */
    public static void commandLine(ArrayList<Task> list, String line) {
        Line newline = new Line(line.substring(9, line.indexOf("/") -1), line.substring(line.indexOf("/") + 4));
        list.add(newline);
        System.out.println(newline);
    }

    /**
     * Event an event with a period
     * @param list list of item
     * @param line command
     */

    public static void commandEvent(ArrayList<Task> list, String line) {
        String lengthy = line.substring(0, line.indexOf("/") + 1);
        int lengths = lengthy.length();
        Event newevent = new Event(line.substring(6, line.indexOf("/") -1),
                line.substring(line.indexOf("/") + 6, (line.indexOf("/",
                        lengths) - 1)), line.substring(line.indexOf("/", lengths) + 4));
        list.add(newevent);
        System.out.println(newevent);
    }

    /**
     * list all items including their status in the list
     * @param list list
     * @param n numberof item
     */

    public static void commandList(ArrayList<Task> list, int n) {
        for (int l = 0; l <n ; l++) {
            System.out.println((l + 1) + "." + list.get(l).taskDescription());
        }
    }

    /**
     * delete an item
     * @param array command split
     * @param list command
     */

    public static void commandDelete(String[] array, ArrayList<Task> list) {
        int commandIndex = Integer.parseInt(array[1]) - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + list.get(commandIndex).taskDescription());
        list.remove(commandIndex);
    }


    /**
     * finds all items with the corresponding description
     * @param array commands
     * @param list cpmmand
     */
    public static void commandFind(String [] array,  ArrayList<Task> list) {
        String find = array[1];
        int number = 0;
        ArrayList<Task> taskReturn = new ArrayList<Task>();
        for (Task task : list) {
            String description = task.taskDescription();
            String tasks;
            if (description.contains("[T]")) {
                tasks = description.substring(description.indexOf("]"));
            } else {
                tasks = description.substring((description.indexOf("]")), (description.indexOf("(")));
            }
            if (tasks.contains(find)) {
                taskReturn.add(task);
                number++;
            }
        }
        for (int i = 0; i < number; i ++) {
            System.out.println((i + 1) + "." + taskReturn.get(i).taskDescription());
        }
    }
}
