package gestorPersonas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

	class Ciudadano  {
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
	} // Finalización de Getters y Setters
	
	
	
	public class menu {
	    private static final String nameFile = "ciudadanos.txt";
	    private static ArrayList<Ciudadano> ciudadanos = new ArrayList<>();

	    public static void main(String[] args) {
	        cargarDatos();

	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n¿Qué te gustaría hacer?");
	            System.out.println("1. Añadir nuevo ciudadano");
	            System.out.println("2. Imprimir ciudadanos");
	            System.out.println("3. Modificar un ciudadano");
	            System.out.println("4. Eliminar un ciudadano");
	            System.out.println("5. Salir");

	            int opcion = scanner.nextInt();
	            scanner.nextLine(); 

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
	        try (Scanner scanner = new Scanner(new File(nameFile))) {
	            while (scanner.hasNextLine()) {
	            	// este string lee la linea y la separa por partes que mas adelante llamaremos con otras strings
	                String[] partes = scanner.nextLine().split(",");
	                // en este se asigna el primera parte a nombr y se hace el trim
	                // el trim se utiliza para eliminar espacios en blanco
	                String nombre = partes[0].trim();
	                String dni = partes[1].trim();
	                int edad = Integer.parseInt(partes[2].trim());
	                ciudadanos.add(new Ciudadano(nombre, dni, edad));
	            }
	            System.out.println("Los datos se han cargado correctamente.");
	        } catch (FileNotFoundException e) {
	            System.out.println("No se encontró el archivo de datos. Se creará uno nuevo al guardar los datos.");
	        } catch (IOException e) {
	            System.out.println("Error al cargar los datos: " + e.getMessage());
	        }
	    }


	    private static void guardarDatos() {
	    	// el printwriter escribe los datos que le he proporcionado en el documento de .txt
	        try (PrintWriter writer = new PrintWriter(new FileWriter(nameFile))) {
	        	// el bucle for para guardar a cada ciudadano en el arraylist ciudadanos especificando con Ciudadano como se guardara
	            for (Ciudadano ciudadano : ciudadanos) {
	                writer.println(ciudadano.getNombre() + ", " + ciudadano.getDni() + ", " + ciudadano.getEdad());
	            }
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
	        scanner.nextLine(); 

	        Ciudadano nuevoCiudadano = new Ciudadano(nombre, dni, edad);
	        ciudadanos.add(nuevoCiudadano);
	        System.out.println("Ha sido añadido correctamente" + " " +nombre);
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
	        scanner.nextLine(); 

	        switch (opcion) {
	            case 1:
	            	modificar_porDNI(scanner);
	                break;
	            case 2:
	            	modificar_porPosicion(scanner);
	                break;
	            default:
	                System.out.println("Opción inválida.");
	        }
	    }

	    private static void modificar_porDNI(Scanner scanner) {
	        System.out.println("Introduce el DNI del ciudadano a modificar:");
	        String dni = scanner.nextLine();

	        for (Ciudadano ciudadano : ciudadanos) {
	            if (ciudadano.getDni().equals(dni)) {
	                System.out.println("Introduce el nuevo nombre:");
	                String nuevoNombre = scanner.nextLine();
	                ciudadano.setNombre(nuevoNombre);

	                System.out.println("Introduce la nueva edad:");
	                int nuevaEdad = scanner.nextInt();
	                scanner.nextLine(); 
	                ciudadano.setEdad(nuevaEdad);

	                System.out.println("Ciudadano modificado correctamente.");
	                return;
	            }
	        }

	        System.out.println("No se encontró ningún ciudadano con ese DNI.");
	    }

	    private static void modificar_porPosicion(Scanner scanner) {
	        System.out.println("Introduce la posición del ciudadano a modificar 'Empieza desde 1 no 0 :)' :");
	        int posicion = scanner.nextInt();
	        scanner.nextLine(); 
	        // || 'o' siempre se me olvida asi que un putisimo recordatorio 
	        if (posicion < 1 || posicion > ciudadanos.size()) {
	            System.out.println("Posición inválida.");
	        } else {
	        	// como empezamos con uno en vez de cero se lo resatamos para que de en la posicion correcta
	            Ciudadano ciudadano = ciudadanos.get(posicion - 1);
	            System.out.println("Introduce el nuevo nombre:");
	            String nuevoNombre = scanner.nextLine();
	            ciudadano.setNombre(nuevoNombre);

	            System.out.println("Introduce la nueva edad:");
	            int nuevaEdad = scanner.nextInt();
	            scanner.nextLine(); 
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
	        scanner.nextLine(); 

	        switch (opcion) {
	            case 1:
	                eliminarPorDNI(scanner);
	                break;
	            case 2:
	                eliminarPorPosicion(scanner);
	                break;
	            default:
	                System.out.println("Opción no valida intentalo de nuevo.");
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
	        scanner.nextLine(); 

	        if (posicion < 1 || posicion > ciudadanos.size()) {
	            System.out.println("Posición inválida.");
	        } else {
	            ciudadanos.remove(posicion - 1);
	            System.out.println("Ciudadano eliminado correctamente.");
	        }
	    }
	}