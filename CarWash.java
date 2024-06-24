import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CarWash {

    // Constante para el porcentaje de IGV (18%)
    private static final double IGV_PORCENTAJE = 0.18;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombreCarWash = "---PRIMAX CARWASH---";

        // Capturar el nombre del cliente
        System.out.print("Ingrese su nombre: ");
        String nombreCliente = scanner.nextLine();

        System.out.println("Bienvenido, " + nombreCliente + ", al " + nombreCarWash + ". Tenemos lavados básicos y premium.");

        String[] tiposLavado = {"Lavado Básico", "Lavado Premium"};
        int[] preciosLavado = {20, 30};

        while (true) {
            System.out.println("Seleccione el tipo de lavado:");
            for (int i = 0; i < tiposLavado.length; i++) {
                System.out.println((i + 1) + ". " + tiposLavado[i] + " - S/" + preciosLavado[i]);
            }
            int tipoLavadoIndex = scanner.nextInt() - 1;

            if (tipoLavadoIndex >= 0 && tipoLavadoIndex < tiposLavado.length) {
                int precioSinIGV = preciosLavado[tipoLavadoIndex];
                double igv = precioSinIGV * IGV_PORCENTAJE;
                double precioConIGV = precioSinIGV + igv;

                System.out.println("Seleccione el método de pago:");
                System.out.println("1. Efectivo");
                System.out.println("2. Tarjeta de Crédito");
                int metodoPago = scanner.nextInt();

                switch (metodoPago) {
                    case 1:
                        System.out.println("Ingrese el monto de pago en efectivo:");
                        int montoPago = scanner.nextInt();
                        if (montoPago >= precioConIGV) {
                            double cambio = montoPago - precioConIGV;
                            System.out.printf("Pago recibido. Su cambio es de %.2f soles. ¡Disfrute su lavado!%n", cambio);

                            // Imprimir boleta
                            imprimirBoleta(nombreCarWash, nombreCliente, tiposLavado[tipoLavadoIndex], precioSinIGV, igv, precioConIGV);
                        } else {
                            System.out.println("El monto ingresado es insuficiente. Por favor, ingrese un monto válido.");
                            continue;
                        }
                        break;
                    case 2:
                        System.out.println("Ingrese el número de tarjeta de crédito:");
                        long numeroTarjeta = scanner.nextLong();
                        System.out.println("Ingrese el monto a cargar a la tarjeta:");
                        int montoCarga = scanner.nextInt();
                        System.out.println("Pago recibido a través de tarjeta de crédito. ¡Disfrute su lavado!");


                        break;
                    default:
                        System.out.println("Opción de pago inválida.");
                        continue;
                }

                System.out.println("¿Desea realizar otro lavado? (si/no)");
                String continuar = scanner.next();
                if (!continuar.equalsIgnoreCase("si")) {
                    System.out.println("Gracias por usar nuestro servicio de carwash " + nombreCarWash + ".");
                    break;
                }
            } else {
                System.out.println("Opción inválida. Por favor, seleccione 1 o 2.");
            }
        }
    }

    private static void imprimirBoleta(String nombreCarWash, String nombreCliente, String tipoLavado, int precioSinIGV, double igv, double precioConIGV) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("---GRACIAS POR USAR NUESTRO SERVICIO DE PRIMAX CARWASH. Aquí tiene su boleta---");
        System.out.println("----------------------------------------------------------");
        System.out.println("                      " + nombreCarWash);
        System.out.println("----------------------------------------------------------");
        System.out.printf("Fecha y hora: %s%n", fechaHoraActual.format(formatoFechaHora));
        System.out.printf("Cliente: %s%n", nombreCliente);
        System.out.printf("Lavado: %-20s Precio: S/%d%n", tipoLavado, precioSinIGV);
        System.out.printf("IGV (18%%): %.2f%n", igv);
        System.out.printf("Total a pagar: %.2f%n", precioConIGV);
        System.out.println("----------------------------------------------------------");
    }
}
