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
        int boardTotalValue = 0;

        Scanner scan = new Scanner(System.in); // Creación scanner

        // Pedir num columnas
        System.out.println("How many rows does your board have?");
        cols = scan.nextInt();

        // Pedir num filas
        System.out.println("How many columns does your board have?");
        rows = scan.nextInt();

        // Enseñar al user sus elecciones
        System.out.println("Your board has " + rows + " rows and " + cols + " columns.");

        // Crear matriz 2D con los valores introducidos por el usuario
        board = new int[rows][cols];

        // Rellenar matriz 2D con valores random
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Asignar valor random entre 1 y 9 a cada spot del board
                board[i][j] = (int) (Math.random() * (MAX_RANDOM - MIN_RANDOM)) + MIN_RANDOM;
                boardTotalValue += board[i][j];
            }
        }

        System.out.println("Your board total value is: " + boardTotalValue);

        // Mostrar menu
        while(showMenu){
            System.out.println(menuOptions);

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
                    System.out.println("You've chosen: 'Show board'");

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
                    System.out.println("You've chosen: 'Plant bomb'");
                    int coord = 0;
                    int bombScore = 0;

                    System.out.println("Select a row to plant the bomb. Use a number below or equal to " + rows);

                    while(!bombX){

                        if(!scan.hasNextInt()){
                            scan.next();
                        } else {
                            coord = scan.nextInt();
                            while(coord > rows || coord <= 0){
                                System.out.println("That row doesn't exist, try again");
                                if(!scan.hasNextInt()){
                                    scan.next();
                                } else {
                                    coord = scan.nextInt();
                                }
                            }
                            System.out.println("The row is: " + coord);
                            bombSpot[0] = coord;
                            bombX = true;
                        }

                    }

                    System.out.println("Select a column to plant the bomb. Use a number below or equal to " + cols);

                    while(!bombY){

                        if(!scan.hasNextInt()){
                            scan.next();
                        } else {
                            coord = scan.nextInt();
                            while(coord > cols || coord <= 0){
                                System.out.println("That column doesn't exist, try again");
                                if(!scan.hasNextInt()){
                                    scan.next();
                                } else {
                                    coord = scan.nextInt();
                                }
                            }
                            System.out.println("The column is: " + coord);
                            bombSpot[1] = coord;
                            bombY = true;
                        }

                    }

                    System.out.println("Your chosen coordinates: Row " + bombSpot[0] + ", Column " + bombSpot[1]);

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

                    System.out.println("Your score for this move: " + bombScore);

                    if(bombScore >= boardTotalValue){
                        System.out.println("You finished the game! Your total score: " + score);
                    } else {
                        showMenu = true;
                    }

                    break;
                case 3:
                    System.out.println("Current ranking: " + score);

                    showMenu = true;

                    break;
                case 0:
                    System.out.println("You've chosen to close the game. Bye!");
                    break;
                default:
                    System.out.println("That option is not valid. Try again.");
                    showMenu = true;
                    break;
            }
        }
    }
}