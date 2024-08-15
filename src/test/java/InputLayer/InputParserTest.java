package InputLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static InputLayer.Instruction.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    InputParser inputParser;

    @BeforeEach
    public void setUp() {
        inputParser = new InputParser();
    }

    @Test
    @DisplayName("return list of instruction given a valid string of instruction")
    public void testCheckForInstruction_validInput() {
        String input = "LRMMRM";

        ArrayList<Instruction> result = inputParser.parseInputToInstruction(input);
        ArrayList<Instruction> expectedResult = new ArrayList<>(){{
            add(L);
            add(R);
            add(M);
            add(M);
            add(R);
            add(M);
        }};

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("return list of instruction given a valid string of instruction but in lowerCase")
    public void testCheckForInstruction_validLowerCaseInput() {
        String input = "lrmmrm";

        ArrayList<Instruction> result = inputParser.parseInputToInstruction(input);
        ArrayList<Instruction> expectedResult = new ArrayList<>(){{
            add(L);
            add(R);
            add(M);
            add(M);
            add(R);
            add(M);
        }};

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("throw an exception when given an invalid string of instruction")
    public void testCheckForInstruction_invalidInput() {
        String input = "LRDMMDSRM";

        assertThrows(IllegalArgumentException.class, () -> inputParser.parseInputToInstruction(input));
    }

    @Test
    @DisplayName("throw an exception when given an empty string")
    public void testCheckForInstruction_emptyInput() {
        String input = "";

        assertThrows(RuntimeException.class, () -> inputParser.parseInputToInstruction(input));
    }

    @Test
    @DisplayName("return a CompassDirection given a valid string of position")
    public void testCheckForCompassDirection_validInput() {
        String input = "E";

        CompassDirection result = inputParser.checkCompassDirection(input);
        CompassDirection expectedResult = CompassDirection.E;

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("return a CompassDirection given a valid string of position but in lowercase")
    public void testCheckForCompassDirection_validLowerCaseInput() {
        String input = "n";

        CompassDirection result = inputParser.checkCompassDirection(input);
        CompassDirection expectedResult = CompassDirection.N;

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("throw an exception when given a invalid string of position")
    public void testCheckForCompassDirection_invalidInput() {
        String input = "1 3 2";

        assertThrows(IllegalArgumentException.class, () -> inputParser.checkCompassDirection(input));
    }

    @Test
    @DisplayName("return a position given a valid string of position but in lowercase")
    public void testCheckForParseInputToPosition_validInput() {
        String input = "1 2 N";
        Position expectedResult = new Position (1, 2, CompassDirection.N);
        Position result = inputParser.parseInputToPosition(input);


        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult); //using assertj library to check the fields of the two
    }

    @Test
    @DisplayName("return a position given a valid string of position but in lowercase")
    public void testCheckForParseInputToPosition_validLowerCaseInput() {
        String input = "1 2 n";

        Position result = inputParser.parseInputToPosition(input);
        Position expectedResult = new Position(1, 2, CompassDirection.N);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }




}