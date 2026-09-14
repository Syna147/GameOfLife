import java.util.Scanner;
import java.io.File;

public class Board {
    private Cell[][] cells;
    private int currentGeneration;
    private int userGeneration;

    public Board() {
        cells = new Cell[20][20];

        for(int row = 0; row < 20; row++) {
            for(int col = 0; col < 20; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }

        currentGeneration = 0;
        userGeneration = 0;
    }

    public void setCellAlive(int row, int col, boolean isLiving) {
        cells[row][col].setIsLiving(isLiving);
    }

    public int countNeighbors(int row, int col) {
        int count = 0;

        for(int r = row - 1; r <= row + 1; r++) {
            for(int c = col - 1; c <= col + 1; c++) {

                if(r >= 0 && r < 20 && c >= 0 && c < 20) {

                    if(!(r == row && c == col)) {
                        if(cells[r][c].getIsLiving()) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }

    public void incrementCurrentGeneration() {
        currentGeneration++;
    }

    public int getUserGeneration() {
        return userGeneration;
    }

    public void setUserGeneration(int generation) {
        userGeneration = generation;
    }
    
    public void nextGeneration() {
        int[][] temp = new int[20][20];
        
        for(int row = 0; row < 20; row++) {
            for(int col = 0; col < 20; col++) {
                temp[row][col] = countNeighbors(row, col);
            }
        }
        
        for(int row = 0; row < 20; row++) {
            for(int col = 0; col < 20; col++) {
                
                boolean status = cells[row][col].getIsLiving();
                int numNeighbors = temp[row][col];
                
                if(status) {
                    if(numNeighbors <= 1 || numNeighbors >= 4) {
                        cells[row][col].setIsLiving(false);
                    }
                } else {
                    if(numNeighbors == 3) {
                        cells[row][col].setIsLiving(true);
                    }
                }
            }
        }
        incrementCurrentGeneration();
    }
    
    public int countLivingInRow(int row) {
        int count = 0;

        for(int col = 0; col < 20; col++) {
            if(cells[row][col].getIsLiving()) {
                count++;
            }
        }

        return count;
    }

    public int countLivingInColumn(int col) {
        int count = 0;

        for(int row = 0; row < 20; row++) {
            if(cells[row][col].getIsLiving()) {
                count++;
            }
        }

        return count;
    }

    public int countLivingCells() {
        int count = 0;

        for(int row = 0; row < 20; row++) {
            for(int col = 0; col < 20; col++) {
                if(cells[row][col].getIsLiving()) {
                    count++;
                }
            }
        }

        return count;
    }

    public void displayBoard() {
        System.out.println("Generation: " + currentGeneration);
        System.out.println();
        

        for(int col = 0; col < 20; col++) {
            if(col == 0) {
                System.out.print("   ");
            }
            if(col < 8) {
                System.out.print(" " + (col + 1) + " ");
            } else {
                System.out.print(" " + (col + 1));
            }
        }
        System.out.println();
        
        for(int row = 0; row < 20; row++) {
            if(row < 9) {
                System.out.print(" " + (row + 1) + " ");
            } else {
                System.out.print(" " + (row + 1));
            }
            
            for(int col = 0; col < 20; col++) {
                if(cells[row][col].getIsLiving()) {
                    System.out.print("◾  ");
                }
                else {
                    System.out.print("◽  ");
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.println();
        System.out.println("Total Living Cells: " + countLivingCells());
        System.out.println("Row 10 has " + countLivingInRow(9) + " living cells");
        System.out.println("Column 10 has " + countLivingInColumn(9) + " living cells");
    }
}
