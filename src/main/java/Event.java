/**
Event class is a class to store event output
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Event to print event
     * @param description the title of the event
     * @param from the start
     * @param to the fin
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * getFrom gets the start
     * @return start
     */

    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }

    /**
     * toString overrides toString of Task class
     * @return event in required format
     */
    @Override
    public String toString() {
        return " [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String taskDescription() {
        return " [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}