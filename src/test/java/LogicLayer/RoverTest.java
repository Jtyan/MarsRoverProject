package LogicLayer;

import InputLayer.CompassDirection;
import InputLayer.Instruction;
import InputLayer.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static InputLayer.Instruction.*;
import static InputLayer.Instruction.M;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void testRotate_WithValidInstruction() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));
        
        rover.rotate(Instruction.L);
        
        assertEquals(CompassDirection.W, rover.getPosition().getFacing());
    }

    @Test
    @DisplayName("Expect CompassDirection unchanged when given invalid instruction")
    void testRotate_WithInvalidInstruction() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));

        assertThrows(IllegalArgumentException.class, () -> rover.rotate(M));
    }

    @Test
    @DisplayName("throw an exception when given an invalid string of instruction")
    public void testRotate_null() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));

        assertThrows(IllegalArgumentException.class, () -> rover.rotate(null));
    }

    @Test
    void testMove_WithValidInstruction() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.S));
        rover.move(Instruction.M);

        Position expectedResult = new Position(1, 2, CompassDirection.S);

        assertThat(rover.getPosition()).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Expect x and y unchanged when given invalid instruction")
    void testMove_WithInvalidInstruction() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));

        assertThrows(IllegalArgumentException.class, () -> rover.move(R));
    }

    @Test
    @DisplayName("throw an exception when given an invalid instruction")
    public void testMove_null() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));

        assertThrows(IllegalArgumentException.class, () -> rover.move(null));
    }

    @Test
    void testSetNewPosition_WithMovingInstruction() {

        Rover rover = new Rover(new Position(1,3, CompassDirection.E));

        Position expectedResult = new Position(2, 3, CompassDirection.E);

        Position result = rover.setNewPosition(M);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testGetNewPosition_WithRotatingInstruction() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));

        Position expectedResult = new Position(1, 3, CompassDirection.W);

        Position result = rover.setNewPosition(L);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}