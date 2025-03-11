import java.util.Scanner;

public class Parqueadero {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Arrreglos para controlar la disponibilidad de puestos
        boolean[] bajoCC = new boolean[20];  //20 puestos para las motos de baja cc(Falso es = libre)
        boolean[] altosCC = new boolean[10]; //10 Puestos para las motos de alto cc
        String[] horasEntrada = new String[30]; //Almacena la hora en la que entra (índicees 0-19_ bajo)
        
        while (true) {
            System.out.println("--- MENÚ ---");
            System.out.println("1. Registra tu moto");
        }
    }

}

