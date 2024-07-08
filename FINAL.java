import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
public class FINAL {
    public FINAL() {
    }

    public static void opciones1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===ELIGA UNA OPCION===\n1. Comprar gasolina\n2.Compra de Lubricantes \n3.Compra de gas\n4.Tienda Listol\n5.Carwash\n6.sala de comunicaciones");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                comprarGasolina(sc);
                break;
            case 2:
                comprarLubricantes(sc);
                break;
            case 3:
                comprarGas(sc);
                break;
            case 4:
                comprarlistol(sc);
                break;
            case 5:
                carwash(sc);
                break;
            case 6:
                cumunicaciones(sc);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        sc.close();
    }
    public static void comprarGasolina(Scanner gaa) {
        String nombreCliente ;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date datos = new Date();
        String fechaActual = formatter.format(datos);

        String[] tiposGasolina = {"Max-D(18.86)","G-Regular(19.81)"," G-Premium(21.22)"};
        double[] preciosGasolina = {15.99, 16.79, 17.99};

        System.out.println("\nTipos de gasolina disponibles:");
        for (int i = 0; i < tiposGasolina.length; i++) {
            System.out.println((i + 1) + ". " + tiposGasolina[i] + " - S/" + " por galón");
        }

        int tipoGasolina;
        do {
            System.out.print("Ingrese el tipo de gasolina (1, 2, 3): ");
            tipoGasolina = gaa.nextInt();

            if (tipoGasolina < 1 || tipoGasolina > 3) {
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (tipoGasolina < 1 || tipoGasolina > 3);

        System.out.print("Cantidad de galones: ");
        int cantidadGasolina = gaa.nextInt();
        double IGV = 0.18;
        double precioGasolina = preciosGasolina[tipoGasolina - 1];
        double totalSinIGV = precioGasolina * cantidadGasolina;
        double totalIGV = totalSinIGV * IGV;
        double totalGasolina = totalSinIGV + totalIGV;
        System.out.print("Ingrese su nombre: ");
        gaa.nextLine();
        nombreCliente = gaa.nextLine();

        System.out.println("Total sin IGV: S/ " + totalSinIGV);
        System.out.println("IGV (18%): S/ " + totalIGV);
        System.out.println("Total con IGV: S/ " + totalGasolina);

        String[] metodosPago = {"Pago en Efectivo", "Pago con Tarjeta"};
        System.out.println("\n--- Métodos de Pago ---");
        for (int i = 0; i < metodosPago.length; i++) {
            System.out.println((i + 1) + "- " + metodosPago[i]);
        }

        System.out.print("Seleccione el método de pago (1 o 2): ");
        int metodoPago = gaa.nextInt();

        switch (metodoPago) {
            case 1:
                System.out.print("Ingrese el monto entregado: S/");
                double montoEntregado = gaa.nextDouble();
                if (montoEntregado >= totalGasolina) {
                    double cambio = montoEntregado - totalGasolina;
                    System.out.println("Pago recibido. Su cambio es: S/ " + cambio);
                    generarBoleta(nombreCliente, fechaActual, "Efectivo", totalSinIGV, totalIGV, totalGasolina, montoEntregado, cambio);
                } else {
                    System.out.println("Monto insuficiente. Por favor, pague al menos S/ " + totalGasolina);
                }
                break;
            case 2:
                System.out.println("\n--- Pago con Tarjeta de Crédito ---");

                System.out.print("Ingrese el número de la tarjeta de crédito: ");
                String numeroTarjeta = gaa.next();

                System.out.print("Ingrese la fecha de vencimiento (MM/AA): ");
                String fechaVencimiento = gaa.next();

                System.out.print("Ingrese el código CVV: ");
                String cvv = gaa.next();

                if (VerificarTarjeta1(numeroTarjeta, fechaVencimiento, cvv, totalGasolina)) {
                    System.out.println("Compra exitosa. Total pagado: S/ " + totalGasolina);
                    generarBoleta(nombreCliente, fechaActual, "Tarjeta", totalSinIGV, totalIGV, totalGasolina, 0, 0);
                } else {
                    System.out.println("Datos de tarjeta inválidos. Pago fallido. Intente nuevamente.");
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static boolean VerificarTarjeta1(String numeroTarjeta, String fechaVencimiento, String cvv, double monto) {
        if (numeroTarjeta.length() == 16 && fechaVencimiento.length() == 5 && cvv.length() == 3) {
            System.out.println("Procesando pago de S/ " + monto + "...");
            return true;
        }
        System.out.println("Datos de tarjeta inválidos.");
        return false;
    }

    private static void generarBoleta(String nombreCliente, String fechaActual, String metodoPago, double precioGasolina, double igv, double totalConIGV, double montoEntregado, double cambio) {
        String nombreArchivo = "boleta_" + nombreCliente + "" + fechaActual.replaceAll("[/: ]", "") + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("---------------------------------");
            writer.println(".........Boleta de Pago..........");
            writer.println("---------------------------------");
            writer.println("Cliente: " + nombreCliente);
            writer.println("Fecha: " + fechaActual);
            writer.println("......GRACIAS POR SU COMPRA......");
            writer.println("---------------------------------");
            writer.println("Descripción: Gasolina seleccionada");
            writer.println("Precio sin IGV: S/ " + precioGasolina);
            writer.println("IGV (18%): S/ " + igv);
            writer.println("Total a pagar: S/ " + totalConIGV);
            writer.println("Método de Pago: " + metodoPago);
            if (metodoPago.equals("Efectivo")) {
                writer.println("Monto Entregado: S/ " + montoEntregado);
                writer.println("Cambio: S/ " + cambio);
            }
            writer.println("---------------------------------");
            System.out.println("Boleta de pago generada exitosamente en " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir la boleta en el archivo.");
            e.printStackTrace();
        }
    }

    public static void comprarLubricantes(Scanner scanner) {
        System.out.println(
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n");
        String[] TiposLubricantes = new String[]{"SHELL HELIX ULTRA S/325.20", "SHELL HELIX HX8 S/288.59", "SHELL RIMULA S/149.38", "SHELL HELIX HX5 S/144.23", "SHELL HELIX HX3 S/137.95"};
        double[] precioslubricante = new double[]{275.6, 244.57, 126.6, 122.23, 116.91};
        System.out.println("--- LUBRICANTES DISPONIBLES ---");

        int tiposLubricantes;
        for (tiposLubricantes = 0; tiposLubricantes < TiposLubricantes.length; ++tiposLubricantes) {
            System.out.println(tiposLubricantes + 1 + "- " + TiposLubricantes[tiposLubricantes]);
        }

        System.out.print("Ingrese el tipo de lubricante : (1, 2, 3, 4, 5): ");
        tiposLubricantes = scanner.nextInt();
        if (tiposLubricantes >= 1 && tiposLubricantes <= TiposLubricantes.length) {
            System.out.print("Ingrese la cantidad de lubricantes: ");
            int cantidadLubricantes = scanner.nextInt();
            double precioLubricantesSeleccionado = precioslubricante[tiposLubricantes - 1] * cantidadLubricantes;
            double porcentaje_IGV = 0.18;
            double igv = precioLubricantesSeleccionado * porcentaje_IGV;
            double totalConIGV = precioLubricantesSeleccionado + igv;
            System.out.print("INGRESE SU NOMBRE POR FAVOR: ");
            scanner.nextLine();
            String nombreCliente = scanner.nextLine();
            System.out.println("\n--- METODO DE PAGO---");
            System.out.println("1- Pago en Efectivo");
            System.out.println("2- Pago con Tarjeta");
            System.out.print("Seleccione el método de pago (1 o 2): ");
            int metodoPago = scanner.nextInt();
            switch (metodoPago) {
                case 1:
                    PagarEnEfectivo(scanner, nombreCliente, precioLubricantesSeleccionado, igv, totalConIGV);
                    break;
                case 2:
                    if (ProcesarPagoConTarjeta(scanner, nombreCliente, precioLubricantesSeleccionado, igv, totalConIGV)) {
                        System.out.println("\nCompra exitosa. Total pagado: S/ " + totalConIGV);
                    } else {
                        System.out.println("\nPago fallido. Intente nuevamente.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } else {
            System.out.println("Opción no válida");
        }
    }

    private static void PagarEnEfectivo(Scanner scanner, String nombreCliente, double preciolubricante, double igv, double totalConIGV) {
        System.out.print("Ingrese el monto entregado por " + nombreCliente + ": ");
        double montoEntregado = scanner.nextDouble();
        if (montoEntregado >= totalConIGV) {
            double cambio = montoEntregado - totalConIGV;
            System.out.println("Pago en efectivo realizado. Total pagado: S/ " + totalConIGV);
            System.out.println("Cambio: S/ " + cambio);
            GenerarBoleta("Efectivo", nombreCliente, preciolubricante, igv, totalConIGV, montoEntregado, cambio);
        } else {
            System.out.println("Monto insuficiente. Pago fallido.");
        }
    }

    private static boolean ProcesarPagoConTarjeta(Scanner scanner, String nombreCliente, double preciolubricante, double igv, double totalConIGV) {
        System.out.print("Ingrese el número de su tarjeta: ");
        String numeroTarjeta = scanner.next();
        System.out.print("Ingrese la fecha de vencimiento (MM/AA): ");
        String fechaVencimiento = scanner.next();
        System.out.print("Ingrese el código CVV: ");
        String cvv = scanner.next();
        boolean pagoExitoso = VerificarTarjeta(numeroTarjeta, fechaVencimiento, cvv, totalConIGV);
        if (pagoExitoso) {
            GenerarBoleta("Tarjeta de Crédito", nombreCliente, preciolubricante, igv, totalConIGV, totalConIGV, 0.0);
        }

        return pagoExitoso;
    }

    private static boolean VerificarTarjeta(String numeroTarjeta, String fechaVencimiento, String cvv, double totalConIGV) {
        if (numeroTarjeta.length() == 16 && fechaVencimiento.length() == 5 && cvv.length() == 3) {
            System.out.println("Procesando pago de S/ " + totalConIGV + " con tarjeta...");
            return true;
        } else {
            System.out.println("Número de tarjeta, fechaVencimiento o CVV incorrectos.");
            return false;
        }
    }

    private static void GenerarBoleta(String metodoPago, String nombreCliente, double preciolubricante, double igv, double totalConIGV, double montoEntregado, double cambio) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        String fileName = "Boleta_" + nombreCliente + ".txt";
        String filePath = "D:\\" + fileName;

        try (PrintWriter cr = new PrintWriter(new FileWriter(filePath))) {
            cr.println("---------------------------------");
            cr.println(".........Boleta de Pago..........");
            cr.println("---------------------------------");
            cr.println("......GRACIAS POR SU COMPRA......");
            cr.println("---------------------------------");
            cr.println("Fecha y Hora: " + formatter.format(currentDate));
            cr.println("Cliente: " + nombreCliente);
            cr.println("Descripción: Lubricante seleccionado");
            cr.println("Precio sin IGV: S/ " + preciolubricante);
            cr.println("IGV (18%): S/ " + igv);
            cr.println("Total a pagar: S/ " + totalConIGV);
            cr.println("Método de Pago: " + metodoPago);
            if (metodoPago.equals("Efectivo")) {
                cr.println("Monto Entregado por " + nombreCliente + ": S/ " + montoEntregado);
                cr.println("Cambio: S/ " + cambio);
            }
            cr.println("---------------------------------");
            System.out.println("Boleta generada y guardada en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al escribir la boleta en el archivo.");
            e.printStackTrace();
        }
    }


    public static void comprarGas(Scanner scanner) {
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▒░░░░▓▓▓▓▓▓▓▓▒░▒▓▓▓▓▓▓▓▒░░▓▓▓░░▒▓▓▓░░░░░▓▓▓▒░░░▒▓▓▓▓░░░░░▓▓▓░░▓▓▒░\n░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓░░░░░░░▓▓▓░░░▓▓▓░▒▓▓░░░▒▓▓▒░▓▓▓░░▒▓▓▓▓░░░▓▓▓▓▒░░░▓▓▒▒▓▒░░░░░▒▓▓▓▓▒░░\n░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓░░░░░░░░░▓▓▓▒▒▒▓▓▒░▒▓▓▓▒▓▓▓▒░░▓▓▓░░▒▓▓▒▓▒░▓▓▒▓▓▒░░▒▓▓░░▓▓▒░░░░░▓▓▓▓░░░\n░░░░░░░▒▓▒▒▒▒▒▓▓▓▒░░░░░░░░░░░▓▓▓▒▒▒░░░░▒▓▓▒░▒▓▓░░░▓▓▓░░▒▓▓░▒▓▓▓▒░▓▓▒░▒▓▓▓▓▓▓▓▓░░░▒▓▓▒▓▓▓░░\n░░░░▒▓▓▓▓▓░░░░░▒▒░░░░░░░░░░░░▒▓▓░░░░░░░▒▓▓░░░▒▓▒░░▒▓▓░░▒▓▓░░▒▓▒░░▓▓▒░▒▓▒░░░░▒▓▒░▒▓▒░░░▒▓▓░\n░░▒▒▒▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n▒▒▒▒▒▒▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒░░░▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n▒▒▒▒▒▒▓▓▓▓▓░░░░░░░░░░░░░░░░░░▒▒▒▒░░▒▒▒░░░░░░░▒▒▒░░▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░▒▒▒▒▒▓▓▓▒░░░░░▒░░░░░░░░░░░░░▒▒▒░░░▒▒▒░░▒▒▒▒▒▒▒▒░░░▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░▒▒▓▓▒░░░░░░▒▓▓░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒░░▒▒▒░▒▒▒▒░░▒▒░▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░▒▓▓▓▓▒░░░░░░░░░░░░░░░▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░▒▒▒▒▒░░░░░░░░░░▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n");
        String[] tiposGas = new String[]{"Balón de 10kg (S/59.00)", "Balón de 15kg (S/82.60)", "Balón de 45kg (S/165.20)"};
        double[] preciosGas = new double[]{50.0, 70.0, 140.0};
        System.out.println("--- GASES DISPONIBLES ---");

        int tipoGas;
        for(tipoGas = 0; tipoGas < tiposGas.length; ++tipoGas) {
            System.out.println(tipoGas + 1 + "- " + tiposGas[tipoGas]);
        }

        System.out.print("Ingrese el tipo de gas que desea: (1, 2, 3): ");
        tipoGas = scanner.nextInt();
        if (tipoGas >= 1 && tipoGas <= tiposGas.length) {
            double precioGasSeleccionado = preciosGas[tipoGas - 1];
            double porcentaje_IGV = 0.18;
            double igv = precioGasSeleccionado * porcentaje_IGV;
            double totalConIGV = precioGasSeleccionado + igv;
            System.out.print("Ingrese su nombre: ");
            scanner.nextLine();
            String nombreCliente = scanner.nextLine();
            System.out.println("\n--- Métodos de Pago ---");
            System.out.println("1- Pago en Efectivo");
            System.out.println("2- Pago con Tarjeta");
            System.out.print("Seleccione el método de pago (1 o 2): ");
            int metodoPago = scanner.nextInt();
            switch (metodoPago) {
                case 1:
                    pagarEnEfectivo(scanner, nombreCliente, precioGasSeleccionado, igv, totalConIGV);
                    break;
                case 2:
                    if (procesarPagoConTarjeta(scanner, nombreCliente, precioGasSeleccionado, igv, totalConIGV)) {
                        System.out.println("\nCompra exitosa. Total pagado: S/ " + totalConIGV);
                    } else {
                        System.out.println("\nPago fallido. Intente nuevamente.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } else {
            System.out.println("Opción no válida");
        }
    }

    private static void pagarEnEfectivo(Scanner scanner, String nombreCliente, double precioGas, double igv, double totalConIGV) {
        System.out.print("Ingrese el monto entregado por " + nombreCliente + ": ");
        double montoEntregado = scanner.nextDouble();
        if (montoEntregado >= totalConIGV) {
            double cambio = montoEntregado - totalConIGV;
            System.out.println("Pago en efectivo realizado. Total pagado: S/ " + totalConIGV);
            System.out.println("Cambio: S/ " + cambio);
            generarBoleta("Efectivo", nombreCliente, precioGas, igv, totalConIGV, montoEntregado, cambio);
        } else {
            System.out.println("Monto insuficiente. Pago fallido.");
        }

    }

    private static boolean procesarPagoConTarjeta(Scanner scanner, String nombreCliente, double precioGas, double igv, double totalConIGV) {
        System.out.print("Ingrese el número de su tarjeta: ");
        String numeroTarjeta = scanner.next();
        System.out.print("Ingrese la fecha de vencimiento (MM/AA): ");
        String fechaVencimiento = scanner.next();
        System.out.print("Ingrese el código CVV: ");
        String cvv = scanner.next();
        boolean pagoExitoso = verificarTarjeta(numeroTarjeta, fechaVencimiento, cvv, totalConIGV);
        if (pagoExitoso) {
            generarBoleta("Tarjeta de Crédito", nombreCliente, precioGas, igv, totalConIGV, totalConIGV, 0.0);
        }

        return pagoExitoso;
    }

    private static boolean verificarTarjeta(String numeroTarjeta, String fechaVencimiento, String cvv, double totalConIGV) {
        if (numeroTarjeta.length() == 16 && fechaVencimiento.length() == 5 && cvv.length() == 3) {
            System.out.println("Procesando pago de S/ " + totalConIGV + " con tarjeta...");
            return true;
        } else {
            System.out.println("Número de tarjeta, fechaVencimiento o CVV incorrectos.");
            return false;
        }
    }

    private static void generarBoleta(String metodoPago, String nombreCliente, double precioGas, double igv, double totalConIGV, double montoEntregado, double cambio) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        String fileName = "Boleta_" + nombreCliente + ".txt";
        String filePath = "D:\\" + fileName;

        try (PrintWriter auls = new PrintWriter(new FileWriter(filePath))) {
            auls.println("---------------------------------");
            auls.println(".........Boleta de Pago..........");
            auls.println("---------------------------------");
            auls.println("......GRACIAS POR SU COMPRA......");
            auls.println("---------------------------------");
            auls.println("Fecha y Hora: " + formatter.format(currentDate));  // Include formatted date and time
            auls.println("Cliente: " + nombreCliente);
            auls.println("Descripción: Lubricante seleccionado");
            auls.println("Precio sin IGV: S/ " + precioGas);
            auls.println("IGV (18%): S/ " + igv);
            auls.println("Total a pagar: S/ " + totalConIGV);
            auls.println("Método de Pago: " + metodoPago);
            if (metodoPago.equals("Efectivo")) {
                auls.println("Monto Entregado por " + nombreCliente + ": S/ " + montoEntregado);
                auls.println("Cambio: S/ " + cambio);
            }
            auls.println("---------------------------------");
            System.out.println("Boleta generada y guardada en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al escribir la boleta en el archivo.");
            e.printStackTrace();
        }
    }

    public static void comprarlistol(Scanner scanner) {

        //los productos
        String[] SANDWICHS = {"Sandwich clásica", "Pan con queso", "Pan con hot dog", "Pan con huevo", "Toctochi", "Pan con mantequilla"};
        String[] ENVOLTURAS = {"Lays", "Cuates", "Cheetos", "Takis", "Chees Tress"};
        String[] GALLETAS = {"Oreo", "Casino", "Wafer", "Chocman", "Field", "Ritz"};
        String[] GASEOSAS = {"Inka Kola", "Coca Cola", "Energina", "Fanta", "Sprite", "Kola Real"};
        String[] BEBIDAS = {"Té", "Café", "Cocoa", "Chicha morada", "Chicha de jora"};

        //precios de cada producto
        double[] PRECIO_SANDWICHS = {3.00, 1.00, 2.00, 2.00, 2.00, 1.00};
        double[] PRECIO_ENVOLTURAS = {2.00, 1.50, 2.00, 2.00, 2.00};
        double[] PRECIO_GALLETAS = {2.00, 1.50, 2.00, 1.00, 1.00, 3.00};
        double[] PRECIO_GASEOSAS = {3.00, 2.00, 1.00, 2.00, 3.00, 1.00};
        double[] PRECIO_BEBIDAS = {1.00, 1.00, 1.00, 2.00, 2.00};

        double totalPedido = 0.0;
        final double IGV = 0.18;
        boolean continuarPedido = true;

        System.out.println("--------- TIENDA PRIMAX ---------");

        while (continuarPedido) {
            System.out.println("---------------------------------------");
            System.out.println("Escriba el N°para selecionar el pedido:");
            System.out.println(" 1.- Sandwichs");
            System.out.println(" 2.- Envolturas");
            System.out.println(" 3.- Galletas");
            System.out.println(" 4.- Gaseosas");
            System.out.println(" 5.- Bebidas");
            System.out.println(" 6.- CANCELAR EL PEDIDO -- SALIR --");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    totalPedido += manejarProductos(SANDWICHS, PRECIO_SANDWICHS, "Sandwichs", scanner);
                    break;
                case 2:
                    totalPedido += manejarProductos(ENVOLTURAS, PRECIO_ENVOLTURAS, "Envolturas", scanner);
                    break;
                case 3:
                    totalPedido += manejarProductos(GALLETAS, PRECIO_GALLETAS, "Galletas", scanner);
                    break;
                case 4:
                    totalPedido += manejarProductos(GASEOSAS, PRECIO_GASEOSAS, "Gaseosas", scanner);
                    break;
                case 5:
                    totalPedido += manejarProductos(BEBIDAS, PRECIO_BEBIDAS, "Bebidas", scanner);
                    break;
                case 6:
                    continuarPedido = false;
                    break;
                default:
                    System.out.println("-- Opción inválida -- Seleccione el número de la lista.");
                    break;
            }
        }
        //juntado todo para la exportacion de boleta

        double totalConIGV = calcularTotalConIGV(totalPedido, IGV);
        exportarBoletaSimple(totalConIGV, SANDWICHS, PRECIO_SANDWICHS, ENVOLTURAS, PRECIO_ENVOLTURAS,
                GALLETAS, PRECIO_GALLETAS, GASEOSAS, PRECIO_GASEOSAS, BEBIDAS, PRECIO_BEBIDAS);
        elegirMetodoPago(totalConIGV, scanner);
    }



    private static void exportarBoletaSimple(double totalConIGV, String[] SANDWICHS, double[] PRECIO_SANDWICHS,
                                             String[] ENVOLTURAS, double[] PRECIO_ENVOLTURAS,
                                             String[] GALLETAS, double[] PRECIO_GALLETAS,
                                             String[] GASEOSAS, double[] PRECIO_GASEOSAS,
                                             String[] BEBIDAS, double[] PRECIO_BEBIDAS) {
        System.out.println("---------------------------------");
        System.out.println("---------- LA BOLETITA ----------");
        System.out.println("---------------------------------");
        System.out.println("Productos comprados:");

        imprimirProductosSeleccionados(SANDWICHS, PRECIO_SANDWICHS);
        imprimirProductosSeleccionados(ENVOLTURAS, PRECIO_ENVOLTURAS);
        imprimirProductosSeleccionados(GALLETAS, PRECIO_GALLETAS);
        imprimirProductosSeleccionados(GASEOSAS, PRECIO_GASEOSAS);
        imprimirProductosSeleccionados(BEBIDAS, PRECIO_BEBIDAS);

        System.out.println("Total a pagar (incluido IGV): S/ " + totalConIGV);
        System.out.println("----------------------------");
    }

    private static void imprimirProductosSeleccionados(String[] productos, double[] precios) {

        for (int i = 0; i < productos.length; i++) {
            if (precios[i] > 0) {
                System.out.println(productos[i] + " - S/ " + precios[i]);
            }
        }
    }

    private static double manejarProductos(String[] productos, double[] precios, String tipo, Scanner scanner) {
        double totalProducto = 0.0;

        System.out.println("Productos disponibles:");
        for (int i = 0; i < productos.length; i++) {
            System.out.println((i + 1) + ". " + productos[i] + " - S/ " + precios[i]);
        }

        System.out.println("Seleccione el número del producto que desea comprar:");
        int seleccion = scanner.nextInt();

        if (seleccion >= 1 && seleccion <= productos.length) {
            totalProducto = precios[seleccion - 1];
            System.out.println("Ha seleccionado: " + productos[seleccion - 1]);
        } else {
            System.out.println("Opción inválida.");
        }

        return totalProducto;
    }

    private static double calcularTotalConIGV(double totalPedido, double IGV) {
        return totalPedido * (1 + IGV);
    }

    private static void elegirMetodoPago(double totalConIGV, Scanner scanner) {
        System.out.println("Seleccione el método de pago:");
        System.out.println(" 1.- Efectivo");
        System.out.println(" 2.- Tarjeta de Crédito");

        int opcionPago = scanner.nextInt();

        switch (opcionPago) {
            case 1:
                pagarEnEfectivo(totalConIGV, scanner);
                break;
            case 2:
                pagarConTarjeta(totalConIGV, scanner);
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    private static void pagarEnEfectivo(double totalConIGV, Scanner scanner) {
        System.out.println("Usted ha elegido pagar en efectivo.");
        System.out.println("Total a pagar (incluido IGV): S/ " + totalConIGV);
        System.out.println("Por favor, ingrese la cantidad en efectivo:");

        double efectivo = scanner.nextDouble();

        if (efectivo >= totalConIGV) {
            double cambio = efectivo - totalConIGV;
            System.out.println("Pago recibido. Su cambio es: S/ " + cambio);
            System.out.println("¡Gracias por su compra!");
        } else {
            System.out.println("Cantidad insuficiente. Pago no realizado.");
        }
    }

    private static void pagarConTarjeta(double totalConIGV, Scanner scanner) {
        System.out.println("Usted ha elegido pagar con tarjeta de crédito.");
        System.out.println("Total a pagar (incluido IGV): S/ " + totalConIGV);


        System.out.println("Procesando pago...");
        System.out.println("Pago realizado correctamente por tarjeta.");
        System.out.println("¡Gracias por su compra!");
    }



    public static void carwash(Scanner yzz) {
        double IGV_PORCENTAJE = 0.18;
        String nombreCarWash = "---PRIMAX CARWASH---";
        System.out.print("Ingrese su nombre: ");
        String nombreCliente = yzz.nextLine();
        System.out.println("Bienvenido, " + nombreCliente + ", al " + nombreCarWash + ". Tenemos lavados básicos y premium.");
        String[] tiposLavado = new String[]{"Lavado Básico", "Lavado Premium"};
        double[] preciosLavadoConIGV = new double[]{20, 30};

        String continuar;
        do {
            label37:
            while (true) {

                System.out.println("Seleccione el tipo de lavado:");

                int tipoLavadoSeleccionado;
                for (tipoLavadoSeleccionado = 0; tipoLavadoSeleccionado < tiposLavado.length; ++tipoLavadoSeleccionado) {
                    System.out.printf("%d. %s - S/%.2f%n", tipoLavadoSeleccionado + 1, tiposLavado[tipoLavadoSeleccionado], preciosLavadoConIGV[tipoLavadoSeleccionado]);
                }

                tipoLavadoSeleccionado = yzz.nextInt() - 1;
                if (tipoLavadoSeleccionado >= 0 && tipoLavadoSeleccionado < tiposLavado.length) {
                    double precioConIGV = preciosLavadoConIGV[tipoLavadoSeleccionado];
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
                                imprimirBoleta(nombreCarWash, nombreCliente, tiposLavado[tipoLavadoSeleccionado], precioSinIGV, igv, precioConIGV);
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
                                imprimirBoleta(nombreCarWash, nombreCliente, tiposLavado[tipoLavadoSeleccionado], precioSinIGV, igv, precioConIGV);
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
            continuar = yzz.next();
        } while (continuar.equalsIgnoreCase("si"));

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

        String rutaArchivo = "D:";
        String nombreArchivo = rutaArchivo + "boleta_" + fechaHoraActual.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("---GRACIAS POR USAR NUESTRO SERVICIO DE PRIMAX CARWASH. Aquí tiene su boleta---\n");
            writer.write("----------------------------------------------------------\n");
            writer.write("                      " + nombreCarWash + "\n");
            writer.write("----------------------------------------------------------\n");
            writer.write(String.format("Fecha y hora: %s%n", fechaHoraActual.format(formatoFechaHora)));
            writer.write(String.format("Cliente: %s%n", nombreCliente));
            writer.write(String.format("Lavado: %-20s Precio sin IGV: S/%.2f%n", tipoLavado, precioSinIGV));
            writer.write(String.format("IGV (18%%): %.2f%n", igv));
            writer.write(String.format("Total a pagar: S/%.2f%n", precioConIGV));
            writer.write("----------------------------------------------------------\n");
            System.out.println("La boleta ha sido guardada en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la boleta en el archivo.");
            e.printStackTrace();
        }
    }


    public static void cumunicaciones(Scanner scanner) {

        System.out.println("--BIENVENIDO A SALA DE COMUNICACIONES--");
        System.out.println("1. contactenos");
        System.out.println("2. Ver noticias relevantes");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                contactenos();
                break;
            case 2:
                mostrarNoticias();
                break;
            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    public static void contactenos() {
        System.out.println("\uD83D\uDCCC-Av. Circunvalación del Club Golf Los Incas N° 134 Edificio Panorama Torre 1 Piso N° 18, Santiago de Surco, Lima");
        System.out.println("☎-Centro de servicio al cliente: (511) 996 207 533" +
                "Oficina Central: (511) 203-3100");
        System.out.println("\uD83D\uDCEC-Contáctanos – Primax Te Escucha");
        System.out.println("Línea Ética");
    }

    public static void mostrarNoticias() {
        String[] titulos = {
                "COMUNICADO GRUPO PRIMAX – MAYO 20 DEL 2024",
                "Educación digital inclusiva: Más de 100 estudiantes del CEBE Corazón de María de Magdalena se benefician con laboratorio de cómputo donado por Primax",
                "PRIMAXGAS POTENCIA SU SERVICIO DE DELIVERY Y LANZA SU NUEVO CANAL DE VENTAS DIGITAL",
                "GNV ¿POR QUÉ ES CONSIDERADO UNA ENERGÍA ECO AMIGABLE?",
                "PRIMAX RENUEVA SU ESTACIÓN DE SERVICIOS UBICADA EN EL KILÓMETRO 97 DE ASIA" ,
                "GAS NATURAL VEHICULAR " ,
                "Energía que nos Conecta" ,
                "premió a sus clientes con viaje todo pagado a Tomorrowland Brasil" ,
                "PRIMAX es reconocida internacionalmente como el mejor retailer de conveniencia de Latinoamérica" ,
                "PRIMAX escala 42 posiciones y se ubica dentro de las 50 empresas con mejor reputación en el Perú" ,
                "Somos reconocidos como el proveedor estratégico de la industria minera del país"


        };

        System.out.println("=== NOTICIAS RELEVANTES ===");
        for (int i = 0; i < titulos.length; i++) {
            System.out.println((i + 1) + ". " + titulos[i]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione una opción: ");
        int opcionSeleccionada = scanner.nextInt();
        scanner.nextLine();


        if (opcionSeleccionada >= 1 && opcionSeleccionada <= titulos.length) {
            mostrarNoticia(opcionSeleccionada - 1);
        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    public static void mostrarNoticia(int indice) {
        switch (indice) {
            case 0:
                System.out.println("Lima, 20 de mayo del 2024. Sobre la explosión ocurrida esta " +
                        "tarde en una planta de compresión de Gas Natural contigua a una estación de servicios," +
                        " de propiedad y operada por un socio afiliado a nuestra marca, ubicada en Villa María del Triunfo," +
                        " Grupo Primax informa a la opinión pública:Que lamentamos profundamente lo ocurrido y expresamos " +
                        "nuestro sentido pésame a la familia de la persona que ha fallecido en este accidente y nuestra total solidaridad " +
                        "con los heridos reportados hasta el momento.Desde Grupo Primax, nuestra prioridad son las personas," +
                        " por lo que desde que se tomó conocimiento de este lamentable accidente, nos pusimos en contacto con el " +
                        "propietario de la estación de servicios, para acompañar la atención de la emergencia." +
                        "Con relación a las causas del accidente, " +
                        "estas aún son materia de investigación por parte de las autoridades. Desde Grupo Primax lamentamos nuevamente lo ocurrido y reafirmamos " +
                        "nuestro total compromiso con los heridos y familiares de la víctima.");
                break;
            case 1:
                System.out.println("Gracias a la donación del laboratorio de cómputo, 120 estudiantes de la institución se beneficiarán " +
                        "con la conectividad y acceso digital para seguir potenciando sus capacidades.Lima, mayo del 2024. PRIMAX, " +
                        "empresa líder en el sector energético, como parte de su programa de responsabilidad social y sostenibilidad" +
                        " “Energía que nos conecta” implementó la donación del laboratorio de cómputo al Centro de Educación Básica" +
                        " Especial “Corazón de María”, de Magdalena.");
                break;
            case 2:
                System.out.println("Con este lanzamiento, la marca busca mejorar la experiencia de sus usuarios innovando " +
                        "en el e-commerce Tras un año marcado por una tendencia en donde más de 23 millones de peruanos adoptaron " +
                        "los canales digitales en sus hábitos de compra debido a la practicidad, conveniencia y ahorro de tiempo;" +
                        " Primaxgas, la marca de gas doméstico de Primax, dio un paso significativo hacia la innovación de su servicio," +
                        " anunciando el lanzamiento de su nueva plataforma online para la comercialización de balones de gas en Perú.");
                break;
            case 3:
                System.out.println("El Perú es rico en un recurso energético clave para la transición energética: el gas natural." +
                        " Extraído desde Camisea, en Cusco, el gas natural para su uso vehicular o GNV, se ha convertido en un aliado " +
                        "de los transportistas y del medio ambiente, siendo conocido como un combustible ‘eco amigable’, ya que produce " +
                        "un 25% menos de dióxido de carbono (CO2) en comparación con la gasolina y en menos de 150% si se compara" +
                        " con el diésel.Los especialistas mencionan que esta energía es mucho más limpia porque además de generar" +
                        " menos emisiones de CO2 por cada kilómetro recorrido, genera mínimas cantidades de azufre, mercurio y otras" +
                        " partículas, lo que la convierte en una energía que ayuda a disminuir el calentamiento global, reducir el efecto " +
                        "invernadero y mejorar la calidad del aire.");
                break;
            case 4:
                System.out.println("PRIMAX, compañía líder en el sector energía, presentó las instalaciones remodeladas de su estación de " +
                        "servicio ubicada en el kilómetro 97 de la Panamericana Sur en Asia. El rediseño de esta estación que forma parte " +
                        "de la estrategia de la marca de seguir brindando a sus clientes una experiencia de consumo memorable contempla un " +
                        "ecosistema de servicios en un área de más de 4 mil metros cuadrados que incluye la tienda de conveniencia de la marca," +
                        " LiSTO! y una renovada zona de recarga de combustible.La remodelación de la estación también contempla la implementación" +
                        " de una señalética, un “pole sign” o poste de señal, que facilita la visibilidad de los conductores y peatones. Asimismo," +
                        " para incentivar la visita a su estación, la marca tiene promociones y beneficios exclusivos para sus clientes que incluyen " +
                        "descuentos en la categoría de comida rápida, cervezas y licores. De igual manera, para el consumo de su combustible" +
                        " premium G-PREMIUM G-PRIX, ofrecen quintuplicar puntos Bonus por cada tanqueada.");
                break;
            case 5:
                System.out.println("El Gas Natural Vehicular (GNV) es uno de los combustibles más demandados por los peruanos. Según indica " +
                        "el portal Infogas, al 2023 se han convertido 74,506 vehículos de gasolina a gas. Para que este recurso " +
                        "energético llegue a más peruanos, se necesita inversiones en infraestructura de estaciones de servicio para que" +
                        " los transportistas puedan abastecerse de esta energía más económica, limpia y eficiente." +
                        "Una de las marcas del sector energía que promueve este tipo de energía en el Perú es PRIMAX, que cuenta " +
                        "con 66 estaciones de GNV a nivel nacional, consolidándose hasta el momento como la red más amplia en el país " +
                        "y en un actor clave para cerrar las brechas de acceso a este recurso en el mercado automotor.");
            case 6 :
                System.out.println("Las empresas se enfrentan hoy al reto constante de generar valor más allá del negocio, " +
                        "tomando la sostenibilidad como un eje esencial en su estrategia. En esta línea, PRIMAX, la compañía \nlíder " +
                        "del sector energía, lanzó en el 2023 su Programa de Responsabilidad Social y Sostenibilidad: “Energía que nos\n" +
                        " conecta”, enfocada en los pilares de medio ambiente, inclusión y apoyo social.\n" +
                        "" +
                        "A través del equipo humano de voluntarios, la marca logró recuperar más de 5,500 metros cuadrados de áreas verdes \n" +
                        "en el distrito de Villa El Salvador en jornadas de arborización en el 2023. Estas iniciativas, que se ejecutaron\n" +
                        " con la participación de los vecinos y de la Municipalidad Metropolitana de Lima (MML) a través de su programa \n" +
                        "“Lima Verde”, demuestra la importancia del trabajo entre las empresas, autoridad y comunidad. Asimismo, bajo la \n" +
                        "línea de recuperación de espacios públicos, el equipo impulsó una jornada de limpieza de la playa de San Pedro en\n" +
                        " Lurín donde se recolectaron más de 300 kg de desperdicios sólidos.");
            case 7 :
                System.out.println("“Nos sentimos muy contentos por la gran acogida que tuvo esta iniciativa y aún más por la experiencia\n" +
                        " que vivieron nuestros clientes más fieles. Somos una marca de origen peruano que busca innovar en la categoría,\n" +
                        " desarrollando una propuesta de valor única en base a servicio y calidad”, sostuvo Julio Cortiguera, Gerente de\n" +
                        " Tiendas de PRIMAX." +
                        "La promoción fue lanzada en junio de este año con el objetivo de premiar a los consumidores de LiSTO! a nivel nacional,\n" +
                        "la misma que consistió en la entrega de paquetes dobles con pasajes a Sao Paulo, Brasil ida y vuelta, entradas al festival\n" +
                        " Tomorrowland, estadía y traslados durante los días de celebración.\n" +
                        "Cabe destacar que LiSTO! viene marcando hitos importantes en la categoría de tiendas de conveniencia en nuestro país.\n" +
                        "Es la primera marca en ingresar a campus universitarios, llevando la experiencia a más peruanos. Asimismo, apuesta por \n" +
                        "el desarrollo del concepto de foodvenience, la cual combina los productos típicos de las tiendas de conveniencia (snacks y bebidas)\n" +
                        " con la de comida fresca y rápida para consumir en cualquier momento del día.");
            case 8 :
                System.out.println("Martes 10 de octubre de 2023.- PRIMAX en Perú suma un nuevo hito que refuerza su liderazgo en la \n" +
                        "categoría de estaciones de servicio con el reconocimiento otorgado por la Asociación Nacional de Tiendas de\n" +
                        " Conveniencia de Estados Unidos (NACS, por sus siglas en inglés). Esta institución, líder a nivel mundial,\n" +
                        " distinguió a la compañía por desarrollar el ecosistema de estación de servicio más completo basado en una \n" +
                        "oferta de valor que incluye productos de calidad, así como una experiencia memorable tanto en las tiendas de\n" +
                        " conveniencia como en la zona de recarga de combustible.\n" +
                        "“El pilar de nuestra diferenciación es el ADN de retail enfocado en la experiencia al cliente. Apostamos por ser\n" +
                        " más que un punto de recarga de combustibles y esto nos ha llevado a desarrollar un ecosistema integral alrededor \n" +
                        "de la energía. Al visitarnos, nuestros clientes no solo pueden abastecer sus vehículos con combustible sino también\n" +
                        " tener de una experiencia memorable en nuestra tienda de conveniencia LiSTO!, disfrutando de la variedad de\n" +
                        " comida y café peruano premium que ofrece”, sostiene Yuri Proaño, Country Manager de PRIMAX en Perú.");
            case 9 :
                System.out.println("La empresa también escaló dos posiciones dentro de las empresas que operan en el sector de petróleo y gas.\n" +
                        "Lima, 27 de setiembre de 2023.-Contar con una oferta de valor única y de alta calidad, con operaciones sostenibles y colocando\n" +
                        " al cliente y a la comunidad en el centro de todas las iniciativas de su estrategia empresarial ha llevado a PRIMAX a ser \n" +
                        "reconocida dentro de las 50 empresas con mejor reputación corporativa en el Perú y la N° 2 en el sector de petróleo y gas, \n" +
                        "según el ránking Merco Empresas 2023." +
                        "Este logro se da luego de que la empresa escalara en menos de un año 42 posiciones ubicándose en el puesto 46 en la lista\n" +
                        " general de empresas peruanas y dos posiciones más dentro de las empresas que operan en el sector de petróleo y gas.\n" +
                        "\n" +
                        "En la cadena del downstream, conformada en parte por los que comercializan combustible, PRIMAX ha respondido siempre\n" +
                        " a los retos de la realidad energética con la apuesta de ir más allá de la comercialización y crear un ecosistema de valor\n" +
                        " que logre transformar la vida de las personas.");
            case 10 :
                System.out.println("Lima, 23 de septiembre de 2023.- La minería es fundamental para el crecimiento económico del país. De acuerdo \n" +
                        "con el Ministerio de Energía y Minas, el sector ocupa cerca del 15% del PBI nacional. Esta industria necesita de fuentes\n" +
                        " de energía para operar, a través de proveedores que cuenten con altos estándares de calidad y seguridad. En ese sentido, \n" +
                        "Primax, compañía líder del sector energía, acompaña a la industria minera con una oferta completa de productos y servicios,\n" +
                        " de la mano de operaciones seguras, con respeto por el medioambiente y suministro aventajado.\n" +
                        "La oferta de PRIMAX es completa y multienergía pues la marca cuenta con combustibles líquidos, gas industrial, lubricantes \n" +
                        "y otros productos complementarios, su oferta de valor también la compone su servicio integral, que atiende a 45 empresas \n" +
                        "que tienen a cargo las operaciones mineras más importantes del Perú, y el acompañamiento va desde la venta del combustible, \n" +
                        "hasta el transporte, construcción y operación de los grifos en los campamentos, junto a un servicio post venta de seguimiento \n" +
                        "y control del abastecimiento.\n" +

                        "Para la industria minera es clave contar con productos de manera oportuna, es por ello que PRIMAX ha gestionado un suministro\n" +
                        " aventajado que cuenta con cuatro fuentes de abastecimiento y 16 terminales de almacenamiento a nivel nacional, lo que garantiza \n" +
                        "la disponibilidad de productos para las operaciones mineras. Esto representa una fortaleza porque permite el abastecimiento frente \n" +
                        "a contextos desafiantes.\n" +

                        "La marca multilatina se ha convertido en la única en la industria en contar con una plataforma como ‘Primax Solutions’, la misma\n" +
                        " que informa sobre el consumo de combustible, estándares de mantenimientos, capacitaciones, y otorga a sus clientes una herramienta\n" +
                        " tecnológica de seguimiento y de información real sobre el servicio para sus operaciones mineras.");
            default:
                System.out.println("Noticia no válida.");
        }
    }


    public static void main(String[] args) {
        logi nShalomEl = new logi();

        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░▒▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░▒▒▓▓▓▓▒░░░░░▒░░░░░░░░░░▓▓▓▓▓▓▓▓▒░░▓▓▓▓▓▓▓▓▒░░▓▓▓░░▓▓▓▓░░░░░▓▓▓▓░░░░▓▓▓▓▒░░░░▓▓▓▒░░▓▓▓░\n░░▒▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░▓▓▓▒░░▒▓▓░░▓▓▓░░░▓▓▓░░▓▓▓░░▓▓▓▓▓░░░▓▓▓▓▓░░░▒▓▓▓▓▓░░░░░▒▓▓▓▓▓▒░░\n░▒▒▒▒▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░▓▓▓▒▒▒▓▓▓░░▓▓▓▒▒▓▓▓▓░░▓▓▓░░▓▓▓▓▓▒░▓▓▓▓▓▓░░░▓▓▒░▓▓▓░░░░░▒▓▓▓▓░░░\n▒▒▒▒▒▒▓▓▓▓▓▒░░░░░░░░░░░░░░░▓▓▓▓▒▒▒▒░░░▓▓▓▒▒▓▓▓░░░▓▓▓░░▓▓▓░▓▓▓▓▓░▓▓▓░░▓▓▓▓▓▓▓▓▒░░░▒▓▓▒▓▓▓░░\n░▒▒▒▒▓▓▓▓▓▒░░░░░▒░░░░░░░░░░▓▓▓▒░░░░░░░▓▓▓░░▒▓▓▓░░▓▓▓░░▓▓▓░░▓▓▓░░▓▓▓░▒▓▓▒░░░▓▓▓▒░▓▓▓░░░▓▓▓░\n░░░▒▒▓▓▓▒░░░░░░▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░▒▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n");
        Scanner sc = new Scanner(System.in);

        nShalomEl.opciones();
        nShalomEl.login();
        opciones1();
        sc.close();
    }
}