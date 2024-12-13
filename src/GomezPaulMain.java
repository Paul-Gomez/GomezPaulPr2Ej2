// GomezPaulMain.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para gestionar la aplicación Event Planner
 */
public class GomezPaulMain {
    // Lista para almacenar los eventos registrados
    private static List<GomezPaulEvent> events = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = -1; // Variable para almacenar la opción del menú

        // Bucle principal del menú
        do {
            System.out.println("Bienvenido a Event Planner. Seleccione una opción:");
            System.out.println("[1] Añadir evento");
            System.out.println("[2] Borrar evento");
            System.out.println("[3] Listar eventos");
            System.out.println("[4] Marcar/desmarcar tarea de un evento como completada");
            System.out.println("[5] Salir");

            try {
                // Leer y convertir la opción seleccionada por el usuario
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Intente nuevamente.");
                continue; // Volver al inicio del bucle si hay error
            }

            // Ejecutar la opción seleccionada
            switch (option) {
                case 1:
                    addEvent(scanner);
                    break;
                case 2:
                    deleteEvent(scanner);
                    break;
                case 3:
                    listEvents();
                    break;
                case 4:
                    toggleTaskCompletion(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo de Event Planner. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 5); // Continuar mientras no se seleccione la opción de salir
    }

    // Método para añadir un nuevo evento
    private static void addEvent(Scanner scanner) {
        System.out.println("Añadir nuevo evento");

        // Solicitar título del evento
        System.out.print("Título del evento: ");
        String title = scanner.nextLine();

        // Solicitar fecha del evento
        System.out.print("Año: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Mes: ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Día: ");
        int day = Integer.parseInt(scanner.nextLine());

        LocalDate date;
        try {
            // Crear objeto LocalDate con los datos ingresados
            date = LocalDate.of(year, month, day);
        } catch (Exception e) {
            System.out.println("Fecha no válida. Intente nuevamente.");
            return;
        }

        // Solicitar prioridad del evento
        System.out.print("Prioridad (HIGH, MEDIUM, LOW): ");
        GomezPaulEvent.Priority priority;
        try {
            priority = GomezPaulEvent.Priority.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Prioridad no válida. Intente nuevamente.");
            return;
        }

        // Crear objeto evento
        GomezPaulEvent event = new GomezPaulEvent(title, date, priority);

        // Preguntar si desea agregar tareas
        System.out.println("¿Desea agregar tareas? (sí/no)");
        String response = scanner.nextLine();
        while (response.equalsIgnoreCase("sí")) {
            System.out.print("Descripción de la tarea: ");
            String taskText = scanner.nextLine();
            event.addTask(new GomezPaulEventTask(taskText)); // Añadir tarea al evento
            System.out.println("¿Agregar otra tarea? (sí/no)");
            response = scanner.nextLine();
        }

        events.add(event); // Agregar evento a la lista
        System.out.println("Evento agregado correctamente.");
    }

    // Método para borrar un evento
    private static void deleteEvent(Scanner scanner) {
        System.out.print("Ingrese el título del evento a borrar: ");
        String title = scanner.nextLine();
        // Buscar y eliminar evento por título
        boolean removed = events.removeIf(e -> e.toString().contains(title));
        System.out.println(removed ? "Evento borrado correctamente." : "Evento no encontrado.");
    }

    // Método para listar todos los eventos
    private static void listEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos registrados.");
        } else {
            events.forEach(System.out::println); // Imprimir información de cada evento
        }
    }

    // Método para marcar o desmarcar una tarea como completada
    private static void toggleTaskCompletion(Scanner scanner) {
        System.out.print("Ingrese el título del evento: ");
        String title = scanner.nextLine();

        // Buscar el evento por título
        GomezPaulEvent event = events.stream()
                .filter(e -> e.toString().contains(title))
                .findFirst()
                .orElse(null);

        if (event == null) {
            System.out.println("Evento no encontrado.");
            return;
        }

        System.out.println("Tareas del evento:");
        List<GomezPaulEventTask> tasks = event.getTasks();

        // Listar tareas del evento
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        // Solicitar selección de tarea
        System.out.print("Seleccione el número de la tarea para marcar/desmarcar: ");
        int taskIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            System.out.println("Número de tarea no válido.");
            return;
        }

        // Marcar o desmarcar tarea
        GomezPaulEventTask task = tasks.get(taskIndex);
        if (task.isCompleted()) {
            task.markUncompleted();
            System.out.println("Tarea desmarcada como completada.");
        } else {
            task.markCompleted();
            System.out.println("Tarea marcada como completada.");
        }
    }
}

