package InputLayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static InputLayer.Instruction.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {


    @Test
    @DisplayName("return instruction given a valid instruction string")
    public void testParseInputToInstruction_validInput() {
        String input = "LRMMRM";

        ArrayList<Instruction> result = InputParser.parseInputToInstruction(input);
        ArrayList<Instruction> expectedResult = new ArrayList<>() {{
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
    @DisplayName("throw an illegal exception when given a valid string of instruction but in lowerCase")
    public void testParseInputToInstruction_validLowerCaseInput() {
        String input = "lrmmrm";

        assertThrows(IllegalArgumentException.class, () -> InputParser
                .parseInputToInstruction(input));
    }

    @Test
    @DisplayName("throw an exception when given an invalid string of instruction")
    public void testParseInputToInstruction_invalidInput() {
        String input = "LRDMMDSRM";

        assertThrows(IllegalArgumentException.class, () -> InputParser
                .parseInputToInstruction(input));
    }

    @Test
    @DisplayName("throw an exception when given an empty string")
    public void testParseInputToInstruction_emptyInput() {
        String input = "";

        assertThrows(RuntimeException.class, () -> InputParser.parseInputToInstruction(input));
    }

    @Test
    @DisplayName("throw an exception when given a null input")
    public void testParseInputToInstruction_nullInput() {
        assertThrows(RuntimeException.class, () -> InputParser.parseInputToInstruction(null));
    }

    @Test
    @DisplayName("return a position given a valid input string")
    public void testCheckForParseInputToPosition_validInput() {
        String input = "1,2,N";
        Position expectedResult = new Position(1, 2, CompassDirection.N);
        Position result = InputParser.parseInputToPosition(input);


        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult); //using assertj library to check the fields of the two
    }

    @Test
    @DisplayName("throw an illegal exception when given a valid string of position but in lowercase")
    public void testCheckForParseInputToPosition_validLowerCaseInput() {
        String input = "1,2,n";

        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPosition(input));
    }

    @Test
    @DisplayName("throw an illegal exception when given a negative integer as position")
    public void testCheckForParseInputToPosition_negativeIntegerInput() {
        String input = "-3,-3,N";

        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPosition(input));
    }

    @Test
    @DisplayName("should throw NullPointerException for null input to parseInputToPosition")
    public void testParseInputToPosition_nullInput() {
        String input = null;

        assertThrows(NullPointerException.class, () -> InputParser.parseInputToPosition(input));
    }

    @Test
    @DisplayName("should throw IllegalArgumentException for short invalid input to parseInputToPosition")
    public void testParseInputToPosition_shortInvalidInput() {
        String input = "1N";

        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPosition(input));
    }

    @Test
    @DisplayName("should return plateau dimensions for valid inputs")
    public void testParseInputToPlateau_validInput() {
        String row = "5";
        String column = "5";

        ArrayList<Integer> result = InputParser.parseInputToPlateau(row, column);
        ArrayList<Integer> expectedResult = new ArrayList<>() {{
            add(5);
            add(5);
        }};

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("should throw IllegalArgumentException for empty plateau inputs")
    public void testParseInputToPlateau_emptyInput() {
        String row = "";
        String column = "";

        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPlateau(row, column));
    }

    @Test
    @DisplayName("should throw IllegalArgumentException for null plateau inputs")
    public void testParseInputToPlateau_nullInput() {
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPlateau(null, null));
    }

    @Test
    @DisplayName("should throw IllegalArgumentException for non-integer plateau inputs")
    public void testParseInputToPlateau_invalidInput() {
        String row = "five";
        String column = "five";

        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPlateau(row, column));
    }

    @Test
    @DisplayName("should throw IllegalArgumentException for too small plateau dimensions")
    public void testParseInputToPlateau_tooSmallInput() {
        String row = "1";
        String column = "1";

        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPlateau(row, column));
    }

    @Test
    @DisplayName("should throw IllegalArgumentException for negative plateau dimensions")
    public void testParseInputToPlateau_negativeInput() {
        String row = "-10";
        String column = "-10";

        assertThrows(IllegalArgumentException.class, () -> InputParser.parseInputToPlateau(row, column));
    }

}