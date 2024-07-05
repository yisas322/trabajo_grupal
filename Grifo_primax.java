
import java.util.Scanner;

public class Grifo_primax {


    private String[] usuarios;      // Array para almacenar nombres de usuario
    private String[] correos;       // Array para almacenar correos electrónicos
    private String[] contraseñas;   // Array para almacenar contraseñas
    private int numUsuarios;        // Número de usuarios registrados
    private Scanner scanner;        // Objeto Scanner para entrada de usuario

    public Grifo_primax() {
        usuarios = new String[10];      // Inicializamos los arrays con capacidad para 10 usuarios
        correos = new String[10];
        contraseñas = new String[10];
        numUsuarios = 0;                // Inicialmente no hay usuarios registrados
        scanner = new Scanner(System.in);  // Inicializamos el Scanner para entrada estándar
    }public void opciones(){
        Scanner input = new Scanner(System.in);
        System.out.println("1.-Crear Cuenta");
        int option = input.nextInt();
        switch (option) {
            case 1:
                crearCuenta();
                break;
            default:
                System.out.println("Invalid option.");
                System.exit(0);
        }
    }

    public void crearCuenta() {
        System.out.println("----- CREAR CUENTA -----");
        System.out.print("Ingrese su usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();

        // Verificar si el correo ya está registrado
        for (int i = 0; i < numUsuarios; i++) {
            if (correos[i].equals(correo)) {
                System.out.println("Error: El correo ya está registrado.");
                return;  // Salir del método si el correo ya está registrado
            }
        }

        // Almacenar los datos del nuevo usuario en los arrays
        usuarios[numUsuarios] = usuario;
        correos[numUsuarios] = correo;
        contraseñas[numUsuarios] = contraseña;
        numUsuarios++;

        System.out.println("Usuario registrado correctamente.");
    }

    public void login() {
        System.out.println("----- INICIAR SESION -----");
        System.out.print("Ingrese su correo: ");
        String correo_in = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña_in = scanner.nextLine();

        // Verificar si las credenciales coinciden con algún usuario registrado
        for (int i = 0; i < numUsuarios; i++) {
            if (correos[i].equals(correo_in) && contraseñas[i].equals(contraseña_in)) {
                System.out.println("Bienvenido: " + usuarios[i]);
              // Salir del método si se encuentra el usuario
            }
        }

        System.out.println("Error: Correo o contraseña incorrectos.");
        System.exit(0);
    }

   }