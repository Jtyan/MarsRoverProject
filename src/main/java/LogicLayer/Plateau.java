package LogicLayer;

import InputLayer.PlateauSize;
import InputLayer.Position;

import java.util.ArrayList;

public class Plateau {
    private PlateauSize plateauSize;
    ArrayList<Rover> listOfRovers = new ArrayList<>();

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }

    public PlateauSize getPlateauSize() {
        return plateauSize;
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
            if (rover.getPosition().equals(roverPosition)) {
                return true;
            }
        }
        return false;
    }


}
