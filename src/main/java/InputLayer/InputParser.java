package InputLayer;

import java.util.ArrayList;

public class InputParser {

    private static ArrayList<Instruction> listOfCommands = new ArrayList<>();

    public String input;

    public static ArrayList<Instruction> getListOfCommands() {
        return listOfCommands;
    }

    public static Instruction checkInstruction (String command) {
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
        if(input.isEmpty()) {
            System.out.println("No input found!");
            throw new RuntimeException("No input found!");
        }
        input = input.replaceAll("[., ]", "");
        String[] inputArr = input.split("");
        for (String command : inputArr) {
            Instruction instruction = checkInstruction(command);
            if (instruction != null) {
                listOfCommands.add(instruction);
            } else {
                throw new IllegalArgumentException("Invalid Input found!");
            }
        }
        return listOfCommands;
    }

    public static Position parseInputToPosition(String input) {
        input = input.replaceAll("[., ]", "");
        if (input.isEmpty() || input.length() > 3) {
            throw new IllegalArgumentException("input is invalid!");
        }
        String[] inputArray = input.split("");
        CompassDirection facingDirection = checkCompassDirection(String.valueOf(input.charAt(inputArray.length - 1)));
        return new Position(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]), facingDirection);
    }

    public static void parseInputToPlateau(String input){
        if (input.isEmpty()) {
            throw new IllegalArgumentException("input is invalid!");
        }

    }

}
