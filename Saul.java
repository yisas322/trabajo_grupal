import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Saul {
    public Saul() {
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
                lubricantes(sc);
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
                cumunicaciones();
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

                if (VerificarTarjeta(numeroTarjeta, fechaVencimiento, cvv, totalGasolina)) {
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

    private static boolean VerificarTarjeta(String numeroTarjeta, String fechaVencimiento, String cvv, double monto) {
        if (numeroTarjeta.length() == 16 && fechaVencimiento.length() == 5 && cvv.length() == 3) {
            System.out.println("Procesando pago de S/ " + monto + "...");
            return true;
        }
        System.out.println("Datos de tarjeta inválidos.");
        return false;
    }

    private static void generarBoleta(String nombreCliente, String fechaActual, String metodoPago, double precioGasolina, double igv, double totalConIGV, double montoEntregado, double cambio) {
        System.out.println("---------------------------------");
        System.out.println(".........Boleta de Pago..........");
        System.out.println("---------------------------------");
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Fecha: " + fechaActual);
        System.out.println("......GRACIAS POR SU COMPRA......");
        System.out.println("---------------------------------");
        System.out.println("Descripción: Gasolina seleccionada");
        System.out.println("Precio sin IGV: S/ " + precioGasolina);
        System.out.println("IGV (18%): S/ " + igv);
        System.out.println("Total a pagar: S/ " + totalConIGV);
        System.out.println("Método de Pago: " + metodoPago);
        if (metodoPago.equals("Efectivo")) {
            System.out.println("Monto Entregado: S/ " + montoEntregado);
            System.out.println("Cambio: S/ " + cambio);
        }
        System.out.println("---------------------------------");
    }
    public static void lubricantes(Scanner gaa) {
        Scanner scanner = new Scanner(System.in);
        String[] lubricantes = new String[]{"SHELL HELIX ULTRA", "SHELL HELIX HX8", "SHELL RIMULA", "SHELL HELIX HX5", "SHELL HELIX HX3"};
        double[] precios = new double[]{275.6, 244.57, 126.6, 122.23, 116.91};
        System.out.println("\nLubricantes disponibles:");

        int tipoLubricante;
        for(tipoLubricante = 0; tipoLubricante < lubricantes.length; ++tipoLubricante) {
            System.out.println(tipoLubricante + 1 + "- Lubricante " + lubricantes[tipoLubricante] + " ---> S/ " + precios[tipoLubricante]);
        }

        do {
            System.out.print("Ingrese el tipo de lubricante (1, 2, 3, 4, 5): ");
            tipoLubricante = scanner.nextInt();
            if (tipoLubricante < 1 || tipoLubricante > 5) {
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while(tipoLubricante < 1 || tipoLubricante > 5);

        System.out.print("Cantidad de lubricantes: ");
        int cantidadLubricante = scanner.nextInt();
        double precioLubricante = precios[tipoLubricante - 1];
        double totalLubricante = precioLubricante * (double)cantidadLubricante;
        double igv = totalLubricante * 0.18;
        double montoFinal = totalLubricante + igv;
        System.out.println("\n--- Métodos de Pago ---");
        System.out.println("1- Pago en Efectivo");
        System.out.println("2- Pago con Tarjeta");
        System.out.print("Seleccione el método de pago (1 o 2): ");
        int metodoPago = scanner.nextInt();
        switch (metodoPago) {
            case 1:
                pagarEnEfectivo(montoFinal, igv);
                break;
            case 2:
                if (procesarPagoConTarjeta(scanner, montoFinal, igv)) {
                    System.out.println("\nCompra exitosa. Total pagado: S/ " + montoFinal);
                } else {
                    System.out.println("\nPago fallido. Intente nuevamente.");
                }
                break;
            default:
                System.out.println("Opción no válida");
        }

    }

    private static void pagarEnEfectivo(double total, double igv) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Pago en Efectivo ---");
        System.out.println("Subtotal de lubricantes: S/ " + (total - igv));
        System.out.println("IGV (18%): S/ " + igv);
        System.out.println("Monto total a pagar: S/ " + total);
        System.out.print("Ingrese el monto pagado en efectivo: ");
        double montoPagado = scanner.nextDouble();
        if (montoPagado < total) {
            System.out.println("Monto insuficiente. Operación cancelada.");
        } else {
            double cambio = montoPagado - total;
            System.out.println("Compra exitosa. Total pagado en efectivo: S/ " + montoPagado);
            System.out.println("Su cambio es: S/ " + cambio);
        }

    }

    private static boolean procesarPagoConTarjeta(Scanner scanner, double monto, double igv) {
        System.out.println("\n--- Pago con Tarjeta de Crédito ---");
        System.out.print("Ingrese el nombre del titular de la tarjeta: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el número de la tarjeta de crédito (16 dígitos): ");

        String numeroTarjeta;
        for(numeroTarjeta = scanner.next(); numeroTarjeta.length() != 16; numeroTarjeta = scanner.next()) {
            System.out.println("Número de tarjeta inválido. Debe tener 16 dígitos.");
            System.out.print("Ingrese el número de la tarjeta de crédito (16 dígitos): ");
        }

        System.out.print("Ingrese el mes de vencimiento (MM): ");
        String mesVencimiento = scanner.next();
        System.out.print("Ingrese el año de vencimiento (AA): ");

        for(String anoVencimiento = scanner.next(); mesVencimiento.length() != 2 || anoVencimiento.length() != 2; anoVencimiento = scanner.next()) {
            System.out.println("Formato de fecha inválido. Debe ser MM/AA.");
            System.out.print("Ingrese el mes de vencimiento (MM): ");
            mesVencimiento = scanner.next();
            System.out.print("Ingrese el año de vencimiento (AA): ");
        }

        System.out.print("Ingrese el código CVV (3 dígitos): ");

        for(String cvv = scanner.next(); cvv.length() != 3; cvv = scanner.next()) {
            System.out.println("Código CVV inválido. Debe tener 3 dígitos.");
            System.out.print("Ingrese el código CVV (3 dígitos): ");
        }

        boolean pagoExitoso = verificarTarjeta(nombre, numeroTarjeta, monto);
        if (pagoExitoso) {
            System.out.println("\n--- Boleta ---");
            System.out.println("Subtotal de lubricantes: S/ " + (monto - igv));
            System.out.println("IGV (18%): S/ " + igv);
            System.out.println("Monto total a pagar: S/ " + monto);
        }

        return pagoExitoso;
    }

    private static boolean verificarTarjeta(String nombre, String numeroTarjeta, double monto) {
        System.out.println("\n--- Verificando información de la tarjeta ---");
        System.out.println("Nombre del titular: " + nombre);
        return true;
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
        System.out.println("---------------------------------");
        System.out.println(".........Boleta de Pago..........");
        System.out.println("---------------------------------");
        System.out.println("......GRACIAS POR SU COMPRA......");
        System.out.println("---------------------------------");
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Descripción: Gas seleccionado");
        System.out.println("Precio sin IGV: S/ " + precioGas);
        System.out.println("IGV (18%): S/ " + igv);
        System.out.println("Total a pagar: S/ " + totalConIGV);
        System.out.println("Método de Pago: " + metodoPago);
        if (metodoPago.equals("Efectivo")) {
            System.out.println("Monto Entregado por " + nombreCliente + ": S/ " + montoEntregado);
            System.out.println("Cambio: S/ " + cambio);
        }

        System.out.println("---------------------------------");
    }

    public static void comprarlistol(Scanner gaa) {
        boolean continuarPedido = true;
        double totalPedido = 0.0;
        double IGV = 0.18;
        String[] productos = new String[]{"Sandwich Clasica", "Pan con queso", "Pan de hot dog", "Pan con huevo", "Lays", "Chocman", "Oreo", "Casino", "Wafer", "Coca kola", "Inka kola", "Yogurt", "Cafe", "Té", "Chicha morada", "Chicha de Jora"};
        double[] precios = new double[]{3.0, 1.0, 2.0, 2.5, 2.0, 1.5, 2.0, 1.5, 2.0, 3.0, 3.5, 4.5, 1.5, 1.0, 1.0, 1.0};
        int[] cantidadesVendidas = new int[productos.length];
        System.out.println("¡¡BUEN DIA!! ¿Qué se le ofrece?");
        System.out.println("------OJO------ Una vez que haya finalizado sus compras, cancele el proceso seleccionando el N°17");
        System.out.println("Seleccione el N° que quiera para hacer su compra");

        while(true) {
            while(continuarPedido) {
                System.out.println("\nMenú:");

                int opcion;
                for(opcion = 0; opcion < productos.length; ++opcion) {
                    System.out.println(opcion + 1 + ". " + productos[opcion] + " - S/" + precios[opcion]);
                }

                System.out.println("17. Cancelar el pedido");
                System.out.print("\nIngrese el número de su elección: ");
                opcion = gaa.nextInt();
                if (opcion >= 1 && opcion <= productos.length) {
                    System.out.print("Ingrese la cantidad: ");
                    int cantidad = gaa.nextInt();
                    cantidadesVendidas[opcion - 1] += cantidad;
                    totalPedido += (double)cantidad * precios[opcion - 1];
                    System.out.println("Ha seleccionado " + productos[opcion - 1] + ": S/" + (double)cantidad * precios[opcion - 1]);
                } else if (opcion == 17) {
                    continuarPedido = false;
                } else {
                    System.out.println("--Opción inválida-- seleccione el N° del menú.");
                }
            }

            double montoIGV = totalPedido * 0.18;
            double totalConIGV = totalPedido + montoIGV;
            System.out.println("\nDetalle de compra:");

            int i;
            for(i = 0; i < productos.length; ++i) {
                if (cantidadesVendidas[i] > 0) {
                    System.out.println(productos[i] + ": " + cantidadesVendidas[i] + " unidades");
                }
            }

            System.out.println("Subtotal: S/" + totalPedido);
            System.out.println("IGV (18%): S/" + montoIGV);
            System.out.println("Total a pagar (incluido IGV): S/" + totalConIGV);
            System.out.println("\nSeleccione el método de pago:");
            System.out.println("1. Efectivo");
            System.out.println("2. Tarjeta de crédito");
            i = gaa.nextInt();
            double cambio;
            switch (i) {
                case 1:
                    System.out.print("Ingrese el monto entregado: S/");
                    double montoEntregado = gaa.nextDouble();
                    if (montoEntregado >= totalConIGV) {
                        cambio = montoEntregado - totalConIGV;
                        System.out.println("Pago recibido. Su cambio es: S/" + cambio);
                    } else {
                        System.out.println("Monto insuficiente. Por favor, pague al menos S/" + totalConIGV);
                    }
                    break;
                case 2:
                    if (totalConIGV > 200.0) {
                        System.out.println("El monto total excede los 200 soles, no se puede pagar con tarjeta de crédito.");
                    } else {
                        cambio = 200.0 - totalConIGV;
                        System.out.println("Pago con tarjeta recibido por un monto de S/200.00. Gracias por su compra.");
                        System.out.println("Se le devolverá el cambio de S/" + cambio);
                    }
                    break;
                default:
                    System.out.println("Método de pago inválido. Por favor, intente de nuevo.");
            }

            System.out.println("\n¡Vuelve pronto!");
            gaa.close();
            return;
        }
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
                while(true) {
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
                }

                System.out.print("¿Desea realizar otro lavado? (si/no): ");
                continuar = yzz.next();
            } while(continuar.equalsIgnoreCase("si"));

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

    public static void cumunicaciones() {
        Scanner gaa = new Scanner(System.in);
        System.out.println("===NOTICAS RELEVANTES===\n1. COMUNICADO GRUPO PRIMAX – MAYO 20 DEL 2024\n2. Educación digital inclusiva: Más de 100 estudiantes del CEBE Corazón de María de Magdalena se benefician con laboratorio de cómputo donado por Primax\n3.PRIMAXGAS POTENCIA SU SERVICIO DE DELIVERY Y LANZA SU NUEVO CANAL DE VENTAS DIGITAL\n4.GNV ¿POR QUÉ ES CONSIDERADO UNA ENERGÍA ECO AMIGABLE?\n5.PRIMAX RENUEVA SU ESTACIÓN DE SERVICIOS UBICADA EN EL KILÓMETRO 97 DE ASIA");
        int opciones = gaa.nextInt();
        switch (opciones) {
            case 1:
                comunicado(gaa);
                break;
            case 2:
                educacion(gaa);
                break;
            case 3:
                potencia(gaa);
                break;
            case 4:
                GNV(gaa);
            case 5:
                RENUEVA(gaa);
            default:
                System.out.println("Opción no válida.");
        }

        gaa.close();
    }

    public static void comunicado(Scanner scanner) {
        System.out.println("Lima, 20 de mayo del 2024. Sobre la explosión ocurrida esta tarde en una planta de compresión de Gas Natural contigua a una estación de servicios, de propiedad y operada por un socio afiliado a nuestra marca, ubicada en Villa María del Triunfo, Grupo Primax informa a la opinión pública:\n\nQue lamentamos profundamente lo ocurrido y expresamos nuestro sentido pésame a la familia de la persona que ha fallecido en este accidente y nuestra total solidaridad con los heridos reportados hasta el momento.\n\nDesde Grupo Primax, nuestra prioridad son las personas, por lo que desde que se tomó conocimiento de este lamentable accidente, nos pusimos en contacto con el propietario de la estación de servicios, para acompañar la atención de la emergencia.\n\nCon relación a las causas del accidente, estas aún son materia de investigación por parte de las autoridades. Desde Grupo Primax lamentamos nuevamente lo ocurrido y reafirmamos nuestro total compromiso con los heridos y familiares de la víctima.:");
    }

    public static void educacion(Scanner scanner) {
        System.out.println("Gracias a la donación del laboratorio de cómputo, 120 estudiantes de la institución se beneficiarán con la conectividad y acceso digital para seguir potenciando sus capacidades.\nLima, mayo del 2024. PRIMAX, empresa líder en el sector energético, como parte de su programa de responsabilidad social y sostenibilidad “Energía que nos conecta” implementó la donación del laboratorio de cómputo al Centro de Educación Básica Especial “Corazón de María”, de Magdalena.");
    }

    public static void potencia(Scanner scanner) {
        System.out.println("Con este lanzamiento, la marca busca mejorar la experiencia de sus usuarios innovando en el e-commerce\nTras un año marcado por una tendencia en donde más de 23 millones de peruanos adoptaron los canales digitales en sus hábitos de compra debido a la practicidad, conveniencia y ahorro de tiempo; Primaxgas, la marca de gas doméstico de Primax, dio un paso significativo hacia la innovación de su servicio, anunciando el lanzamiento de su nueva plataforma online para la comercialización de balones de gas en Perú.");
    }

    public static void GNV(Scanner scanner) {
        System.out.println("El Perú es rico en un recurso energético clave para la transición energética: el gas natural. Extraído desde Camisea, en Cusco, el gas natural para su uso vehicular o GNV, se ha convertido en un aliado de los transportistas y del medio ambiente, siendo conocido como un combustible ‘eco amigable’, ya que produce un 25% menos de dióxido de carbono (CO2) en comparación con la gasolina y en menos de 150% si se compara con el diésel.\n\nLos especialistas mencionan que esta energía es mucho más limpia porque además de generar menos emisiones de CO2 por cada kilómetro recorrido, genera mínimas cantidades de azufre, mercurio y otras partículas, lo que la convierte en una energía que ayuda a disminuir el calentamiento global, reducir el efecto invernadero y mejorar la calidad del aire.");
    }

    public static void RENUEVA(Scanner scanner) {
        System.out.println("PRIMAX,compañía líder en el sector energía, presentó las instalaciones remodeladas de su estación de servicio ubicada en el kilómetro 97 de la Panamericana Sur en Asia. El rediseño de esta estación que forma parte de la estrategia de la marca de seguir brindando a sus clientes una experiencia de consumo memorable contempla un ecosistema de servicios en un área de más de 4 mil metros cuadrados que incluye la tienda de conveniencia de la marca, LiSTO! y una renovada zona de recarga de combustible.\n\nLa remodelación de la estación también contempla la implementación de una señalética, un “pole sign” o poste de señal, que facilita la visibilidad de los conductores y peatones. Asimismo, para incentivar la visita a su estación, la marca tiene promociones y beneficios exclusivos para sus clientes que incluyen descuentos en la categoría de comida rápida, cervezas y licores. De igual manera, para el consumo de su combustible premium G-PREMIUM G-PRIX, ofrecen quintuplicar puntos Bonus por cada tanqueada.");
    }

    public static void main(String[] args) {
        Grifo_primax nShalomEl = new Grifo_primax();

        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░▒▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░▒▒▓▓▓▓▒░░░░░▒░░░░░░░░░░▓▓▓▓▓▓▓▓▒░░▓▓▓▓▓▓▓▓▒░░▓▓▓░░▓▓▓▓░░░░░▓▓▓▓░░░░▓▓▓▓▒░░░░▓▓▓▒░░▓▓▓░\n░░▒▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░▓▓▓▒░░▒▓▓░░▓▓▓░░░▓▓▓░░▓▓▓░░▓▓▓▓▓░░░▓▓▓▓▓░░░▒▓▓▓▓▓░░░░░▒▓▓▓▓▓▒░░\n░▒▒▒▒▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░▓▓▓▒▒▒▓▓▓░░▓▓▓▒▒▓▓▓▓░░▓▓▓░░▓▓▓▓▓▒░▓▓▓▓▓▓░░░▓▓▒░▓▓▓░░░░░▒▓▓▓▓░░░\n▒▒▒▒▒▒▓▓▓▓▓▒░░░░░░░░░░░░░░░▓▓▓▓▒▒▒▒░░░▓▓▓▒▒▓▓▓░░░▓▓▓░░▓▓▓░▓▓▓▓▓░▓▓▓░░▓▓▓▓▓▓▓▓▒░░░▒▓▓▒▓▓▓░░\n░▒▒▒▒▓▓▓▓▓▒░░░░░▒░░░░░░░░░░▓▓▓▒░░░░░░░▓▓▓░░▒▓▓▓░░▓▓▓░░▓▓▓░░▓▓▓░░▓▓▓░▒▓▓▒░░░▓▓▓▒░▓▓▓░░░▓▓▓░\n░░░▒▒▓▓▓▒░░░░░░▓▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n░░░░░░░░░░░░░░▒▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n");
        Scanner sc = new Scanner(System.in);
        nShalomEl.opciones();
        nShalomEl.login();
        opciones1();
        sc.close();
    }
}
