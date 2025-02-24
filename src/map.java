import java.util.HashMap;
import java.util.Map;

public class map {
    char[][] grid;
    char[][] nextGrid;
    public map(int row, int col){
        this.grid = new char[row][col];
        this.nextGrid = new char[row][col];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                this.grid[i][j] = 'x';
                this.nextGrid[i][j] = 'x';
            }
        }
    }
    public void resetGrid(){
        for (int i = 0; i < this.grid.length; i++){
            for (int j = 0; j < this.grid[0].length; j++){
                this.grid[i][j] = 'x';
            }
        }
    }
    public boolean placeBlock(blocks block){
        if (block.lastPos[0]+block.block.length > this.grid.length || block.lastPos[1]+block.block[0].length > this.grid[0].length){
            return false;
        }
        for (int i = 0; i < block.block.length; i++){
            for (int j = 0; j < block.block[0].length; j++){
                if (this.grid[block.lastPos[0]+i][block.lastPos[1]+j] != 'x'){
                    if (block.block[i][j] == 1){
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < block.block.length; i++){
            for (int j = 0; j < block.block[0].length; j++){
                if (block.block[i][j] == 1){
                    this.grid[block.lastPos[0]+i][block.lastPos[1]+j] = block.character;
                }
            }
        }
        return true;
    }

    private static final Map<Character, String> colorMap = new HashMap<>();
    private static final String RESET = "\u001B[0m";

    static {
        String[] colors ={
            "\u001B[31m",
            "\u001B[32m",
            "\u001B[33m",
            "\u001B[34m",
            "\u001B[35m",
            "\u001B[36m",
            "\u001B[37m",
            "\u001B[91m",
            "\u001B[92m",
            "\u001B[93m",
            "\u001B[94m",
            "\u001B[95m",
            "\u001B[96m",
            "\u001B[97m" 
        };
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            colorMap.put(letter, colors[(letter - 'A') % colors.length]);
        }
    }
    public void printMap(){
        for (int i = 0; i < this.grid.length; i++){
            for (int j = 0; j < this.grid[0].length; j++){
                if (this.grid[i][j] == 'x'){
                    System.out.print("x");
                } else {
                    String color = colorMap.getOrDefault(this.grid[i][j], RESET);
                    System.out.print(color + this.grid[i][j] + RESET);
                }
            }
            System.out.println();
        }
    }
}



