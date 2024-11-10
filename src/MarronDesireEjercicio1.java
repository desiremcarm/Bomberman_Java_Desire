// By Desiré Marrón Carmona

import java.util.Scanner;

public class MarronDesireEjercicio1 {
    public static void main(String[] args) {

        // Variables

        // CONST RANDOM
        final int MAX_RANDOM = 9;
        final int MIN_RANDOM = 1;

        // CONST COLORS
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        // MENU RELATED
        String menuOptions = "[2] 💣 Poner bomba \n[1] ♟️ Mostrar matriz \n[3] 📊 Ver ranking \n[0] 👋 Salir";
        int menuOption;

        // BOARD RELATED
        int cols;
        int rows;
        int[][] board;
        int boardTotalValue = 0;

        // X Y COORDINATES
        int[] bombSpot = new int[2];

        // MAIN FLAG TO CONTROL THE EXECUTION OF THE PROGRAM
        boolean game = true;

        // GLOBAL SCORE FOR EACH MATCH
        int score = 0;

        Scanner scan = new Scanner(System.in); // Creación scanner

        // Pedir num filas
        System.out.print(ANSI_BLUE + "How many rows does your board have? ");

        // Comprobar que 1. Introducen un número entero y 2. No introducen un caracter
        do {
            System.out.print(ANSI_RED + "⚠️ Please, use a positive number\n");
            while(!scan.hasNextInt()){
                System.out.println(ANSI_RED + "⚠️ That value is not valid. Try again");
                scan.next();
            }
            rows = scan.nextInt();
        }while(rows <= 0);

        // Pedir num columnas
        System.out.println(ANSI_BLUE + "How many columns does your board have?");

        // Comprobar que 1. Introducen un número entero y 2. No introducen un caracter
        do {
            System.out.print(ANSI_RED + "⚠️ Please, use a positive number\n");
            while(!scan.hasNextInt()){
                System.out.println(ANSI_RED + "⚠️ That value is not valid. Try again");
                scan.next();
            }
            cols = scan.nextInt();
        }while(cols <= 0);

        // Enseñar al user sus elecciones de creación de tablero
        System.out.println(ANSI_BLUE + "⭐ Your board has " + rows + " rows and " + cols + " columns.");

        // Crear matriz 2D con los valores introducidos por el usuario
        board = new int[rows][cols];

        // Rellenar matriz 2D con valores random
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Asignar valor random entre 1 y 9 (ambos incluidos) a cada spot del board
                board[i][j] = (int) (Math.random() * ((MAX_RANDOM + 1) - MIN_RANDOM)) + MIN_RANDOM;
                // Calcular el valor total del tablero para saber cuándo el user ha ganado
                boardTotalValue += board[i][j];
            }
        }

        // Indicar al user el valor total de su tablero
        System.out.println("🏆 Your board total value is: " + boardTotalValue);

        // Mostrar menu
        while(game){
            System.out.println(ANSI_BLUE + "\n" + menuOptions);

            // Comprobar que la elección del user es un número positivo
            do {
                System.out.print(ANSI_RED + "Type in your choice. ⚠️ Please, use a positive number\n");
                while(!scan.hasNextInt()){
                    System.out.println(ANSI_RED + "⚠️ That value is not valid. Choose a number between 0, 1, 2 and 3:");
                    scan.next();
                }
                menuOption = scan.nextInt();
            }while(menuOption >= 4);

            // Casos para cada opción del menú
            switch (menuOption){
                // Mostrar tablero
                case 1:
                    System.out.println(ANSI_PURPLE + "\n1️⃣ You've chosen: 'Show board'");

                    // Mostrar matriz
                    for (int i = 0; i < rows; i++) {
                        System.out.print("[ ");
                        for (int j = 0; j < cols; j++) {
                            // Mostrar spot en columna y decoraciones
                            if(j + 1 == cols){
                                System.out.print(ANSI_WHITE + board[i][j] + " ");
                            } else {
                                System.out.print(ANSI_WHITE + board[i][j]);
                                System.out.print(ANSI_PURPLE + " | ");
                            }
                        }
                        // Mostrar spot en fila y decoraciones
                        System.out.print(ANSI_PURPLE + "]");
                        System.out.println();
                    }

                    break;
                // Plantar bomba
                case 2:
                    System.out.println(ANSI_PURPLE + "\n2️⃣ You've chosen: 'Plant bomb'");
                    int coord;
                    int bombScore = 0;

                    System.out.println(ANSI_BLUE + "🎯 Select a row to plant the bomb.");

                    // Pedir la posición X de la bomba (la fila)
                    do {
                        System.out.print(ANSI_RED + "⚠️ Use a number below or equal to " + rows + "\n");
                        while(!scan.hasNextInt()){
                            System.out.println(ANSI_RED + "⚠️ That value is not valid. Try again");
                            scan.next();
                        }
                        coord = scan.nextInt();
                        bombSpot[0] = coord;
                    }while(coord <= 0 || coord > rows);

                    System.out.println(ANSI_BLUE + "🎯 Select a column to plant the bomb. Use a number below or equal to " + cols);

                    // Pedir la posición Y de la bomba (la columna)
                    do {
                        System.out.print(ANSI_RED + "⚠️ Use a number below or equal to " + cols + "\n");
                        while(!scan.hasNextInt()){
                            System.out.println(ANSI_RED + "⚠️ That value is not valid. Try again");
                            scan.next();
                        }
                        coord = scan.nextInt();
                        bombSpot[1] = coord;
                    }while(coord <= 0 || coord > cols);

                    System.out.println(ANSI_BLUE + "🎯 Your chosen coordinates: Row " + bombSpot[0] + ", Column " + bombSpot[1]);

                    // Calcular el score de la jugada y actualizar los números del tablero
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            if(j == bombSpot[1] - 1){
                                bombScore += board[i][j];
                                board[i][j] = 0;
                            }
                            if(i == bombSpot[0] - 1){
                                bombScore += board[i][j];
                                board[i][j] = 0;
                            }
                        }
                    }

                    // Guardar el score de la jugada al score global
                    score += bombScore;

                    System.out.println("🏆 Your score for this move: " + bombScore);

                    // Comprobar si con esta jugada has terminado el juego
                    if(score >= boardTotalValue){
                        System.out.println(ANSI_PURPLE + "👏 You finished the game! Your total score: " + score + ". Thanks for playing! 💖");
                        game = false;
                    }

                    break;
                // Consultar ranking global de la partida
                case 3:
                    System.out.println(ANSI_PURPLE + "\n3️⃣ Current ranking: " + score);
                    break;
                // Salir del juego y cerrar el progarma
                case 0:
                    System.out.println(ANSI_PURPLE + "\n0️⃣ You've chosen to close the game. Bye!");
                    game = false;
                    break;
                // Manejar opción incorrecta del usuario
                default:
                    System.out.println(ANSI_RED + "That option is not valid. Try again.");
                    break;
            }
        }
    }
}