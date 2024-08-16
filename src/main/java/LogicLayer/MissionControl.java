package LogicLayer;

import InputLayer.PlateauSize;
import InputLayer.Position;

import java.util.ArrayList;

public class MissionControl {
    private PlateauSize plateauSize;
    private ArrayList<Rover> listOfRovers = new ArrayList<>();

    public MissionControl(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }

    public ArrayList<Rover> getListOfRovers() {
        return listOfRovers;
    }

    public Rover getRover(Rover rover) {
        if (listOfRovers.contains(rover)) {
            return rover;
        }
        throw new IllegalArgumentException("This rover does not exist on the plateau.");
    }

    public void addRover(Rover rover) {
        Position roverPosition = rover.getPosition();
        if (checkIfAnyRoverInTheWay(roverPosition)) {
            throw new IllegalArgumentException("There is another rover in the way");
        }
        listOfRovers.add(rover);
    }

    private boolean checkIfAnyRoverInTheWay(Position roverPosition) {
        for (Rover rover : listOfRovers) {
            if (rover.getPosition().getX() == (roverPosition.getX())
                && rover.getPosition().getY() == (roverPosition.getY())
            ) {
                return true;
            }
        }
        return false;
    }

    public  void removeRover(Rover roverToDelete) {
        for (Rover rover : listOfRovers) {
            if (rover.equals(roverToDelete)) {
                listOfRovers.remove(rover);
            } else {
                throw new IllegalArgumentException("This rover does not exist on the plateau.");
            }
        }
    }
}
