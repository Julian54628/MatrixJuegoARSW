import java.util.*;

public class Tablero {
    private char[][] matriz;
    private int filaNeo, colNeo;
    private List<int[]> telefonos;
    private List<int[]> muros;
    private List<Agente> agentes;
    private boolean terminado;
    private String mensaje;

    public Tablero(int numAgentes) {
        matriz = new char[8][8];
        telefonos = new ArrayList<>();
        muros = new ArrayList<>();
        agentes = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matriz[i][j] = '.';
            }
        }

        Random r = new Random();

        telefonos.add(new int[]{0, 7});
        telefonos.add(new int[]{7, 0});
        matriz[0][7] = 'T';
        matriz[7][0] = 'T';

        for (int i = 0; i < 3; i++) {
            int f, c;
            do {
                f = r.nextInt(8);
                c = r.nextInt(8);
            } while (matriz[f][c] != '.');
            matriz[f][c] = '#';
            muros.add(new int[]{f, c});
        }

        do {
            filaNeo = r.nextInt(8);
            colNeo = r.nextInt(8);
        } while (matriz[filaNeo][colNeo] != '.');
        matriz[filaNeo][colNeo] = 'N';

        for (int i = 0; i < numAgentes; i++) {
            int f, c;
            do {
                f = r.nextInt(8);
                c = r.nextInt(8);
            } while (matriz[f][c] != '.');
            Agente a = new Agente(this, i, f, c);
            agentes.add(a);
            matriz[f][c] = 'A';
        }
    }

    public void mostrarTablero() {
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean moverNeo(int f, int c) {
        if (terminado) return false;

        if (f < 0 || f >= 8 || c < 0 || c >= 8) return false;
        if (matriz[f][c] == '#') return false;

        if (matriz[f][c] == 'A') {
            terminado = true;
            mensaje = "Neo capturado";
            return false;
        }

        if (matriz[f][c] == 'T') {
            matriz[filaNeo][colNeo] = '.';
            filaNeo = f;
            colNeo = c;
            matriz[filaNeo][colNeo] = 'N';
            terminado = true;
            mensaje = "Neo escapo";
            return true;
        }

        matriz[filaNeo][colNeo] = '.';
        filaNeo = f;
        colNeo = c;
        matriz[filaNeo][colNeo] = 'N';
        return true;
    }

    public void moverAgentes() {
        if (terminado) return;

        for (Agente a : agentes) {
            a.moverHaciaNeo();
        }
    }

    public boolean moverAgente(int id, int f, int c) {
        if (terminado) return false;

        if (f < 0 || f >= 8 || c < 0 || c >= 8) return false;
        if (matriz[f][c] == '#') return false;

        if (matriz[f][c] == 'N') {
            terminado = true;
            mensaje = "Agente " + id + " atrapo a Neo";
            return true;
        }

        for (Agente a : agentes) {
            if (a.obtenerId() == id) {
                int[] pos = a.obtenerPosicion();
                matriz[pos[0]][pos[1]] = '.';
                a.establecerPosicion(f, c);
                matriz[f][c] = 'A';
                return true;
            }
        }
        return false;
    }

    public List<int[]> encontrarCaminoCorto(int inicioF, int inicioC, int finF, int finC) {
        boolean[][] visto = new boolean[8][8];
        int[][] padreF = new int[8][8];
        int[][] padreC = new int[8][8];

        for (int i = 0; i < 8; i++) {
            Arrays.fill(padreF[i], -1);
            Arrays.fill(padreC[i], -1);
        }

        Queue<int[]> cola = new LinkedList<>();
        cola.add(new int[]{inicioF, inicioC});
        visto[inicioF][inicioC] = true;

        int[][] dirs = {
                {-1,-1},{-1,0},{-1,1},
                {0,-1},{0,1},
                {1,-1},{1,0},{1,1}
        };

        while (!cola.isEmpty()) {
            int[] actual = cola.poll();
            int f = actual[0];
            int c = actual[1];

            if (f == finF && c == finC) {
                List<int[]> camino = new ArrayList<>();
                int rf = f, rc = c;

                while (rf != -1 && rc != -1) {
                    camino.add(0, new int[]{rf, rc});
                    int tempf = padreF[rf][rc];
                    int tempc = padreC[rf][rc];
                    rf = tempf;
                    rc = tempc;
                }

                return camino;
            }

            for (int[] d : dirs) {
                int nf = f + d[0];
                int nc = c + d[1];

                if (nf >= 0 && nf < 8 && nc >= 0 && nc < 8 &&
                        !visto[nf][nc] && matriz[nf][nc] != '#') {
                    visto[nf][nc] = true;
                    padreF[nf][nc] = f;
                    padreC[nf][nc] = c;
                    cola.add(new int[]{nf, nc});
                }
            }
        }

        return new ArrayList<>();
    }

    public int[] obtenerPosicionNeo() {
        return new int[]{filaNeo, colNeo};
    }

    public List<int[]> obtenerTelefonos() {
        return telefonos;
    }

    public int[] getNeoPosition() {
        return obtenerPosicionNeo();
    }

    public List<int[]> getTelefonos() {
        return telefonos;
    }

    public boolean juegoTerminado() {
        return terminado;
    }

    public String getMensaje() {
        return mensaje;
    }
}