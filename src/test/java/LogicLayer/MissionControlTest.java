package LogicLayer;

import InputLayer.CompassDirection;
import InputLayer.Plateau;
import InputLayer.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MissionControlTest {

    private MissionControl missionControl;
    private Rover rover;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        Plateau.resetInstance();
        Plateau.getInstance().setPlateauSize("3", "3");
        Plateau.getInstance().generateObstaclesRandomPosition();
        rover = new Rover();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("check rover position when deploying rover with a valid position")
    void testDeployRover_WithValidPosition() {
        missionControl = new MissionControl(Plateau.getInstance(), rover);
        missionControl.deployRover("3,2,N");

        Position expectedResult = new Position(3, 2, CompassDirection.N);

        assertThat(missionControl.getRoverPosition(rover)).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("show a message when deploying rover with an out of the map grid position")
    void testDeployRover_OutOfPlateauBounds() {
        missionControl = new MissionControl(Plateau.getInstance(), rover);
        missionControl.deployRover("11,11,N");

        assertEquals("Rover cannot be deployed outside of the plateau.", outContent.toString().trim());
    }


    @Test
    @DisplayName("throw an exception when deploying rover with an invalid position")
    public void testDeployRover_WithInvalidPosition() {
        missionControl = new MissionControl(Plateau.getInstance(), rover);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> missionControl.deployRover("1,-1,N"));

        assertEquals("Input is invalid!", exception.getMessage());
    }

    @Test
    @DisplayName("show a message when deploying rover to a position with obstacle")
    public void testDeployRover_WithObstacleInThePosition() {
        missionControl = new MissionControl(Plateau.getInstance(), rover);
        Plateau.getInstance().getObstacles().add(new Position(1, 1, CompassDirection.N));

        missionControl.deployRover("1,1,N");

        assertEquals("Unable to place the rover. There is an obstacle at the location.", outContent.toString().trim());
    }

    @Test
    @DisplayName("check rover position when moving rover with a valid position")
    void testMoveRover_WithValidPosition() {
        missionControl = new MissionControl(Plateau.getInstance(), rover);
        rover.setPosition(new Position(1, 1, CompassDirection.N));

        missionControl.moveRover("MM");

        Position expectedResult = new Position(1, 3, CompassDirection.N);

        assertThat(missionControl.getRoverPosition(rover)).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("show a message when moving rover to an out of the map grid position")
    void testMoveRover_OutOfPlateauBounds() {
        missionControl = new MissionControl(Plateau.getInstance(), rover);
        rover.setPosition(new Position(3, 3, CompassDirection.N));

        missionControl.moveRover("MM");

        assertEquals("Rover cannot move outside of the plateau.",
                outContent.toString().trim());
    }

    @Test
    @DisplayName("show a message when moving rover to a position with obstacle")
    void testMoveRover_ObstacleInThePosition() {
        missionControl = new MissionControl(Plateau.getInstance(), rover);
        rover.setPosition(new Position(1, 1, CompassDirection.N));
        Plateau.getInstance().getObstacles().add(new Position(1, 2, CompassDirection.N));

        missionControl.moveRover("M");

        assertEquals("There is an obstacle in front. Please input new instruction.",
                outContent.toString().trim());
    }
}