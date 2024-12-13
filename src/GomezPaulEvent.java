// GomezPaulEventTask.java
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Clase que representa una tarea asociada a un evento
 */
public class GomezPaulEventTask {
    private String text;
    private boolean isCompleted;

    public GomezPaulEventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markUncompleted() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + text;
    }
}