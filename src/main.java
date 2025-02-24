import java.util.*;

class IntWrapper {
    public int value;
    public IntWrapper(int value){
        this.value = value;
    }
}

class CharWrapper {
    public char value;
    public CharWrapper(char value){
        this.value = value;
    }
}

public class main {
    public static char[][] copyGrid(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
    public static void main(String[] args) {
        Scanner scans = new Scanner(System.in);
        System.out.println("Masukkan nama file: ");
        String filename = scans.nextLine();
        IntWrapper M = new IntWrapper(0);
        IntWrapper N = new IntWrapper(0);
        IntWrapper P = new IntWrapper(0);
        String S = "";
        scans.close();
        List<blocks> bList = new ArrayList<>();
        scan.read_line(filename, M, N, P, S, bList);
        map map = new map(M.value, N.value);
        Stack<map> mapStack = new Stack<>();
        Stack<int[]> state = new Stack<>();
        int currentBlockIDX = 0;
        mapStack.push(new map(M.value, N.value));
        mapStack.peek().grid = copyGrid(map.grid);
        state.push(new int[]{currentBlockIDX, 0, 0, 0, 0});
        boolean solveFlag = false;
        int iter = 0;
        int startRow = 0;
        int startCol = 0;
        long start = System.nanoTime();
        while (currentBlockIDX < bList.size()){
            blocks currentBlock = bList.get(currentBlockIDX);
            boolean succeed = false;
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 2; j++){
                    for (int k = startRow; k < map.grid.length; k++){
                        for (int l = startCol; l < map.grid[0].length; l++){
                            iter++;
                            currentBlock.lastPos = new int[]{k, l};
                            if (map.placeBlock(currentBlock)){
                                mapStack.push(new map(map.grid.length, map.grid[0].length));
                                mapStack.peek().grid = copyGrid(map.grid);
                                state.push(new int[]{currentBlockIDX, i, j, k, l});
                                succeed = true;
                                startRow = 0;
                                startCol = 0;
                                currentBlockIDX++;
                                break;
                            }
                        } if (succeed){break;}
                    }if (succeed){break;}
                    currentBlock.mirrorBlock();
                }if (succeed){break;}
                currentBlock.mirrorBlock();
                currentBlock.rotateBlock();
            }
            currentBlock.rotateBlock();
            if (!succeed){
                if (mapStack.size() == 1){
                    System.out.println("The Puzzle has No Solution");
                    long end = System.nanoTime();
                    System.out.println("Time Elapsed: " + (end-start)/1000000 + "ms");
                    System.out.println("Iteration: " + iter);
                    break;
                } else {
                    map last = mapStack.pop();
                    last.printMap();
                    System.out.println("CHARACTER: " + currentBlock.character);
                    int[] lastState = state.pop();
                    map.grid = copyGrid(mapStack.peek().grid);
                    currentBlockIDX = lastState[0];
                    currentBlock = bList.get(currentBlockIDX);
                    int rot = lastState[1];
                    for (int i = 0; i < rot; i++){
                        currentBlock.rotateBlock();
                    }
                    int mir = lastState[2];
                    for (int i = 0; i < mir; i++){
                        currentBlock.mirrorBlock();
                    }
                    int[] location = new int[]{lastState[3], lastState[4]};
                    startRow = location[0];
                    startCol = location[1] + 1;
                    if (startCol >= map.grid[0].length) {
                        startCol = 0;
                        startRow++;
                    }
                }
            }
        }
        if (currentBlockIDX == bList.size()){
            solveFlag = true;
        }
        if (solveFlag){
            long end = System.nanoTime();
            System.out.println("The Puzzle has been solved");
            map = mapStack.pop();
            map.printMap();
            System.out.println();
            System.out.println("Time Elapsed: " + (end-start)/1000000 + "ms");
            System.out.println("Iteration: " + iter);
        }
    }
}
