// GomezPaulEvent.java
/**
 * Clase que representa un evento con tareas asociadas
 */
public class GomezPaulEvent {
    private String title;
    private LocalDate date;
    private Priority priority;
    private List<GomezPaulEventTask> tasks;

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public GomezPaulEvent(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>();
    }

    public void addTask(GomezPaulEventTask task) {
        tasks.add(task);
    }

    public List<GomezPaulEventTask> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        long completedTasks = tasks.stream().filter(t -> t.isCompleted).count();
        return "Evento: " + title + ", Fecha: " + date + ", Prioridad: " + priority +
                ", Tareas Completadas: " + completedTasks + "/" + tasks.size();
    }
}