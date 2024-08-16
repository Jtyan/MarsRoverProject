package LogicLayer;

import InputLayer.CompassDirection;
import InputLayer.PlateauSize;
import InputLayer.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {


    private static final Plateau plateau = new Plateau(new PlateauSize(5,5));


    @Test
    void testAddRover_WithValidPosition() {
        ArrayList<Rover> mockListOfRovers = new ArrayList<>(){{
            add(new Rover(new Position(2,4, CompassDirection.N)));
            add(new Rover(new Position(0,1, CompassDirection.S)));
            add(new Rover(new Position(2,3, CompassDirection.E)));
            add(new Rover(new Position(3,1, CompassDirection.W)));
        }};

        plateau.addRover(new Rover(new Position(2,4,CompassDirection.N)));
        plateau.addRover(new Rover(new Position(0,1, CompassDirection.S)));
        plateau.addRover(new Rover(new Position(2,3, CompassDirection.E)));
        plateau.addRover(new Rover(new Position(3,1, CompassDirection.W)));
        ArrayList<Rover> listOfRovers = plateau.getListOfRovers();

        assertEquals(listOfRovers.size(), mockListOfRovers.size());

        // Using assertJ library
//        assertThat(mockListOfRovers).usingRecursiveComparison().isEqualTo(listOfRovers);

        // (Without assertJ library) Compare each element in the lists
        for (int i = 0; i < mockListOfRovers.size(); i++) {
            assertTrue(compareRovers(listOfRovers.get(i).getPosition().getX(), mockListOfRovers.get(i).getPosition().getX()));
            assertTrue(compareRovers(listOfRovers.get(i).getPosition().getY(), mockListOfRovers.get(i).getPosition().getY()));
        }
    }

    @Test
    @DisplayName("throw an exception when adding invalid position")
    public void testAddRover_WithInvalidPosition() {
        plateau.addRover(new Rover(new Position(2,4,CompassDirection.N)));
        plateau.addRover(new Rover(new Position(0,1, CompassDirection.S)));

        assertThrows(IllegalArgumentException.class, () -> plateau.addRover(new Rover(new Position(0,1, CompassDirection.E))));
    }

    @Test
    @DisplayName("remove rover from listOfRover when given an existing rover")
    void testRemoveRover_WithExistingRover() {
        ArrayList<Rover> mockListOfRovers = new ArrayList<>(){
            {
                add(new Rover(new Position(0, 1, CompassDirection.S)));
            }
        };

        plateau.addRover(new Rover(new Position(2,4,CompassDirection.N)));
        plateau.addRover(new Rover(new Position(0,1, CompassDirection.S)));
        plateau.removeRover(new Rover(new Position(2,4,CompassDirection.N)));
        ArrayList<Rover> listOfRovers = plateau.getListOfRovers();

        assertThat(mockListOfRovers).usingRecursiveComparison().isEqualTo(listOfRovers);
    }

    private <T> boolean compareRovers(T r1, T r2) {
        // This assumes the Rover class's equals() method correctly compares two Rovers
        return r1.equals(r2);
    }
}