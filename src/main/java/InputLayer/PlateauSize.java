package InputLayer;

import java.util.Arrays;

public class PlateauSize {
    private static PlateauSize instance;
    private int row;
    private int column;

    private PlateauSize() {};

    public static PlateauSize getInstance() {
        if(instance == null) {
            instance = new PlateauSize();
        }
        return instance;
    }

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
