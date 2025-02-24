import java.io.*;
import java.util.*;

public class scan {
    public static void read_line(String filename, IntWrapper M, IntWrapper N, IntWrapper P, String S, List<blocks> bList){
        try {
            File file = new File("test", filename);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] Arr = line.split(" ");
            M.value = Integer.parseInt(Arr[0]);
            N.value = Integer.parseInt(Arr[1]);
            P.value = Integer.parseInt(Arr[2]);
            line = scanner.nextLine();
            S = line;
            int[][] Matrix;
            char Current = 'A';
            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                lines.add(line);
            }
            scanner.close();
            int curline = 0;
            int copyP = P.value;
            boolean flag = false;
            List<Integer> width = new ArrayList<>();
            List<String> block = new ArrayList<>();
            while (copyP > 0){
                if (copyP != 0 && curline == lines.size()){
                    System.out.println("Not Enough Blocks");
                    break;
                }
                block.clear();
                width.clear();
                while (true) { 
                    line = lines.get(curline);
                    Arr = line.split("");
                    for (int i = 0; i < Arr.length; i++){
                        if (line.charAt(i) != Current && line.charAt(i) != ' '){
                            flag = true;
                            break;
                        }
                    }
                    if (flag){
                        flag = false;
                        break;
                    }
                    if (curline == lines.size()-1){
                        block.add(line);
                        width.add(Arr.length);
                        break;
                    }
                    block.add(line);
                    width.add(Arr.length);
                    curline++;
                }
                int maxW = Collections.max(width);
                Matrix = new int[block.size()][maxW];
                for (int i = 0; i < block.size(); i++){
                    Arr = block.get(i).split("");
                    for (int j = 0; j < maxW; j++){
                        if (j >= Arr.length){
                            Matrix[i][j] = 0;
                        } else if (Arr[j].equals(" ")){
                            Matrix[i][j] = 0;
                        } else {
                            Matrix[i][j] = 1;
                        }
                    }
                }
                bList.add(new blocks(Matrix, Current));
                Current++;
                copyP--;
            }
        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
