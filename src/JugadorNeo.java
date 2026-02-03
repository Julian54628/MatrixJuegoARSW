import java.util.List;
import java.util.ArrayList;

public class JugadorNeo {
    private Tablero tablero;
    private int fila;
    private int columna;

    public JugadorNeo(Tablero tablero, int filaInicial, int columnaInicial) {
        this.tablero = tablero;
        this.fila = filaInicial;
        this.columna = columnaInicial;
    }

    public int[] obtenerPosicion() {
        return new int[]{fila, columna};
    }

    private List<int[]> encontrarTelefonoCercano() {
        List<int[]> telefonos = tablero.obtenerTelefonos();
        List<int[]> caminoMasCorto = null;
        int distanciaMinima = Integer.MAX_VALUE;

        for (int[] telefono : telefonos) {
            List<int[]> camino = tablero.encontrarCaminoCorto(
                    fila, columna, telefono[0], telefono[1]);

            if (!camino.isEmpty() && camino.size() < distanciaMinima) {
                distanciaMinima = camino.size();
                caminoMasCorto = camino;
            }
        }

        return caminoMasCorto != null ? caminoMasCorto : new ArrayList<>();
    }

    public boolean moverHaciaTelefono() {
        List<int[]> camino = encontrarTelefonoCercano();

        if (camino.size() > 1) {
            int[] siguientePaso = camino.get(1);
            boolean movido = tablero.moverNeo(siguientePaso[0], siguientePaso[1]);
            if (movido) {
                fila = siguientePaso[0];
                columna = siguientePaso[1];
            }
            return movido;
        }
        return false;
    }

    public boolean moverManual(int tecla) {
        int nuevaFila = fila;
        int nuevaColumna = columna;

        switch (tecla) {
            case 1: nuevaFila++; nuevaColumna--; break;
            case 2: nuevaFila++; break;
            case 3: nuevaFila++; nuevaColumna++; break;
            case 4: nuevaColumna--; break;
            case 6: nuevaColumna++; break;
            case 7: nuevaFila--; nuevaColumna--; break;
            case 8: nuevaFila--; break;
            case 9: nuevaFila--; nuevaColumna++; break;
            default: return false;
        }

        boolean movido = tablero.moverNeo(nuevaFila, nuevaColumna);
        if (movido) {
            fila = nuevaFila;
            columna = nuevaColumna;
        }
        return movido;
    }
}