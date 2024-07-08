import java.util.Scanner;

public class logi {


    private String[] usuarios;
    private String[] correos;
    private String[] contraseñas;
    private int numUsuarios;
    private Scanner scanner;

    public logi() {
        usuarios = new String[10];
        correos = new String[10];
        contraseñas = new String[10];
        numUsuarios = 0;
        scanner = new Scanner(System.in);
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


        for (int i = 0; i < numUsuarios; i++) {
            if (correos[i].equals(correo)) {
                System.out.println("Error: El correo ya está registrado.");
                return;
            }
        }


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


        for (int i = 0; i < numUsuarios; i++) {
            if (correos[i].equals(correo_in) && contraseñas[i].equals(contraseña_in)) {
                System.out.println("Bienvenido: " + usuarios[i]);
                return;
            }
        }

        System.out.println("Error: Correo o contraseña incorrectos.");
        System.exit(0);
    }

}