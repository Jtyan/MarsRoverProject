package InputLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;

import static InputLayer.Instruction.*;
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

        ArrayList<Instruction> result = inputParser.parseStringToInstruction(input);
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

        assertThrows(IllegalArgumentException.class, () -> inputParser.parseStringToInstruction(input));
    }

    @Test
    @DisplayName("throw an exception when given an empty string")
    public void testCheckForInstruction_emptyInput() {
        String input = "";

        assertThrows(RuntimeException.class, () -> inputParser.parseStringToInstruction(input));
    }

    @Test
    @DisplayName("return a CompassDirection given a valid string of position")
    public void testCheckForCompassDirection_validInput() {
        String input = "1 3 N";

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




}