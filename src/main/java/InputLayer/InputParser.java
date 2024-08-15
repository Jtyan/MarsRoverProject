package InputLayer;

import java.util.ArrayList;

public class InputParser {

    ArrayList<Instruction> listOfCommands = new ArrayList<>();

    public String input;

    public ArrayList<Instruction> parseStringToInstruction(String input) {
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

    public Instruction checkInstruction (String command) {

        for (Instruction validCommand : Instruction.values()) {
            if (validCommand.name().equals(command)) {
                return Instruction.valueOf(command);
            }
        }
        return null;
    }

    public CompassDirection checkCompassDirection(String position) {
        if (position.isEmpty()) {
            System.out.println("No input found!");
            throw new RuntimeException("No input found!");
        }
        String[] positionArray = position.split("");
        for (String direction : positionArray) {
            for (CompassDirection validCommand : CompassDirection.values()) {
                if (validCommand.name().equals(direction)) {
                    System.out.println("Rover is facing: " + direction);
                    return CompassDirection.valueOf(direction);
                }
            }
        }
        throw new IllegalArgumentException("No direction details found!");
    }
}
