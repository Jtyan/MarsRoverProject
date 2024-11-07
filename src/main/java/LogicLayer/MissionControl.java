package LogicLayer;

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

//    private boolean checkIfAnyRoverInTheWay(Position roverPosition) {
//        for (Rover rover : listOfRovers) {
//            if (rover.getPosition().getX() == (roverPosition.getX())
//                && rover.getPosition().getY() == (roverPosition.getY())
//            ) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public  void removeRover(Rover roverToDelete) {
//        for (Rover rover : listOfRovers) {
//            if (rover.equals(roverToDelete)) {
//                listOfRovers.remove(rover);
//            } else {
//                throw new IllegalArgumentException("This rover does not exist on the plateau.");
//            }
//        }
//    }

    public void moveRover(ArrayList<Instruction> validListOfInstructions) {
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
