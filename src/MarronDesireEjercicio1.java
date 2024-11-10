import java.util.Scanner;

public class MarronDesireEjercicio1 {
    public static void main(String[] args) {

        // Variables necesarias
        final int MAX_RANDOM = 9;
        final int MIN_RANDOM = 1;
        int cols;
        int rows;
        int[][] board;
        String menuOptions = "[2] Poner bomba \n[1] Mostrar matriz \n[0] Salir";

        Scanner scan = new Scanner(System.in); // Creación scanner

        // Pedir num columnas
        System.out.println("Say something");
        cols = scan.nextInt();

        // Pedir num filas
        System.out.println("Say something");
        rows = scan.nextInt();

        // Crear matriz 2D con los valores introducidos por el usuario
        board = new int[rows][cols];

        // Rellenar matriz 2D con valores random
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Asignar valor random entre 1 y 9 a cada spot del board
                board[i][j] = (int) (Math.random() * (MAX_RANDOM - MIN_RANDOM)) + MIN_RANDOM;
            }
        }

        // Mostrar menú
        System.out.println(menuOptions);

        // Mostrar matriz
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Mostrar spot en columna
                System.out.print(board[i][j] + " ");
            }
            // Mostrar spot en fila
            System.out.println();
        }
    }
}