package InputLayer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static ArrayList<Instruction> listOfCommands = new ArrayList<>();

    public static ArrayList<Instruction> getListOfCommands() {
        return listOfCommands;
    }

    private static Instruction CheckInstruction(String command) {
        for (Instruction validCommand : Instruction.values()) {
            if (command.equalsIgnoreCase(validCommand.name())) {
                return Instruction.valueOf(command.toUpperCase());
            }
        }
        throw new IllegalArgumentException("No instruction details found!");
    }

    private static CompassDirection checkCompassDirection(String direction) {
        if (direction == null || direction.isEmpty()) {
            throw new IllegalArgumentException("Direction cannot be null or empty");
        }
        for (CompassDirection validCommand : CompassDirection.values()) {
            if (direction.equalsIgnoreCase(validCommand.name())) {
                return CompassDirection.valueOf(direction.toUpperCase());
            }
        }
        throw new IllegalArgumentException("No direction details found!");
    }

    public static ArrayList<Instruction> parseInputToInstruction(String input) {
        final String regex = "^[LRM]*$";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);

        if (input == null || input.isEmpty()) {
            throw new RuntimeException("No input found!");
        }
        String[] inputArr = input.split("");
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input is invalid!");
        } else {
            for (String command : inputArr) {
                Instruction instruction = CheckInstruction(command);
                listOfCommands.add(instruction);
            }
        }
        return listOfCommands;
    }

    public static Position parseInputToPosition(String input) {
        final String regex = "\\b(100|[1-9][0-9]?),(100|[1-9][0-9]?),[NSEW]\\b";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);
        if (input == null) {
            throw new NullPointerException("No input found!");
        } else if (!matcher.matches()) {
            throw new IllegalArgumentException("Input is invalid!");
        }
        String[] inputArray = input.split(",");

        CompassDirection facingDirection = checkCompassDirection(inputArray[inputArray.length - 1]);
        return new Position(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]), facingDirection);
    }

    public static ArrayList<Integer> parseInputToPlateau(String rowString, String columnString) {
        int row;
        int column;

        if (rowString == null || rowString.isEmpty() || columnString == null || columnString.isEmpty()) {
            throw new IllegalArgumentException("User input for map size is empty!");
        }

        try {
            row = Integer.parseInt(rowString.trim());
            column = Integer.parseInt(columnString.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Map dimensions must be valid integers.");
        }
        if (row < 2 || column < 2) {
            throw new IllegalArgumentException("Map dimensions must be at least 2x2.");
        }

        ArrayList<Integer> dimensions = new ArrayList<>();
        dimensions.add(row);
        dimensions.add(column);

        return dimensions;
    }
}
