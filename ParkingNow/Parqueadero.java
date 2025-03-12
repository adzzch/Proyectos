import java.util.Scanner;

public class Parqueadero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Arrreglos para controlar la disponibilidad de puestos
        boolean[] bajosCC = new boolean[20];  //20 puestos para las motos de baja cc(Falso es = libre)
        boolean[] altosCC = new boolean[10]; //10 Puestos para las motos de alto cc
        String[] horasEntrada = new String[30]; //Almacena la hora en la que entra (índicees 0-19_ bajo)
        
        while (true) { // Menu interactivo
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Registra tu moto");
            System.out.println("2. Valor a pagar");
            System.out.println("3. Salir");
            System.out.print("Elige: ");           
            int opcion = scanner.nextInt();

            if (opcion == 1){
                // Registrar la moto
                //// Sistema de pago de las motos segun su cilindraje
                int tipo;
                do {
                    System.out.print("Tipo (1=Bajo CC / 2=Alto CC): ");
                    tipo = scanner.nextInt();
                    if (tipo < 1 || tipo > 2) {
                        System.out.println("Error: Opcion no valida");
                    }
                } while (tipo < 1 || tipo > 2);

                //Mostrar puestos disponibles
                if (tipo == 1){
                    System.out.println("Puestos Bajos CC [D=Libre, O=Ocupado]: ");
                    for (int i = 0; i < 20; i++){
                        System.out.print((i+1) + (bajosCC[i] ? "O " : "D "));
                        
                    }
                    } else {
                        System.out.println("Puestos Altos CC [D=Libre, O=Ocupado]");
                        for(int i = 0; i <10; i++) {
                            System.out.print((i+1) + (altosCC[i] ? "O " : "D "));
                        }
                    }
                    
                    System.out.print("Elige el puesto que desees: ");
                    int puesto = scanner.nextInt();


                    // Generar una hora aleatoria entre las 07:00 y 22:00
                    int horaEntradaMin = 420 + (int)(Math.random() * 901);
                    String horaEntrada = String.format("%02d:%02d",
                        horaEntradaMin / 60,
                        horaEntradaMin % 60 );
                    System.out.println("Hora entrada generada: " + horaEntrada);

                    // Guardar registro
                    boolean exito = false;
                    if (tipo == 1 && puesto <= 20 && !bajosCC[puesto - 1]) {
                        bajosCC[puesto-1] = true;
                        horasEntrada[puesto-1] = horaEntrada;
                        exito = true;
                    } else if (tipo == 2 && puesto <= 10 && !altosCC[puesto - 1]) {
                        altosCC[puesto - 1] = true;
                        horasEntrada[20 + (puesto-1)] = horaEntrada;
                        exito = true;
                    }

                     System.out.println(exito ? "Éxito" : "Error: Este puesto no existe");
                } else if (opcion == 2) {
                // Cobrar
                System.out.print("Tipo (1=BajoCC / 2=AltoCC): ");
                int tipo = scanner.nextInt();

                // Validar tipo de moto
                if (tipo != 1 && tipo != 2) {
                    System.out.println("Error: Opción no valida.");
                    continue; // Volver al menú principal
                }

                System.out.print("Número de puesto: ");
                int puesto = scanner.nextInt();

                // Validar que el puesto exista
                if ((tipo == 1 && (puesto < 1 || puesto > 20))) {
                    System.out.println("Error: Puesto no válido para motos de baja CC.");
                    continue; // Volver al menú principal
                } else if (tipo == 2 && (puesto < 1 || puesto > 10)) {
                    System.out.println("Error: Puesto no válido para motos de alta CC.");
                    continue;
                }
                    // Obtener hora almacenada (Entrada)

                    int index = (tipo == 1) ? puesto-1 : 20 + (puesto-1);
                    String entrada = horasEntrada[index];
                    if (entrada == null) {
                    System.out.println("Puesto no ocupado");
                    continue;

                }

                //Generar la hora de salida aleatoria, Despues de la entrada
                int entradaMin = convertirHoraAMinutos(entrada);
                int salidaMin = entradaMin + 1 + (int)(Math.random() * (1320 - entradaMin));
                if (salidaMin > 1320) salidaMin = 1320; // Máximo 22:00
                String salida = String.format("%02d:%02d", salidaMin/60, salidaMin%60);
                System.out.println("Hora salida generada: " + salida);

                //Calcular tarifa
                 int duracion = salidaMin - entradaMin;
                int pago = (duracion > 60) ? 2000 : (duracion > 30) ? 800 : 0;
                System.out.println("Total a pagar: $" + pago);

                //Liberar puesto
                if (tipo == 1) bajosCC[puesto-1] = false;
                else altosCC[puesto-1] = false;
                horasEntrada[index] = null;

            } else if (opcion == 3) {
                System.out.println("Hasta luego");
                break;
            } else {
                System.out.println("Opción no válida.");
            }

          
        }
        scanner.close();
    }   
        

    // Convierte "Horas a minutos" a minutos totales
    private static int convertirHoraAMinutos(String hora) {
        String[] partes = hora.split(":");
        return Integer.parseInt(partes[0]) * 60 + Integer.parseInt(partes[1]);
    }

}

