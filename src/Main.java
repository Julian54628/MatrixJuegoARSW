import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Matrix Escape");
        System.out.print("Numero de agentes: ");
        int agentes = sc.nextInt();

        Tablero tablero = new Tablero(agentes);

        int[] posInicial = tablero.getNeoPosition();
        JugadorNeo neo = new JugadorNeo(tablero, posInicial[0], posInicial[1]);

        while (!tablero.juegoTerminado()) {
            tablero.mostrarTablero();

            System.out.println("Movimientos: 7 8 9");
            System.out.println("              4 6");
            System.out.println("              1 2 3");
            System.out.println("5: Auto  0: Salir");
            System.out.print("Movimiento: ");

            int opcion = sc.nextInt();

            if (opcion == 0) {
                break;
            }

            switch (opcion) {
                case 1: neo.moverManual(1); break;
                case 2: neo.moverManual(2); break;
                case 3: neo.moverManual(3); break;
                case 4: neo.moverManual(4); break;
                case 5: neo.moverHaciaTelefono(); break;
                case 6: neo.moverManual(6); break;
                case 7: neo.moverManual(7); break;
                case 8: neo.moverManual(8); break;
                case 9: neo.moverManual(9); break;
            }

            if (!tablero.juegoTerminado()) {
                tablero.moverAgentes();
            }
        }

        System.out.println(tablero.getMensaje());
        sc.close();
    }
}