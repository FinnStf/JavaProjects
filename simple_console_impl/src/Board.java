import java.util.Arrays;

public class Board {
    public int [][] grid;

    public Board(int col){
        this.createField(col);
    }


    public void createField(int col) {
        grid = new int[col][col];
        for (int i = 0; i< grid.length;i++){
            for (int j=0; j<grid[i].length;j++) {
                grid[i][j]=0;
            }
        }
    }

    @Override
    public String toString() {
        String erg = "";
        for (int i = grid.length-1; i>=0;i--){
                if (i< grid.length){
                    erg +="\n";
                }
            for (int j=0; j<grid[i].length;j++) {
                if (grid[i][j]==0){
                    erg+=" [ ] ";
                }else if (grid[i][j]==1){
                    erg+=" [X] ";
                }else{
                    erg+=" [O] ";
                }
            }
        }
        return erg;
    }
}
