// GomezPaulEvent.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un evento con tareas asociadas.
 */
public class GomezPaulEvent {
    // Título del evento
    private String title;
    // Fecha del evento
    private LocalDate date;
    // Prioridad del evento
    private Priority priority;
    // Lista de tareas asociadas al evento
    private List<GomezPaulEventTask> tasks;

    // Enumeración que define los niveles de prioridad
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    // Constructor para inicializar un evento con título, fecha y prioridad
    public GomezPaulEvent(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>(); // Inicializa la lista de tareas vacía
    }

    // Método para agregar una tarea al evento
    public void addTask(GomezPaulEventTask task) {
        tasks.add(task);
    }

    // Método para obtener la lista de tareas del evento
    public List<GomezPaulEventTask> getTasks() {
        return tasks;
    }

    // Método para representar el evento como una cadena de texto
    @Override
    public String toString() {
        // Contar las tareas completadas
        long completedTasks = tasks.stream().filter(GomezPaulEventTask::isCompleted).count();
        // Construir la representación del evento
        return "Evento: " + title + ", Fecha: " + date + ", Prioridad: " + priority +
                ", Tareas Completadas: " + completedTasks + "/" + tasks.size();
    }
}