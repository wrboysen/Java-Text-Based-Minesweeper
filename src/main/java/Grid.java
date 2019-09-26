import java.util.Random;

public class Grid {
    public int rows;
    public int cols;
    //Constructor with height and width parameters.
    public Grid(int height, int width) {
    rows = height;
    cols = width;
    }
    public int getRows() {
        return rows;
    }    
    public int getCols() {
        return cols;
    }
    
    //Randomly places 10 mines on the real board.
    public void randomFillGrid(char[][] realBoard) {
        int mines = 0;
        Random random = new Random();
        
        while(mines < 10) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            if (realBoard[x][y] != '*') {
                realBoard[x][y] = '*';
                mines ++;
            }
        }
        for (int i = 0; i < realBoard.length; i++) {
            for (int j = 0; j < realBoard[i].length; j++) {
                if (realBoard[i][j] != '*') {
                    realBoard[i][j] = ' ';
                }    
                            
            }
        }    
    }
    
    //Displays the player board.
    public void displayGrid(char[][] playerBoard, int boardCols) {
        System.out.println();
        System.out.println("There are a total of 10 mines in the mine field.");
        System.out.println();
        System.out.print("    ");
        for (int columns = 0; columns < boardCols; columns++) {
            System.out.print((columns + 1) + " ");
        }
        System.out.print("\n");
        System.out.println();
        for (int i = 0; i < playerBoard.length; i++) {
            if (i <= 8) {
            System.out.print((i+1) + "   ");
            }
            else {
            System.out.print((i+1) + "  ");
            }
            for (int j = 0; j < playerBoard[i].length; j++) {
                System.out.print(playerBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //Displays the real board.
    public void displayGridData(char[][] realBoard, int boardCols) {
        System.out.println();
        System.out.println("Game Over!");
        System.out.println();
        System.out.print("    ");
        for (int columns = 0; columns < boardCols; columns++) {
            System.out.print((columns + 1) + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < realBoard.length; i++) {
            if (i <= 8) {
            System.out.print((i+1) + "   ");
            }
            else {
            System.out.print((i+1) + "  ");
            }
            for (int j = 0; j < realBoard[i].length; j++) {
                System.out.print(realBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}    
        
    
   
