package InputLayer;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private static ArrayList<Instruction> listOfCommands = new ArrayList<>();

    public static ArrayList<Instruction> getListOfCommands() {
        return listOfCommands;
    }

    public static Instruction checkSingleInstruction(String command) {
        for (Instruction validCommand : Instruction.values()) {
            if (validCommand.name().equalsIgnoreCase(command)) {
                return Instruction.valueOf(command.toUpperCase());
            }
        }
        return null;
    }

    public static CompassDirection checkCompassDirection(String direction) {
        for (CompassDirection validCommand : CompassDirection.values()) {
            if (validCommand.name().equalsIgnoreCase(direction)) {
                return CompassDirection.valueOf(direction.toUpperCase());
            }
        }
        throw new IllegalArgumentException("No direction details found!");
    }

    public static ArrayList<Instruction> parseInputToInstruction(String input) {
        if (input.isEmpty()) {
            System.out.println("No input found!");
            throw new RuntimeException("No input found!");
        }
        input = input.replaceAll("[., ]", "");
        String[] inputArr = input.split("");
        for (String command : inputArr) {
            Instruction instruction = checkSingleInstruction(command);
            if (instruction != null) {
                listOfCommands.add(instruction);
            } else {
                throw new IllegalArgumentException("Input is invalid!");
            }
        }
        return listOfCommands;
    }

    public static Position parseInputToPosition(String input) {
        if (input == null) {
            throw new NullPointerException("No input found!");
        }
        input = input.replaceAll("[., ]", "");
        if (input.length() != 3) {
            throw new IllegalArgumentException("input is invalid!");
        }
        String[] inputArray = input.split("");
        CompassDirection facingDirection = checkCompassDirection(String.valueOf(input.charAt(inputArray.length - 1)));
        return new Position(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]), facingDirection);
    }

    public static ArrayList<Integer> parseInputToPlateau(String input) {
        int width;
        int height;

        if (input.isEmpty()) {
            throw new IllegalArgumentException("input is invalid!");
        }
        input = input.replaceAll("[., ]", "");
        String[] inputArray = input.split("");
        if (inputArray.length != 2) {
            throw new IllegalArgumentException("input is invalid!");
        }

        try {
            width = Integer.parseInt(inputArray[0].trim());
            height = Integer.parseInt(inputArray[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Map dimensions must be valid integers.");
        }
        if (width < 2 && height < 2) {
            throw new IllegalArgumentException("Map dimensions must be at least 2x2.");
        }

        ArrayList<Integer> dimensions = new ArrayList<>();
        dimensions.add(width);
        dimensions.add(height);

        return dimensions;
    }

}
