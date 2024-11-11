package LogicLayer;

import InputLayer.InputParser;
import InputLayer.Instruction;
import InputLayer.Plateau;
import InputLayer.Position;

import java.util.ArrayList;

public class MissionControl {
    private Plateau plateau;
    private Rover rover;

    public MissionControl(Plateau plateau, Rover rover) {
        this.plateau = plateau;
        this.rover = rover;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Position getRoverPosition(Rover rover) {
        if (rover != null) {
            return rover.getPosition();
        }
        throw new IllegalArgumentException("Rover does not exist on the plateau.");
    }

    //deployRover, parseInputToPosition, setRoverPosition
    public void deployRover(String positionInput) {
        Position validInput = InputParser.parseInputToPosition(positionInput);
        if (plateau.hasObstacle(validInput)) {
            throw new RuntimeException("There is an obstacle at " + validInput + ".");
        } else if (validInput.getX() > plateau.getRow() || validInput.getY() > plateau.getColumn()) {
            throw new RuntimeException("Rover already exists on the plateau.");
        } else {
            rover.setPosition(validInput);
        }
    }

    public void moveRover(String instructionInput) {
        ArrayList<Instruction> validListOfInstructions = InputParser.parseInputToInstruction(instructionInput);
        for (Instruction instruction : validListOfInstructions) {
            Position newPosition = rover.setNewPosition(instruction);
            if (plateau.hasObstacle(newPosition)) {
                System.out.println("There is an obstacle at " + newPosition + ".");
                return;
            } else {
                rover.setNewPosition(instruction);
            }
        }
    }
}
