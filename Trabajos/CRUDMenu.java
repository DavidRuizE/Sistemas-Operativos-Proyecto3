import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Map.Entry;

public class CRUDMenu {
    public static void main(String[] args) {
        // Cargar datos desde los archivos CSV
        Minero.Database.loadDataFromCSV();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú de Gestión de Robots");
            System.out.println("1. Crear Robot");
            System.out.println("2. Actualizar Robot");
            System.out.println("3. Consultar Robot");
            System.out.println("4. Eliminar Robot");
            System.out.println("5. Listar Eventos de Log");
            System.out.println("6. Listar Estados del Programa");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el tipo de robot (1: Minero, 2: Tren, 3: Extractor): ");
                    int tipoRobot = scanner.nextInt();
                    System.out.print("Ingrese el ID del robot: ");
                    int id = scanner.nextInt();
                    System.out.print("¿Está encendido? (true/false): ");
                    boolean encendido = scanner.nextBoolean();
                    Minero.Database.updateRobotData(id, tipoRobot, encendido);
                    Minero.Database.saveDataToCSV(); // Guardar datos después de crear
                    break;
                case 2:
                    System.out.print("Ingrese el ID del robot a actualizar: ");
                    id = scanner.nextInt();
                    System.out.print("Ingrese el nuevo tipo de robot (1: Minero, 2: Tren, 3: Extractor): ");
                    tipoRobot = scanner.nextInt();
                    System.out.print("¿Está encendido? (true/false): ");
                    encendido = scanner.nextBoolean();
                    Minero.Database.updateRobotData(id, tipoRobot, encendido);
                    Minero.Database.saveDataToCSV(); // Guardar datos después de actualizar
                    break;
                case 3:
                    System.out.print("Ingrese el ID del robot a consultar: ");
                    id = scanner.nextInt();
                    Minero.Database.RobotData data = Minero.Database.getRobotData(id);
                    if (data != null) {
                        System.out.println("Datos del Robot: " + data);
                    } else {
                        System.out.println("Robot no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el ID del robot a eliminar: ");
                    id = scanner.nextInt();
                    Minero.Database.getRobots().remove(id);
                    Minero.Database.saveDataToCSV(); // Guardar datos después de eliminar
                    System.out.println("Robot eliminado.");
                    break;
                case 5:
                    System.out.println("Eventos de Log:");
                    for (Entry<LocalDateTime, Minero.Database.LogEvento> entry : Minero.Database.getLogEventos().entrySet()) {
                        System.out.println("Fecha: " + entry.getKey() + " - Evento: " + entry.getValue());
                    }
                    break;
                case 6:
                    System.out.println("Estados del Programa:");
                    for (Entry<LocalDateTime, Minero.Database.EstadoPrograma> entry : Minero.Database.getEstadoProgramas().entrySet()) {
                        System.out.println("Fecha: " + entry.getKey() + " - Estado: " + entry.getValue());
                    }
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
