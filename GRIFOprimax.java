import java.util.Scanner;
public class GRIFOprimax {
    public static void opciones() {
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
                cumunicaciones();
                break;


            default:
                System.out.println("Opción no válida.");
                break;

        }

        sc.close();
    }
    public static void comprarGasolina(Scanner gaa) {
        System.out.println("\nTipos de gasolina disponibles:");
        System.out.println("1. Max-D - S/15.99 por galón");
        System.out.println("2. G-Regular - S/16.79 por galón");
        System.out.println("3. G-Premium - S/17.99 por galón");

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

        double precioGasolina = 0;
        switch (tipoGasolina) {
            case 1:
                precioGasolina = 15.99;
                break;
            case 2:
                precioGasolina = 16.79;
                break;
            case 3:
                precioGasolina = 17.99;
                break;
        }
        double totalGasolina = precioGasolina * cantidadGasolina;
        System.out.println("\n--- Métodos de Pago ---");
        System.out.println("1- Pago en Efectivo");
        System.out.println("2- Pago con Tarjeta");

        System.out.print("Seleccione el método de pago (1 o 2): ");
        int metodoPago = gaa.nextInt();

        switch (metodoPago) {
            case 1:
                System.out.println("\n--- Pago en Efectivo ---");
                System.out.println("Son  " + totalGasolina + " en total");
                System.out.println("Compra exitosa. Total pagado en efectivo: S/ " + totalGasolina);
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
        if (numeroTarjeta.length() == 16 && fechaVencimiento.length()==5 && cvv.length() == 3) {
            System.out.println("Procesando pago de S/ " + monto + "...");
            return true;
        }
        System.out.println("Datos de tarjeta inválidos.");
        return false;
    }
    public static void comprarLubricantes(Scanner gaa) {
        Scanner gaaa = new Scanner(System.in);
        System.out.println("\nlubricantes disponibles:");
        System.out.println("1-lubricante SHELL HELIX ULTRA ---> S/ 275.60");
        System.out.println("2-lubricante SHELL HELIX HX8 ---> S/ 244.57");
        System.out.println("3-lubricante SHELL RIMULA  ---> S/ 126.60");
        System.out.println("4-lubricante SHELL HELIX HX5  ---> S/ 122.23");
        System.out.println("5-lubricante SHELL HELIX HX3 ---> S/ 116.91");
        int tipolubricante;
        do {
            System.out.print("Ingrese el tipo de gasolina (1, 2, 3,4,5): ");
            tipolubricante = gaa.nextInt();

            if (tipolubricante < 1 || tipolubricante > 5) {
                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (tipolubricante < 1 || tipolubricante > 5);

        System.out.print("Cantidad de galones: ");
        int cantidad_lubricante = gaa.nextInt();

        double precio_lubricante = 0;
        switch (tipolubricante) {
            case 1:
                precio_lubricante = 275.60;
                break;
            case 2:
                precio_lubricante = 244.57;
                break;
            case 3:
                precio_lubricante = 126.60;
                break;
            case 4:
                precio_lubricante = 122.23;
                break;
            case 5:
                precio_lubricante = 116.91;
                break;
            default:

                System.out.println("Opción no válida.");
                return;


        }

        double totallubricante = precio_lubricante * cantidad_lubricante;
        System.out.println("\n--- Métodos de Pago ---");
        System.out.println("1- Pago en Efectivo");
        System.out.println("2- Pago con Tarjeta");

        System.out.print("Seleccione el método de pago (1 o 2): ");
        int metodoPago = gaa.nextInt();

        switch (metodoPago) {
            case 1:
                pagarEnEfectivo(totallubricante);
                break;
            case 2:
                if (procesarPagoConTarjeta(gaa, totallubricante)) {
                    System.out.println("\nCompra exitosa. Total pagado: S/ " + totallubricante);
                } else {
                    System.out.println("\nPago fallido. Intente nuevamente.");
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void pagarEnEfectivos(double total) {
        System.out.println("\n--- Pago en Efectivo ---");
        System.out.println("Por favor, pague en caja el monto de S/ " + total);
        System.out.println("Compra exitosa. Total pagado en efectivo: S/ " + total);
    }

    private static boolean procesarPagoConTarjetas(Scanner scanner, double monto) {
        System.out.println("\n--- Pago con Tarjeta de Crédito ---");

        System.out.print("Ingrese el número de la tarjeta de crédito: ");
        String numeroTarjeta = scanner.next();

        System.out.print("Ingrese la fecha de vencimiento (MM/AA): ");
        String fechaVencimiento = scanner.next();

        System.out.print("Ingrese el código CVV: ");
        String cvv = scanner.next();

        boolean pagoExitoso = verificarTarjeta(numeroTarjeta, fechaVencimiento, cvv, monto);

        return pagoExitoso;
    }

    private static boolean verificarTarjetas(String numeroTarjeta, String fechaVencimiento, String cvv, double monto) {
        if (numeroTarjeta.length() == 16 && fechaVencimiento.length() == 4 && cvv.length() == 3) {
            System.out.println("Procesando pago de S/ " + monto + "...");
            return true;
        }
        System.out.println("Datos de tarjeta inválidos.");
        return false;
    }

    public static void comprarGas(Scanner scanner) {
        System.out.println("---  GASES DISPONIBLES ---");
        System.out.println("1- Balón de 10kg ");
        System.out.println("2- Balón de 15kg ");
        System.out.println("3- Balón de 45kg ");

        System.out.print("Ingrese el tipo de gas que desea: (1, 2, 3): ");
        int tipoGas = scanner.nextInt();
        double precioGas = 0;
        switch (tipoGas) {
            case 1:
                precioGas = 50.00;
                break;
            case 2:
                precioGas = 70.00;
                break;
            case 3:
                precioGas = 140.00;
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        double totalGas = precioGas;
        System.out.println("\n--- Métodos de Pago ---");
        System.out.println("1- Pago en Efectivo");
        System.out.println("2- Pago con Tarjeta");

        System.out.print("Seleccione el método de pago (1 o 2): ");
        int metodoPago = scanner.nextInt();

        switch (metodoPago) {
            case 1:
                pagarEnEfectivo(totalGas);
                break;
            case 2:
                if (procesarPagoConTarjeta(scanner, totalGas)) {
                    System.out.println("\nCompra exitosa. Total pagado: S/ " + totalGas);
                } else {
                    System.out.println("\nPago fallido. Intente nuevamente.");
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void pagarEnEfectivo(double total) {
        System.out.println("\n--- Pago en Efectivo ---");
        System.out.println("Por favor, pague en caja el monto de S/ " + total);
        System.out.println("Compra exitosa. Total pagado en efectivo: S/ " + total);
    }

    private static boolean procesarPagoConTarjeta(Scanner aulus, double monto) {
        System.out.println("\n--- Pago con Tarjeta de Crédito ---");

        System.out.print("Ingrese el número de la tarjeta de crédito: ");
        String numeroTarjeta = aulus.next();

        System.out.print("Ingrese la fecha de vencimiento (MM/AA): ");
        String fechaVencimiento = aulus.next();

        System.out.print("Ingrese el código CVV: ");
        String cvv = aulus.next();

        boolean pagoExitoso = verificarTarjeta(numeroTarjeta, fechaVencimiento, cvv, monto);

        return pagoExitoso;
    }

    private static boolean verificarTarjeta(String numeroTarjeta, String fechaVencimiento, String cvv, double monto) {

        if (numeroTarjeta.length() == 16 && fechaVencimiento.length() == 4 && cvv.length() == 3) {
            System.out.println("Procesando pago de S/ " + monto + "...");
            return true;
        }
        System.out.println("Datos de tarjeta inválidos.");
        return false;
    }
    public static void comprarlistol(Scanner gaa) {
        Scanner gaaa = new Scanner(System.in);
        boolean continuarPedido = true;
        double totalPedido = 0.0;

        System.out.println("¡¡BUEN DIA!! Que se le ofrece ");
        System.out.println("------OJO------Una vez que haya finalizado sus compras, cancele el proceso- seleccionando el N°17");
        System.out.println("Seleccione el ----N°---- que quiera para hacer su compra");

        while (continuarPedido) {
            System.out.println("\nMenú:");
            System.out.println("1. sandwich Clasica -  S/3.00");
            System.out.println("2. pan con queso - S/1.00");
            System.out.println("3. pan de hot dog - S/2.00");
            System.out.println("4. pan con huevo - S/2.50");
            System.out.println("5. Lays --Papitas-- - s/2.00");
            System.out.println("6. Chocman - s/1.50");
            System.out.println("7. Oreo - s/2.00");
            System.out.println("8. Casino - s/1.50");
            System.out.println("9. Wafer - s/2.00");
            System.out.println("10. Coca kola - s/3.00");
            System.out.println("11. Inka kola - S/3.50");
            System.out.println("12. Yogurt - s/4.50");
            System.out.println("13. Cafe - S/1.50");
            System.out.println("14. Té - S/1.00");
            System.out.println("15. Chicha morada - S/1.00");
            System.out.println("16. Chicha de Jora - s/1.00");
            System.out.println("17. Cancelar el pedido");

            System.out.print("\nIngrese el número de su elección: ");
            int opcion = gaa.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la cantidad de su sandwich: ");
                    int cantidad1 = gaa.nextInt();
                    totalPedido += cantidad1 * 3.00;
                    System.out.println("Su sandwich clasica: S/" + (cantidad1 * 3.00));
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad de su pan con queso: ");
                    int cantidad2 = gaa.nextInt();
                    totalPedido += cantidad2 * 1.00;
                    System.out.println("Su pan con queso:S/" + (cantidad2 * 1.00));
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad de su pan con hot dogs: ");
                    int cantidad3 = gaa.nextInt();
                    totalPedido += cantidad3 * 2.00;
                    System.out.println("Su pan con hot dog: S/" + (cantidad3 * 2.00));
                    break;
                case 4:
                    System.out.print("Ingrese la cantidad de su pan con huevo: ");
                    int cantidad4 = gaa.nextInt();
                    totalPedido += cantidad4 * 2.50;
                    System.out.println("Su pan con huevo: S/" + (cantidad4 * 2.50));
                    break;
                case 5:
                    System.out.print("Ingrese la cantidad de Lays: ");
                    int cantidad5 = gaa.nextInt();
                    totalPedido += cantidad5 * 2.00;
                    System.out.println("Su Papas lays:S/" + (cantidad5 * 2.00));
                    break;
                case 6:
                    System.out.print("Ingrese la cantidad de Chocmans: ");
                    int cantidad6 = gaa.nextInt();
                    totalPedido += cantidad6 * 1.50;
                    System.out.println("Su Chocman: S/" + (cantidad6 * 1.50));
                    break;
                case 7:
                    System.out.print("Ingrese la cantidad de Oreos: ");
                    int cantidad7 = gaa.nextInt();
                    totalPedido += cantidad7 * 2.00;
                    System.out.println("Su Oreo: S/" + (cantidad7 * 2.00));
                    break;
                case 8:
                    System.out.print("Ingrese la cantidad de Casino: ");
                    int cantidad8 = gaa.nextInt();
                    totalPedido += cantidad8 * 1.50;
                    System.out.println("Su Casino:S/" + (cantidad8 * 1.50));
                    break;
                case 9:
                    System.out.print("Ingrese la cantidad de Wafer: ");
                    int cantidad9 = gaa.nextInt();
                    totalPedido += cantidad9 * 2.00;
                    System.out.println("Su Wafer :S/" + (cantidad9 * 2.00));
                    break;
                case 10:
                    System.out.print("Ingrese la cantidad de Coca kola: ");
                    int cantidad10 = gaa.nextInt();
                    totalPedido += cantidad10 * 3.00;
                    System.out.println("Su Coca kola:S/" + (cantidad10 * 3.00));
                    break;
                case 11:
                    System.out.print("Ingrese la cantidad de Inka kola: ");
                    int cantidad11 = gaa.nextInt();
                    totalPedido += cantidad11 * 3.50;
                    System.out.println("Su Inka kola:S/" + (cantidad11 * 3.50));
                    break;
                case 12:
                    System.out.print("Ingrese la cantidad de Yogurt: ");
                    int cantidad12 = gaa.nextInt();
                    totalPedido += cantidad12 * 4.50;
                    System.out.println("Su Yogurt:S/" + (cantidad12 * 4.50));
                    break;
                case 13:
                    System.out.print("Ingrese la cantidad de Cafe: ");
                    int cantidad13 = gaa.nextInt();
                    totalPedido += cantidad13 * 1.50;
                    System.out.println("Su Cafe:S/" + (cantidad13 * 1.50));
                    break;
                case 14:
                    System.out.print("Ingrese la cantidad de Té: ");
                    int cantidad14 = gaa.nextInt();
                    totalPedido += cantidad14 * 1.00;
                    System.out.println("Su Té:S/" + (cantidad14 * 1.00));
                    break;
                case 15:
                    System.out.print("Ingrese la cantidad de Chicha morada: ");
                    int cantidad15 = gaa.nextInt();
                    totalPedido += cantidad15 * 1.00;
                    System.out.println("Su Chicha morada:S/" + (cantidad15 * 1.00));
                    break;
                case 16:
                    System.out.print("Ingrese la cantidad de Chicha de Jora: ");
                    int cantidad16 = gaa.nextInt();
                    totalPedido += cantidad16 * 1.00;
                    System.out.println("Su Chicha de Jora:S/" + (cantidad16 * 1.00));
                    break;
                case 17:
                    continuarPedido = false;
                    break;
                default:
                    System.out.println("--Opción inválida-- seleccione el N° del menú.");
                    return;
            }
        }

        System.out.println("\nGracias por su compra. Su costo total es: S/" + totalPedido);

        // Método de pago
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta de crédito");

        int metodoPago = gaa.nextInt();

        switch (metodoPago) {
            case 1:
                System.out.print("Ingrese el monto entregado: S/");
                double montoEntregado = gaa.nextDouble();
                if (montoEntregado >= totalPedido) {
                    double cambio = montoEntregado - totalPedido;
                    System.out.println("Pago recibido. Su cambio es: S/" + cambio);
                } else {
                    System.out.println("Monto insuficiente. Por favor, pague al menos S/" + totalPedido);
                }
                break;
            case 2:
        }
        if (metodoPago == 2) {
            System.out.println("Ingrese el número de tarjeta de crédito:");
            long numeroTarjeta = gaa.nextLong();
            System.out.println("Ingrese el monto a cargar a la tarjeta:");
            int montoCarga = gaa.nextInt();
            System.out.println("Pago realisadp con su tarjeta de crédito.");
        } else {
            System.out.println("Opción de pago inválida.");
        }
        System.out.println("---gracias por usar consumis de nuestra tienda listol--");
        System.out.println("Precio de su consumo: S/" + totalPedido);
        {
            System.out.println("\nVuelve pronto");
            gaa.close();
        }
    }


    public static void carwash(Scanner alus) {

        Scanner niko = new Scanner(System.in);
        String nombreCarWash = "---PRIMAX CARWASH---";

        System.out.println("Bienvenido al " + nombreCarWash + ". Tenemos lavados básicos y premium.");

        while (true) {
            System.out.println("Seleccione el tipo de lavado:");
            System.out.println("1. Lavado Básico - S/20");
            System.out.println("2. Lavado Premium - S/30");
            int tipoLavado = niko.nextInt();

            int precio;
            if (tipoLavado == 1) {
                precio = 20;
            } else if (tipoLavado == 2) {
                precio = 30;
            } else {
                System.out.println("Opción inválida. Por favor, seleccione 1 o 2.");
                continue;
            }

            System.out.println("Seleccione el método de pago:");
            System.out.println("1. Efectivo");
            System.out.println("2. Tarjeta de Crédito");
            int metodoPago = alus.nextInt();

            if (metodoPago == 1) {
                System.out.println("Ingrese el monto de pago en efectivo:");
                int montoPago = alus.nextInt();
                if (montoPago >= precio) {
                    int cambio = montoPago - precio;
                    System.out.println("Pago recibido. Su cambio es de " + cambio + " soles. ¡Disfrute su lavado!");
                } else {
                    System.out.println("El monto ingresado es insuficiente. Por favor, ingrese un monto válido.");
                    continue;
                }
            } else if (metodoPago == 2) {
                System.out.println("Ingrese el número de tarjeta de crédito:");
                long numeroTarjeta = alus.nextLong();
                System.out.println("Ingrese el monto a pagar de la  tarjeta:");
                int montoCarga = alus.nextInt();
                System.out.println("Pago recibido a través de tarjeta de crédito. ¡Disfrute su lavado!");
            } else {
                System.out.println("Opción de pago inválida.");
                continue;
            }

            System.out.println("---GRACIAS POR USAR NUESTRO SERVICIO DE PRIMAX CARWASH. Aqui tiene su boleta---");
            System.out.println("-------------------------------");
            System.out.println("         " + nombreCarWash);
            System.out.println("-------------------------------");
            System.out.println("Lavado: " + (tipoLavado == 1 ? "Básico" : "Premium"));
            System.out.println("Precio: S/" + precio);
            System.out.println("-------------------------------");

            System.out.println("¿Desea realizar otro lavado? (si/no)");
            String continuar = alus.next();
            if (!continuar.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("Gracias por usar nuestro servicio de carwash " + nombreCarWash + ".");
        }
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
                break;
        }

        gaa.close();
    }


    public static void comunicado(Scanner scanner) {
        System.out.println("Lima, 20 de mayo del 2024. Sobre la explosión ocurrida esta tarde en una planta de compresión de Gas Natural contigua a una estación de servicios, de propiedad y operada por un socio afiliado a nuestra marca, ubicada en Villa María del Triunfo, Grupo Primax informa a la opinión pública:\n" +
                "\n" +
                "Que lamentamos profundamente lo ocurrido y expresamos nuestro sentido pésame a la familia de la persona que ha fallecido en este accidente y nuestra total solidaridad con los heridos reportados hasta el momento.\n" +
                "\n" +
                "Desde Grupo Primax, nuestra prioridad son las personas, por lo que desde que se tomó conocimiento de este lamentable accidente, nos pusimos en contacto con el propietario de la estación de servicios, para acompañar la atención de la emergencia.\n" +
                "\n" +
                "Con relación a las causas del accidente, estas aún son materia de investigación por parte de las autoridades. Desde Grupo Primax lamentamos nuevamente lo ocurrido y reafirmamos nuestro total compromiso con los heridos y familiares de la víctima.:");

    }

    public static void educacion(Scanner scanner) {
        System.out.println("Gracias a la donación del laboratorio de cómputo, 120 estudiantes de la institución se beneficiarán con la conectividad y acceso digital para seguir potenciando sus capacidades.\n" +
                "Lima, mayo del 2024. PRIMAX, empresa líder en el sector energético, como parte de su programa de responsabilidad social y sostenibilidad “Energía que nos conecta” implementó la donación del laboratorio de cómputo al Centro de Educación Básica Especial “Corazón de María”, de Magdalena.");

    }

    public static void potencia(Scanner scanner) {
        System.out.println("Con este lanzamiento, la marca busca mejorar la experiencia de sus usuarios innovando en el e-commerce\n" +
                "Tras un año marcado por una tendencia en donde más de 23 millones de peruanos adoptaron los canales digitales en sus hábitos de compra debido a la practicidad, conveniencia y ahorro de tiempo; Primaxgas, la marca de gas doméstico de Primax, dio un paso significativo hacia la innovación de su servicio, anunciando el lanzamiento de su nueva plataforma online para la comercialización de balones de gas en Perú.");
    }

    public static void GNV(Scanner scanner) {
        System.out.println("El Perú es rico en un recurso energético clave para la transición energética: el gas natural. Extraído desde Camisea, en Cusco, el gas natural para su uso vehicular o GNV, se ha convertido en un aliado de los transportistas y del medio ambiente, siendo conocido como un combustible ‘eco amigable’, ya que produce un 25% menos de dióxido de carbono (CO2) en comparación con la gasolina y en menos de 150% si se compara con el diésel.\n" +
                "\n" +
                "Los especialistas mencionan que esta energía es mucho más limpia porque además de generar menos emisiones de CO2 por cada kilómetro recorrido, genera mínimas cantidades de azufre, mercurio y otras partículas, lo que la convierte en una energía que ayuda a disminuir el calentamiento global, reducir el efecto invernadero y mejorar la calidad del aire.");
    }

    public static void RENUEVA(Scanner scanner) {
        System.out.println("PRIMAX,compañía líder en el sector energía, presentó las instalaciones remodeladas de su estación de servicio ubicada en el kilómetro 97 de la Panamericana Sur en Asia. El rediseño de esta estación que forma parte de la estrategia de la marca de seguir brindando a sus clientes una experiencia de consumo memorable contempla un ecosistema de servicios en un área de más de 4 mil metros cuadrados que incluye la tienda de conveniencia de la marca, LiSTO! y una renovada zona de recarga de combustible.\n" +
                "\n" +
                "La remodelación de la estación también contempla la implementación de una señalética, un “pole sign” o poste de señal, que facilita la visibilidad de los conductores y peatones. Asimismo, para incentivar la visita a su estación, la marca tiene promociones y beneficios exclusivos para sus clientes que incluyen descuentos en la categoría de comida rápida, cervezas y licores. De igual manera, para el consumo de su combustible premium G-PREMIUM G-PRIX, ofrecen quintuplicar puntos Bonus por cada tanqueada.");

    }


    public static void main(String[] args) {

        System.out.println("===BIENVENIDO AL GRIFO PRIMAX===");
        Scanner sc = new Scanner(System.in);

        opciones();

        sc.close();
    }
}