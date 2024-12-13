// GomezPaulEventTask.java
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Clase que representa una tarea asociada a un evento
 */
public class GomezPaulEventTask {
    // Descripción de la tarea
    private String text;
    // Estado de la tarea: completada o no
    private boolean isCompleted;

    // Constructor para inicializar la tarea con un texto y marcarla como no completada por defecto
    public GomezPaulEventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    // Método para marcar la tarea como completada
    public void markCompleted() {
        this.isCompleted = true;
    }

    // Método para desmarcar la tarea como completada
    public void markUncompleted() {
        this.isCompleted = false;
    }

    // Método para verificar si la tarea está completada
    public boolean isCompleted() {
        return isCompleted;
    }

    // Método para representar la tarea como cadena, mostrando su estado y descripción
    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + text;
    }
}