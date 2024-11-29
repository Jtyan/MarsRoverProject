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

    public Boolean deployRover(String positionInput) {
        Position validInput = InputParser.parseInputToPosition(positionInput);
        if (plateau.hasObstacle(validInput)) {
            System.out.println("Unable to place the rover. There is an obstacle at the location.");
            return false;
        } else if (validInput.getX() > plateau.getRow() || validInput.getY() > plateau.getColumn()) {
            System.out.println("Rover cannot be deployed outside of the plateau.");
            return false;
        } else {
            rover.setPosition(validInput);
            System.out.println("Rover deployed at position: " + validInput.getX() + "," + validInput.getY() + "," + validInput.getFacing() + ".");
            return true;
        }
    }

    public void moveRover(String instructionInput) {
        InputParser.getListOfCommands().clear();
        ArrayList<Instruction> validListOfInstructions = InputParser.parseInputToInstruction(instructionInput);

        for (Instruction instruction : validListOfInstructions) {
            if (instruction == Instruction.M) {
                Boolean hasObstacle = switch (rover.getPosition().getFacing()) {
                    case N -> plateau.hasObstacle(new Position(rover.getPosition().getX(), rover.getPosition().getY() + 1));
                    case W -> plateau.hasObstacle(new Position(rover.getPosition().getX() - 1, rover.getPosition().getY()));
                    case S -> plateau.hasObstacle(new Position(rover.getPosition().getX(), rover.getPosition().getY() - 1));
                    case E -> plateau.hasObstacle(new Position(rover.getPosition().getX() + 1, rover.getPosition().getY()));
                };

                if (hasObstacle) {
                    System.out.println("There is an obstacle in front. Please input new instruction.");
                    return;
                } else if (rover.getPosition().getX() >= plateau.getRow()  || rover.getPosition().getY() >= plateau.getColumn()
                        || rover.getPosition().getX() < 1 || rover.getPosition().getY() < 1) {
                    System.out.println("Rover cannot move outside of the plateau.");
                    return;
                } else {
                    rover.setNewPosition(instruction);
                    System.out.println("Rover moves to " + rover.getPosition().getX() + "," + rover.getPosition().getY() + "," + rover.getPosition().getFacing() + ".");
                }

            } else if (instruction == Instruction.L || instruction == Instruction.R) {
                rover.setNewPosition(instruction);
            }
        }
        System.out.println("Rover final position: " + rover.getPosition().getX() + "," + rover.getPosition().getY() + "," + rover.getPosition().getFacing() + ".");
    }
}
