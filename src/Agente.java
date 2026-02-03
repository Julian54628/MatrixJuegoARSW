import java.util.List;
import java.util.ArrayList;

public class Agente {
    private Tablero tablero;
    private int id;
    private int fila;
    private int columna;

    public Agente(Tablero tablero, int id, int filaInicial, int columnaInicial) {
        this.tablero = tablero;
        this.id = id;
        this.fila = filaInicial;
        this.columna = columnaInicial;
    }

    public int obtenerId() {
        return id;
    }

    public int[] obtenerPosicion() {
        return new int[]{fila, columna};
    }

    public void establecerPosicion(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    private List<int[]> encontrarCaminoANeo() {
        int[] posNeo = tablero.obtenerPosicionNeo();
        return tablero.encontrarCaminoCorto(
                fila, columna, posNeo[0], posNeo[1]);
    }

    public void moverHaciaNeo() {
        List<int[]> camino = encontrarCaminoANeo();

        if (camino.size() > 1) {
            int[] siguientePaso = camino.get(1);
            boolean movido = tablero.moverAgente(id, siguientePaso[0], siguientePaso[1]);
            if (movido) {
                fila = siguientePaso[0];
                columna = siguientePaso[1];
            }
        }
    }
}