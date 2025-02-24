public class blocks {
    public int[][] block;
    public char character;
    public int[] lastPos = new int[2];
    public int rotation;
    public blocks(int[][] block, char character) {
        this.block = block;
        this.character = character;
        this.lastPos[0] = 0;
        this.lastPos[1] = 0;
        this.rotation = 0;
    }

    public void rotateBlock(){
        int[][] matrix = new int[this.block[0].length][this.block.length];
        for (int i = 0; i < this.block.length; i++){
            for (int j = 0; j < this.block[0].length; j++){
                matrix[j][i] = this.block[i][j];
            }
        }
        this.block = matrix;
        this.rotation++;
    }

    public void mirrorBlock(){
        int[][] matrix = new int[this.block.length][this.block[0].length];
        for (int i = 0; i < this.block.length; i++){
            for (int j = 0; j < this.block[0].length; j++){
                matrix[i][this.block[0].length-1-j] = this.block[i][j];
            }
        }
        this.block = matrix;
    }
}
