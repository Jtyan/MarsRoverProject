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

    public static synchronized void resetInstance() {
        instance = null;
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

    public Set<Position> getObstacles() {
        return obstacles;
    }

    public void setObstacles(Set<Position> obstacles) {
        this.obstacles = obstacles;
    }

    public void generateObstaclesRandomPosition() {
        int totalCells = row * column;
        int maxObstacles = (int) Math.ceil(totalCells / 10.0);
        int numberOfObstacles = random.nextInt(maxObstacles);

        System.out.println("Number of obstacles placed: " + numberOfObstacles);

        for (int i = 0; i < numberOfObstacles; i++) {
            Position newObstaclePosition;
            boolean isDuplicate;

            do {
                newObstaclePosition = new Position(random.nextInt(row), random.nextInt(column));
                isDuplicate = false;

                for (Position position : obstacles) {
                    if (position.getX() == newObstaclePosition.getX() &&
                            position.getY() == newObstaclePosition.getY()) {
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);

            obstacles.add(newObstaclePosition);
            System.out.println(newObstaclePosition.getX() + "," + newObstaclePosition.getY());
        }
    }

    public boolean hasObstacle(Position position) {
        Position positionToCheck = new Position(position.getX(), position.getY());
        for (Position obstacle : obstacles) {
            if (obstacle.getX() == positionToCheck.getX() && obstacle.getY() == positionToCheck.getY()) {
                return true;
            }
        }
        return false;
    }

    public void setPlateauSize(String rowString, String columnString) {
        ArrayList<Integer> dimension = InputParser.parseInputToPlateau(rowString, columnString);
        this.setRow(dimension.get(0));
        this.setColumn(dimension.get(1));
    }
}
