package gestorPersonas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

	class Ciudadano implements Serializable {
	    private String nombre;
	    private String dni;
	    private int edad;

	    public Ciudadano(String nombre, String dni, int edad) {
	        this.nombre = nombre;
	        this.dni = dni;
	        this.edad = edad;
	    }

	    // Getters y Setters
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getDni() {
	        return dni;
	    }

	    public void setDni(String dni) {
	        this.dni = dni;
	    }

	    public int getEdad() {
	        return edad;
	    }

	    public void setEdad(int edad) {
	        this.edad = edad;
	    }

	    @Override
	    public String toString() {
	        return "Nombre: " + nombre + ", DNI: " + dni + ", Edad: " + edad;
	    }
	}

	public class menu {
	    private static final String FILENAME = "ciudadanos.dat";
	    private static ArrayList<Ciudadano> ciudadanos = new ArrayList<>();

	    public static void main(String[] args) {
	        cargarDatos();

	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n¿Qué quieres hacer?");
	            System.out.println("1. Añadir nuevo ciudadano");
	            System.out.println("2. Imprimir ciudadanos");
	            System.out.println("3. Modificar un ciudadano");
	            System.out.println("4. Eliminar un ciudadano");
	            System.out.println("5. Salir");

	            int opcion = scanner.nextInt();
	            scanner.nextLine(); // Consume la nueva línea después de nextInt()

	            switch (opcion) {
	                case 1:
	                    añadirCiudadano(scanner);
	                    break;
	                case 2:
	                    imprimirCiudadanos();
	                    break;
	                case 3:
	                    modificarCiudadano(scanner);
	                    break;
	                case 4:
	                    eliminarCiudadano(scanner);
	                    break;
	                case 5:
	                    guardarDatos();
	                    System.out.println("¡Hasta luego!");
	                    return;
	                default:
	                    System.out.println("Opción inválida. Inténtalo de nuevo.");
	            }
	        }
	    }

	    private static void cargarDatos() {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
	            ciudadanos = (ArrayList<Ciudadano>) ois.readObject();
	            System.out.println("Datos cargados correctamente.");
	        } catch (FileNotFoundException e) {
	            System.out.println("No se encontró el archivo de datos. Se creará uno nuevo al guardar los datos.");
	        } catch (IOException | ClassNotFoundException e) {
	            System.out.println("Error al cargar los datos: " + e.getMessage());
	        }
	    }

	    private static void guardarDatos() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
	            oos.writeObject(ciudadanos);
	            System.out.println("Datos guardados correctamente.");
	        } catch (IOException e) {
	            System.out.println("Error al guardar los datos: " + e.getMessage());
	        }
	    }

	    private static void añadirCiudadano(Scanner scanner) {
	        System.out.println("Introduce el nombre del ciudadano:");
	        String nombre = scanner.nextLine();

	        System.out.println("Introduce el DNI del ciudadano:");
	        String dni = scanner.nextLine();

	        System.out.println("Introduce la edad del ciudadano:");
	        int edad = scanner.nextInt();
	        scanner.nextLine(); // Consume la nueva línea después de nextInt()

	        Ciudadano nuevoCiudadano = new Ciudadano(nombre, dni, edad);
	        ciudadanos.add(nuevoCiudadano);
	        System.out.println("Ciudadano añadido correctamente.");
	    }

	    private static void imprimirCiudadanos() {
	        if (ciudadanos.isEmpty()) {
	            System.out.println("No hay ciudadanos registrados.");
	        } else {
	            System.out.println("Lista de ciudadanos:");
	            for (Ciudadano ciudadano : ciudadanos) {
	                System.out.println(ciudadano);
	            }
	        }
	    }

	    private static void modificarCiudadano(Scanner scanner) {
	        if (ciudadanos.isEmpty()) {
	            System.out.println("No hay ciudadanos registrados para modificar.");
	            return;
	        }

	        System.out.println("¿Qué desea modificar?");
	        System.out.println("1. Por DNI");
	        System.out.println("2. Por posición");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consume la nueva línea después de nextInt()

	        switch (opcion) {
	            case 1:
	                modificarPorDNI(scanner);
	                break;
	            case 2:
	                modificarPorPosicion(scanner);
	                break;
	            default:
	                System.out.println("Opción inválida.");
	        }
	    }

	    private static void modificarPorDNI(Scanner scanner) {
	        System.out.println("Introduce el DNI del ciudadano a modificar:");
	        String dni = scanner.nextLine();

	        for (Ciudadano ciudadano : ciudadanos) {
	            if (ciudadano.getDni().equals(dni)) {
	                System.out.println("Introduce el nuevo nombre:");
	                String nuevoNombre = scanner.nextLine();
	                ciudadano.setNombre(nuevoNombre);

	                System.out.println("Introduce la nueva edad:");
	                int nuevaEdad = scanner.nextInt();
	                scanner.nextLine(); // Consume la nueva línea después de nextInt()
	                ciudadano.setEdad(nuevaEdad);

	                System.out.println("Ciudadano modificado correctamente.");
	                return;
	            }
	        }

	        System.out.println("No se encontró ningún ciudadano con ese DNI.");
	    }

	    private static void modificarPorPosicion(Scanner scanner) {
	        System.out.println("Introduce la posición del ciudadano a modificar (empezando desde 1):");
	        int posicion = scanner.nextInt();
	        scanner.nextLine(); // Consume la nueva línea después de nextInt()

	        if (posicion < 1 || posicion > ciudadanos.size()) {
	            System.out.println("Posición inválida.");
	        } else {
	            Ciudadano ciudadano = ciudadanos.get(posicion - 1);
	            System.out.println("Introduce el nuevo nombre:");
	            String nuevoNombre = scanner.nextLine();
	            ciudadano.setNombre(nuevoNombre);

	            System.out.println("Introduce la nueva edad:");
	            int nuevaEdad = scanner.nextInt();
	            scanner.nextLine(); // Consume la nueva línea después de nextInt()
	            ciudadano.setEdad(nuevaEdad);

	            System.out.println("Ciudadano modificado correctamente.");
	        }
	    }

	    private static void eliminarCiudadano(Scanner scanner) {
	        if (ciudadanos.isEmpty()) {
	            System.out.println("No hay ciudadanos registrados para eliminar.");
	            return;
	        }

	        System.out.println("¿Qué desea eliminar?");
	        System.out.println("1. Por DNI");
	        System.out.println("2. Por posición");

	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consume la nueva línea después de nextInt()

	        switch (opcion) {
	            case 1:
	                eliminarPorDNI(scanner);
	                break;
	            case 2:
	                eliminarPorPosicion(scanner);
	                break;
	            default:
	                System.out.println("Opción inválida.");
	        }
	    }

	    private static void eliminarPorDNI(Scanner scanner) {
	        System.out.println("Introduce el DNI del ciudadano a eliminar:");
	        String dni = scanner.nextLine();

	        for (int i = 0; i < ciudadanos.size(); i++) {
	            if (ciudadanos.get(i).getDni().equals(dni)) {
	                ciudadanos.remove(i);
	                System.out.println("Ciudadano eliminado correctamente.");
	                return;
	            }
	        }

	        System.out.println("No se encontró ningún ciudadano con ese DNI.");
	    }

	    private static void eliminarPorPosicion(Scanner scanner) {
	        System.out.println("Introduce la posición del ciudadano a eliminar (empezando desde 1):");
	        int posicion = scanner.nextInt();
	        scanner.nextLine(); // Consume la nueva línea después de nextInt()

	        if (posicion < 1 || posicion > ciudadanos.size()) {
	            System.out.println("Posición inválida.");
	        } else {
	            ciudadanos.remove(posicion - 1);
	            System.out.println("Ciudadano eliminado correctamente.");
	        }
	    }
	}


