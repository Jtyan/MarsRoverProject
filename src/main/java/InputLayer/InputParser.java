package InputLayer;

import java.util.ArrayList;

public class InputParser {

    ArrayList<Instruction> listOfCommands = new ArrayList<>();

    public String input;

    public Instruction checkInstruction (String command) {
        for (Instruction validCommand : Instruction.values()) {
            if (validCommand.name().equalsIgnoreCase(command)) {
                return Instruction.valueOf(command.toUpperCase());
            }
        }
        return null;
    }

    public CompassDirection checkCompassDirection(String direction) {
            for (CompassDirection validCommand : CompassDirection.values()) {
                if (validCommand.name().equalsIgnoreCase(direction)) {
                    return CompassDirection.valueOf(direction.toUpperCase());
                }
        }
        throw new IllegalArgumentException("No direction details found!");
    }

    public ArrayList<Instruction> parseInputToInstruction(String input) {
        if(input.isEmpty()) {
            System.out.println("No input found!");
            throw new RuntimeException("No input found!");
        }
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

    public Position parseInputToPosition(String input) {

        input = input.replace(" ", "");
        if (input.isEmpty() || input.length() > 3) {
            throw new IllegalArgumentException("input is invalid!");
        }
        String[] inputArray = input.split("");
        CompassDirection facingDirection = checkCompassDirection(String.valueOf(input.charAt(inputArray.length - 1)));
        return new Position(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]), facingDirection);
    }



}
