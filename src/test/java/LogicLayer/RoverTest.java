package LogicLayer;

import InputLayer.CompassDirection;
import InputLayer.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static InputLayer.Instruction.*;
import static InputLayer.Instruction.M;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverTest {
    Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover();
    }

    @Test
    void testSetNewPosition_WithMovingInstructionWhenFacingNorth() {
        rover.setPosition(new Position(1, 3, CompassDirection.N));
        Position expectedResult = new Position(1, 4, CompassDirection.N);

        Position result = rover.setNewPosition(M);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithMovingInstructionWhenFacingWest() {
        rover.setPosition(new Position(1, 3, CompassDirection.W));

        Position result = rover.setNewPosition(M);
        Position expectedResult = new Position(0, 3, CompassDirection.W);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithMovingInstructionWhenFacingSouth() {
        rover.setPosition(new Position(1, 3, CompassDirection.S));
        Position expectedResult = new Position(1, 2, CompassDirection.S);

        Position result = rover.setNewPosition(M);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithMovingInstructionWhenFacingEast() {
        rover.setPosition(new Position(1, 3, CompassDirection.E));

        Position expectedResult = new Position(2, 3, CompassDirection.E);

        Position result = rover.setNewPosition(M);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithRotatingLeftWhenFacingNorth() {
        rover.setPosition(new Position(1, 3, CompassDirection.N));
        Position expectedResult = new Position(1, 3, CompassDirection.W);

        Position result = rover.setNewPosition(L);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithRotatingLeftWhenFacingWest() {
        rover.setPosition(new Position(1, 3, CompassDirection.W));
        Position expectedResult = new Position(1, 3, CompassDirection.S);

        Position result = rover.setNewPosition(L);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithRotatingRightWhenFacingSouth() {
        rover.setPosition(new Position(1, 3, CompassDirection.S));
        Position expectedResult = new Position(1, 3, CompassDirection.W);

        Position result = rover.setNewPosition(R);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithRotatingRightWhenFacingEast() {
        rover.setPosition(new Position(1, 3, CompassDirection.E));
        Position expectedResult = new Position(1, 3, CompassDirection.S);

        Position result = rover.setNewPosition(R);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_InvalidInstruction() {
        rover.setPosition(new Position(0, 0, CompassDirection.N));
        assertThrows(IllegalArgumentException.class, () -> rover.setNewPosition(null));
    }

    @Test
    void testRotateFromUninitializedPosition() {
        assertThrows(NullPointerException.class, () -> rover.setNewPosition(L));
    }

}