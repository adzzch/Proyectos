import java.util.Scanner;

public class Parqueadero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Arrreglos para controlar la disponibilidad de puestos
        boolean[] bajoCC = new boolean[20];  //20 puestos para las motos de baja cc(Falso es = libre)
        boolean[] altoCC = new boolean[10]; //10 Puestos para las motos de alto cc
        String[] horaEntrada = new String[30]; //Almacena la hora en la que entra (índicees 0-19_ bajo)
        
        while (true) { // Menu interactivo
            System.out.println("--- MENÚ ---");
            System.out.println("1. Registra tu moto");
            System.out.println("2. Valor a pagar");
            System.out.println("3. Salir");
            System.out.println("Elige: ");
            int opcion = scanner.next();

            if (opcion == 1){
                //Registrar la moto
                ////Sistema de pago de las motos segun su cilindraje

                System.out.println("Tipo (1= Bajo CC / 2=Alto CC): ");
                int tipo = scanner.nextInt();

                //Mostrar puestos disponibles
                if (tipo ==1){
                    System.out.print("Puestos Bajos CC [D=Libre, O=Ocupado]: ");
                    for (int i = 0; i < 20; i++){
                        System.out.print((i+1) + (bajosCC[i] ? "O " : "D "));
                    }
                    } else {
                        System.out.println("Puestos Altos CC [D=Libre, O=Ocupado]");
                        for(int i = 0; i <10; i++) {
                            System.out.print((i+1) + (altosCC[i] ? "O " : "D "));
                        }
                    }

                    System.out.println("Elige el puesto que desees");
                    int puesto = scanner.nextInt();

                    // Generar una hora aleatoria entre las 07:00 y 22:00
                    int horaEntradaMin = 420 + (int)(Math.random() * 901);
                    String horaEntrada = String.format("%02:%02d",
                        horaEntradaMin / 60,
                        horaEntradaMin % 60 );
                    System.out.println("Hora entrada generada: " + horaEntrada);

                    // Guardar registro
                    boolean exito = false;
                    if (tipo == 1 && puesto <= 20 && !bajosCC[puesto - 1]) {
                        bajoCC[puesto-1] = true;
                        horasEntrada[puesto-1] = horaEntrada;
                        exito = true;
                    } else if (tipo == 2 && puesto <= 10 && !altosCC[puesto - 1]) {
                        altosCC[puesto - 1] = true;
                        horasEntrada[20 + (puesto-1)] = horaEntrada;
                        exito = true;
                    }

                }
            }
        }
    }

}

