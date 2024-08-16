package InputLayer;

import java.util.Arrays;

public class PlateauSize {
//    private static PlateauSize instance;
    private int row;
    private int column;

    public PlateauSize(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

//    public static PlateauSize getInstance() {
//        if(instance == null) {
//            instance = new PlateauSize();
//        }
//        return instance;
//    }
//
//    public int[][] setGrid(int row, int column){
//        int[][] grid = new int [row][column];
//        System.out.println(Arrays.deepToString(grid));
//
//        for (int r = 0; r < grid.length; r++) {
//            for(int c = 0; c < grid.length; c++) {
//                grid[r][c] = c;
//            }
//        }
//        System.out.println(Arrays.deepToString(grid));
//        return grid;
//    }

}
