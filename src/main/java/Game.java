import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
        int height = 0;
        int width = 0;
        char[][] realBoard;
        char[][] playerBoard;
        boolean isInt = false;
        int row;
        int col;
        
    public void start() {
        //Welcome the user, get input from the user to create the game board, 
        //and call the game loop. 
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper.");
        System.out.println("Enter the height of the grid:");
        isInt = false;
        while (isInt == false) {
            try {
                height = sc.nextInt();
                isInt = true;
            } catch (InputMismatchException imex) {
                System.out.println("Please enter a number between 1 and 20.");
                sc.next();
            }
        }
        System.out.println("Enter the width of the grid:");
        isInt = false;
        while (isInt == false) {
            try {
                width = sc.nextInt();
                isInt = true;
            } catch (InputMismatchException imex) {
                System.out.println("Please enter a number between 1 and 20.");
                sc.next();
            }    
        }
        Grid board = new Grid(height, width);
        int boardRows = board.getRows();
        int boardCols = board.getCols();
        
        //The real board that holds the position of the mines.
        realBoard = new char[boardRows][boardCols];
        
        //The board that the player sees as they play the game.
        playerBoard = new char[boardRows][boardCols];
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard[i].length; j++) {
                playerBoard[i][j] = 'X';
            }
        }
        board.randomFillGrid(realBoard);
        gameLoop(board, realBoard, playerBoard, boardCols);
    }    
    
    //This loop allows the player to keep picking cells until 
    //they win or lose the game.
    private void gameLoop(Grid board, char[][] realBoard, char[][] playerBoard, 
            int boardCols) {
        String keepGoing = "yes";
        while (keepGoing == "yes") {
            Scanner sc = new Scanner(System.in);
            board.displayGrid(playerBoard, boardCols);
            System.out.println("Pick a spot to check for a mine by entering a"
                    + " row number followed by a column number.");
            System.out.println("Row number:");
            isInt = false;
            while (isInt == false || row < 0 || row > height) {
                try {
                    row = sc.nextInt() - 1;
                    isInt = true;
                } catch (InputMismatchException imex){
                    System.out.println("Please enter a number no greater than "
                    + "the numbers of rows.");
                    sc.next();
                }
            }
            System.out.println("Column number:");
            isInt = false;
            while (isInt == false || col < 0 || col > width) {
                try {
                    col = sc.nextInt() - 1;
                    isInt = true;
                } catch (InputMismatchException imex) {
                    System.out.println("Please enter a number no greater than"
                            + "the number of columns.");
                    sc.next();
                }
            }
            checkLocation(row, col);
            if (checkLocation(row, col) == true) {
                keepGoing = "no";
            }     
        } 
        board.displayGridData(realBoard, boardCols);
    }
    
    //Checks the cell the player searched to see if a mine is present.
    private boolean checkLocation(int row, int col) {
        if (realBoard[row][col] == '*'){
            return true;
        }
        else {
            numberOfMines(row, col);
            checkForWin();
            return false;
        }    
    }
    
    //Checks the spots adjacent to the cell selected by the player for mines.
    public void numberOfMines(int row, int col) {
        int mineNumber = 0;
        if (gridCage(row -1, col -1) == true && realBoard[row -1][col -1] == '*') {
            mineNumber ++;
        }
        if (gridCage(row -1, col) == true && realBoard[row -1][col] == '*') {
            mineNumber ++;
        }       
        if (gridCage(row -1, col +1) == true && realBoard[row -1][col +1] == '*') {
            mineNumber ++;
        }
        if (gridCage(row, col -1) == true && realBoard[row][col -1] == '*') {
            mineNumber ++;
        }
        if (gridCage(row, col +1) == true && realBoard[row][col +1] == '*') {
            mineNumber ++;
        }
        if (gridCage(row +1, col -1) == true && realBoard[row +1][col -1] == '*') {
            mineNumber ++;
        }
        if (gridCage(row +1, col) == true && realBoard[row +1][col] == '*') {
            mineNumber ++;
        }
        if (gridCage(row +1, col +1) == true && realBoard[row +1][col +1] == '*') {
            mineNumber ++;
        }    
        switch (mineNumber) {
            case 1:
                playerBoard[row][col] = '1';
                //mineNumber = 0;
                break;
            case 2:
                playerBoard[row][col] = '2';   
                //mineNumber = 0;
                break;
            case 3:
                playerBoard[row][col] = '3';
                //mineNumber = 0;
                break;
            case 4:
                playerBoard[row][col] = '4';
                //mineNumber = 0;
                break;
            case 5:
                playerBoard[row][col] = '5';
                //mineNumber = 0;
                break;
            case 6:
                playerBoard[row][col] = '6';
                //mineNumber = 0;
                break;
            case 7:
                playerBoard[row][col] = '7';
                //mineNumber = 0;
                break;
            case 8:
                playerBoard[row][col] = '8';
                //mineNumber = 0;
                break;
            default:
                clearBlanks(row, col);
                break;
            }
    }
    
    //NEEDS MORE WORK
    public void clearBlanks(int row, int col) {
        if (gridCage(row, col) == true && playerBoard[row][col] == 'X') {    
            playerBoard[row][col] = ' ';
            if (gridCage(row-1, col-1) == true) {
                numberOfMines(row-1, col-1);
            }
            if (gridCage(row-1, col) == true) {
                numberOfMines(row-1, col);
            }
            if (gridCage(row-1, col+1) == true) {
                numberOfMines(row-1, col+1);
            }
            if (gridCage(row, col-1) == true) {
                numberOfMines(row, col-1);
            }
            if (gridCage(row, col+1) == true) {
                numberOfMines(row, col+1);
            }
            if (gridCage(row+1, col-1) == true) {
                numberOfMines(row+1, col-1);
            }
            if (gridCage(row+1, col) == true) {
                numberOfMines(row+1, col);
            }
            if (gridCage(row+1, col+1) == true) {
                numberOfMines(row+1, col+1);
            }
        }
    }    
    
    public boolean gridCage(int row, int col) {
        if (row < 0 || col < 0) return false;
        if (row >= height || col >= width) return false;
        return true;
    }
    
    public void checkForWin() {
        int win = 0;
        
        for (int i = 0; i < playerBoard.length; i++) {
                for (int j = 0; j < playerBoard[i].length; j++) {
                    if (playerBoard[i][j] == 'X') {
                        win ++;
                    }
                }
        }
        if (win == 10) {
            System.out.println("Congratulations! You win!");
        }
    }
}