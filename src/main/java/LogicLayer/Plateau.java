package LogicLayer;

import InputLayer.PlateauSize;
import InputLayer.Position;

import java.util.ArrayList;

public class Plateau {
    private PlateauSize plateauSize;
    private static ArrayList<Rover> listOfRovers = new ArrayList<>();

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }

    public PlateauSize getPlateauSize() {
        return plateauSize;
    }

    public ArrayList<Rover> getListOfRovers() {
        return listOfRovers;
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
        try {
            for(Rover rover: listOfRovers) {
                if (rover.getPosition().equals(roverToDelete.getPosition())){
                    listOfRovers.remove(rover);
                }
            }

        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("This rover does not exist on the plateau.");
        }
    }



}
