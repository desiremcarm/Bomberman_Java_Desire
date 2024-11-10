import java.util.Scanner;

public class MarronDesireEjercicio1 {
    public static void main(String[] args) {

        // Variables necesarias
        final int MAX_RANDOM = 9;
        final int MIN_RANDOM = 1;
        int cols;
        int rows;
        int[][] board;
        int[] bombSpot = new int[2];
        String menuOptions = "[2] Poner bomba \n[1] Mostrar matriz \n[3] Ver ranking \n[0] Salir";
        int menuOption = -1;
        boolean showMenu = true;
        boolean bombX = false;
        boolean bombY = false;
        int score = 0;

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

        // Mostrar menu
        while(showMenu){
            System.out.println(menuOptions);

            // Reset coordenadas de las bombas
            if(bombX && bombY){
                bombX = false;
                bombY = false;
            }

            if(!scan.hasNextInt()){
                scan.next();
            } else {
                menuOption = scan.nextInt();
                showMenu = false;
            }

            switch (menuOption){
                case 1:
                    System.out.println("It was 1");

                    // Mostrar matriz
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            // Mostrar spot en columna
                            System.out.print(board[i][j] + " ");
                        }
                        // Mostrar spot en fila
                        System.out.println();
                    }

                    showMenu = true;

                    break;

                case 2:
                    System.out.println("It was 2");
                    int coord;
                    int bombScore = 0;

                    while(!bombX){
                        System.out.println("Row");
                        if(!scan.hasNextInt()){
                            scan.next();
                        } else {
                            coord = scan.nextInt();
                            bombSpot[0] = coord;
                            bombX = true;
                        }
                    }

                    while(!bombY){
                        System.out.println("Col");
                        if(!scan.hasNextInt()){
                            scan.next();
                        } else {
                            coord = scan.nextInt();
                            bombSpot[1] = coord;
                            bombY = true;
                        }
                    }

                    System.out.println("Coord de bombas: " + bombSpot[0] + " " + bombSpot[1]);

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

                    score += bombScore;

                    System.out.println(bombScore);
                    
                    showMenu = true;

                    break;
                case 3:
                    System.out.println("Ranking actual: " + score);

                    showMenu = true;

                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Mal");
                    break;
            }
        }
    }
}