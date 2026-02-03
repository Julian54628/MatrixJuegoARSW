# Matrix Escape Game 

## Descripción del Juego

Matrix Escape es un juego de estrategia por turnos donde controlas a Neo en su intento por escapar de la Matrix. Neo debe evitar a los agentes que lo persiguen mientras busca un teléfono para salir de la simulación.

## Reglas del Juego

### Objetivo
- **Neo **: Debe llegar a cualquiera de los dos teléfonos para escapar
- **Agentes **: Deben capturar a Neo antes de que escape

### Elementos en el Tablero
N = Neo 
A = Agente
T = Teléfono 

= Muro 
. = Espacio vacío

text

### Tablero
- **Tamaño**: 8x8 casillas
- **Teléfonos**: Siempre en esquina superior derecha (0,7) y esquina inferior izquierda (7,0)
- **Muros**: 3 muros colocados aleatoriamente
- **Neo**: Posición inicial aleatoria
- **Agentes**: Cantidad elegida por el jugador, posiciones aleatorias

### Movimientos Permitidos
7 8 9 ↖ ↑ ↗
4 6   ←   →
1 2 3 ↙ ↓ ↘

5 = Movimiento automático (Neo busca teléfono solo)
0 = Salir del juego

text

### Cómo se Juega
1. **Turno del jugador**: Mueves a Neo usando el teclado numérico
2. **Turno de los agentes**: Todos los agentes se mueven automáticamente hacia Neo
3. **Repetir** hasta que Neo escape o sea capturado

### Condiciones de Fin del Juego
-  **GANAS**: Neo llega a un teléfono → `"¡Neo escapó!"`
-  **PIERDES**: Un agente captura a Neo → `"Neo capturado"`
-  **SALIR**: Presionas 0 para abandonar el juego