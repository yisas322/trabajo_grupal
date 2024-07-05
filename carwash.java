import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class carwash {

    public static void main(String[] args) {
        Scanner yzz = new Scanner(System.in);
        carwash(yzz);
    }

    public static void carwash(Scanner yzz) {
        double IGV_PORCENTAJE = 0.18;
        String nombreCarWash = "---PRIMAX CARWASH---";
        System.out.print("Ingrese su nombre: ");
        String nombreCliente = yzz.nextLine();
        System.out.println("Bienvenido, " + nombreCliente + ", al " + nombreCarWash + ". Tenemos lavados básicos y premium.");
        String[] tiposLavado = new String[]{"Lavado Básico", "Lavado Premium"};
        double[] preciosLavadoConIGV = new double[]{20, 30};

        String jess;
        do {
            label37:
            while(true) {

                    System.out.println("Seleccione el tipo de lavado:");

                    int tipoLavadosoncc;
                    for(tipoLavadosoncc  = 0; tipoLavadosoncc < tiposLavado.length; ++tipoLavadosoncc) {
                        System.out.printf("%d. %s - S/%.2f%n", tipoLavadosoncc + 1, tiposLavado[tipoLavadosoncc], preciosLavadoConIGV[tipoLavadosoncc]);
                    }

                    tipoLavadosoncc = yzz.nextInt() - 1;
                    if (tipoLavadosoncc >= 0 && tipoLavadosoncc < tiposLavado.length) {
                        double precioConIGV = preciosLavadoConIGV[tipoLavadosoncc];
                        double precioSinIGV = precioConIGV / (1 + IGV_PORCENTAJE);
                        double igv = precioConIGV - precioSinIGV;

                        yzz.nextLine();

                        System.out.println("Seleccione el método de pago:");
                        System.out.println("1. Efectivo\uD83D\uDCB5");
                        System.out.println("2. Tarjeta de Crédito\uD83D\uDCB3");
                        int metodoPago = yzz.nextInt();
                        switch (metodoPago) {
                            case 1:
                                System.out.print("Ingrese el monto de pago en efectivo: ");
                                double montoPago = yzz.nextDouble();
                                if (montoPago >= precioConIGV) {
                                    double cambio = montoPago - precioConIGV;
                                    System.out.printf("Pago recibido. Su cambio es de %.2f soles. ¡Disfrute su lavado!%n", cambio);
                                    imprimirBoleta(nombreCarWash, nombreCliente, tiposLavado[tipoLavadosoncc], precioSinIGV, igv, precioConIGV);
                                    break label37;
                                }

                                System.out.println("El monto ingresado es insuficiente. Por favor, ingrese un monto válido.");
                                break;
                            case 2:
                                System.out.print("Ingrese el número de tarjeta de crédito (16 dígitos): ");
                                String numeroTarjeta = yzz.next();
                                if (numeroTarjeta.length() == 16 && numeroTarjeta.matches("\\d+")) {
                                    System.out.print("Ingrese el CCV (código de verificación, 3 dígitos): ");
                                    int ccv = yzz.nextInt();
                                    System.out.print("Ingrese la fecha de caducidad (MM/YY): ");
                                    String fechaCaducidad = yzz.next();
                                    System.out.println("Pago recibido a través de tarjeta de crédito. ¡Disfrute su lavado!");
                                    imprimirBoleta(nombreCarWash, nombreCliente, tiposLavado[tipoLavadosoncc], precioSinIGV, igv, precioConIGV);
                                    break label37;
                                } else {
                                    System.out.println("Número de tarjeta inválido. Asegúrese de ingresar 16 dígitos.");
                                }
                                break;

                            default:
                                System.out.println("Opción de pago inválida.");
                        }
                    } else {
                        System.out.println("Opción inválida. Por favor, seleccione 1 o 2.");
                    }
                }


            System.out.print("¿Desea realizar otro lavado? (si/no): ");
            jess = yzz.next();
        } while(jess.equalsIgnoreCase("si"));

        System.out.println("Gracias por usar nuestro servicio de carwash " + nombreCarWash + ".");
    }

    private static void imprimirBoleta(String nombreCarWash, String nombreCliente, String tipoLavado, double precioSinIGV, double igv, double precioConIGV) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("---GRACIAS POR USAR NUESTRO SERVICIO DE PRIMAX CARWASH. Aquí tiene su boleta---");
        System.out.println("----------------------------------------------------------");
        System.out.println("                      " + nombreCarWash);
        System.out.println("----------------------------------------------------------");
        System.out.printf("Fecha y hora: %s%n", fechaHoraActual.format(formatoFechaHora));
        System.out.printf("Cliente: %s%n", nombreCliente);
        System.out.printf("Lavado: %-20s Precio sin IGV: S/%.2f%n", tipoLavado, precioSinIGV);
        System.out.printf("IGV (18%%): %.2f%n", igv);
        System.out.printf("Total a pagar: S/%.2f%n", precioConIGV);
        System.out.println("----------------------------------------------------------");
    }

}
