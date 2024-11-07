package InputLayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Plateau {
    private static Plateau instance;
    private int row;
    private int column;
    private Set<Position> obstacles;

    private Random random;

    public Plateau() {
        this.random = new Random();
        this.obstacles = new HashSet<>();
    }

    public static synchronized Plateau getInstance() {
        if (instance == null) {
            instance = new Plateau();
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

    public void generateObstaclesRandomly(Integer numberOfObstacles) {
        for (int i = 0; i < numberOfObstacles; i++) {
            Position newObstaclePosition = new Position(random.nextInt(row), random.nextInt(column));
            if (obstacles.contains(newObstaclePosition)) {
                i--;
            }
            obstacles.add(newObstaclePosition);
        }
    }

    public boolean hasObstacle(Position position) {
        return obstacles.contains(position);
    }

    public void setPlateauSize(String mapInput) {
        ArrayList<Integer> dimension = InputParser.parseInputToPlateau(mapInput);
        this.setRow(dimension.get(0));
        this.setColumn(dimension.get(1));
    }
}
