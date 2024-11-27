package InputLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {

    @BeforeEach
    void setUp() {
        Plateau.resetInstance();
    }

    @Test
    @DisplayName("Ensure obstacles are generated randomly on an empty plateau")
    public void testGenerateObstaclesRandomPosition_emptyPlateau() {
        Plateau.getInstance().setPlateauSize("10", "10");
        Plateau.getInstance().generateObstaclesRandomPosition();



        assertTrue(Plateau.getInstance().getObstacles().size() >= 0 && Plateau.getInstance().getObstacles().size() < 10);
    }

    @Test
    @DisplayName("Ensure obstacles will always be zero on plateau size smaller than 4 x 4")
    public void testGenerateObstaclesRandomPosition_smallPlateau() {
        Plateau.getInstance().setPlateauSize("3", "3");
        Plateau.getInstance().generateObstaclesRandomPosition();

        assertTrue(Plateau.getInstance().getObstacles().isEmpty());
    }

    @Test
    @DisplayName("check hasObstacle method with position containing obstacle")
    public void testHasObstacle_withObstaclePosition() {
        Plateau.getInstance().setPlateauSize("10", "10");
        Set<Position> obstacles = Plateau.getInstance().getObstacles();
        obstacles.add(new Position(1, 1));
        obstacles.add(new Position(2, 2));

        assertTrue(Plateau.getInstance().hasObstacle(new Position(1, 1)));
        assertTrue(Plateau.getInstance().hasObstacle(new Position(2, 2)));
    }

    @Test
    @DisplayName("check hasObstacle method with position without obstacle")
    public void testHasObstacle_withNoObstaclePosition() {
        Plateau.getInstance().setPlateauSize("10", "10");
        Set<Position> obstacles = Plateau.getInstance().getObstacles();
        obstacles.add(new Position(1, 1));

        assertFalse(Plateau.getInstance().hasObstacle(new Position(1, 2)));
        assertFalse(Plateau.getInstance().hasObstacle(new Position(3, 2)));
    }

    @Test
    @DisplayName("check hasObstacle method with empty obstacles")
    public void testHasObstacle_withEmptyObstacleList() {
        Plateau.getInstance().setPlateauSize("10", "10");

        assertFalse(Plateau.getInstance().hasObstacle(new Position(4, 2)));
        assertFalse(Plateau.getInstance().hasObstacle(new Position(3, 2)));
    }

    @Test
    @DisplayName("Test setting plateau size with valid input")
    public void testSetPlateauSize_validInput() {
        Plateau instance = Plateau.getInstance();
        instance.setPlateauSize("20", "30");
        assertTrue(instance.getRow() == 20 && instance.getColumn() == 30);
    }

    @Test
    @DisplayName("Test setting plateau size with negative dimensions")
    public void testSetPlateauSize_negativeDimensions() {
        Plateau instance = Plateau.getInstance();
        assertThrows(IllegalArgumentException.class, () -> instance.setPlateauSize("-10", "10"));
        assertThrows(IllegalArgumentException.class, () -> instance.setPlateauSize("10", "-10"));
        assertThrows(IllegalArgumentException.class, () -> instance.setPlateauSize("-10", "-10"));
    }

}
