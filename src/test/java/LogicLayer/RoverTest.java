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
        
        rover.rotate(Instruction.M);
        
        assertEquals(CompassDirection.N, rover.getPosition().getFacing());
    }

    @Test
    @DisplayName("throw an exception when given an invalid string of instruction")
    public void testRotate_null() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));

        assertThrows(IllegalArgumentException.class, () -> rover.rotate(null));
    }

    @Test
    void testMove_WithValidInstruction() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));
        rover.move(Instruction.M);

        Position expectedResult = new Position(1, 4, CompassDirection.N);

        assertThat(rover.getPosition()).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Expect x and y unchanged when given invalid instruction")
    void testMove_WithInvalidInstruction() {
        Rover rover = new Rover(new Position(1,3, CompassDirection.N));
        rover.move(Instruction.R);
        
        Position expectedResult = new Position(1, 3, CompassDirection.N);
        
        assertThat(rover.getPosition()).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testGetNewPosition_WithValidListOfInstruction() {

        Rover rover = new Rover(new Position(1,3, CompassDirection.N));
        ArrayList<Instruction> listOfInstruction = new ArrayList<>(){{
            add(L);
            add(R);
            add(M);
            add(M);
            add(R);
            add(M);
        }};

        Position expectedResult = new Position(2, 5, CompassDirection.E);

        Position result = rover.setNewPosition(listOfInstruction);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testGetNewPosition_WithOnlyListOfInstructionToMove() {

        Rover rover = new Rover(new Position(1,3, CompassDirection.E));
        ArrayList<Instruction> listOfInstruction = new ArrayList<>(){{
            add(M);
            add(M);
            add(M);
            add(M);
        }};

        Position expectedResult = new Position(5, 3, CompassDirection.E);

        Position result = rover.setNewPosition(listOfInstruction);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void testSetNewPosition_WithOnlyListOfInstructionToRotate() {

        Rover rover = new Rover(new Position(1,3, CompassDirection.E));
        ArrayList<Instruction> listOfInstruction = new ArrayList<>(){{
            add(L);
            add(L);
            add(R);
            add(L);
        }};

        Position expectedResult = new Position(1, 3, CompassDirection.W);

        Position result = rover.setNewPosition(listOfInstruction);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}