package InputLayer;

import java.util.Arrays;

public class PlateauSize {
    private static PlateauSize instance;
    private int row;
    private int column;

    public PlateauSize() {
    }

    public static synchronized PlateauSize getInstance() {
        if (instance == null) {
            instance = new PlateauSize();
        }
        return instance;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
