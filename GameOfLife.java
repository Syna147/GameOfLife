import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GameofLife {

    private Board grid;
    private String fileName;
    private int numGenerations;

    public GameofLife() {
        grid = new Board();
        fileName = "life100.txt";
        numGenerations = 0;
    }

    public void getUserInput() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the file name:");
        fileName = input.nextLine();

        System.out.println("Enter a value for the number of generations:");
        numGenerations = input.nextInt();

        grid.setUserGeneration(numGenerations);
    }

    public void readDataFromFile() {
        try {
            Scanner fileReader = new Scanner(new File(fileName));

            while(fileReader.hasNextInt()) {
                int row = fileReader.nextInt();
                int col = fileReader.nextInt();

                row--;
                col--;

                grid.setCellAlive(row, col, true);
            }

            fileReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Could not find " + fileName);
        }
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void runGame() {
        readDataFromFile();
        clearConsole();
        grid.displayBoard();
        
        try {
    
            Thread.sleep(400); 
        } catch (InterruptedException e) {
    
            Thread.currentThread().interrupt();
        }
        
        for(int i = 0; i < numGenerations; i++) {
            grid.nextGeneration();
            clearConsole();
            grid.displayBoard();
           
            try {
                Thread.sleep(400); 
            } catch (InterruptedException e) {
               
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        GameofLife game = new GameofLife();
        game.getUserInput();
        game.runGame();
    }
}

