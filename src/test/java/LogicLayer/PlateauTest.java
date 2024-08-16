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
    private static final Rover rover1 = new Rover(new Position(2,4, CompassDirection.N));
    private static final Rover rover2 = new Rover(new Position(0,1, CompassDirection.S));
    private static final Rover rover3 = new Rover(new Position(2,3, CompassDirection.E));
    private static final Rover rover4 = new Rover(new Position(3,1, CompassDirection.W));
    @Test
    void testAddRover_WithValidPosition() {
        Plateau plateau = new Plateau(new PlateauSize(5,5));
        ArrayList<Rover> mockListOfRovers = new ArrayList<>(){{
            add(new Rover(new Position(2,4, CompassDirection.N)));
            add(new Rover(new Position(0,1, CompassDirection.S)));
            add(new Rover(new Position(2,3, CompassDirection.E)));
            add(new Rover(new Position(3,1, CompassDirection.W)));
        }};

        plateau.addRover(rover1);
        plateau.addRover(rover2);
        plateau.addRover(rover3);
        plateau.addRover(rover4);
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
        Plateau plateau = new Plateau(new PlateauSize(5,5));
        plateau.addRover(rover1);
        plateau.addRover(rover2);
        System.out.println(plateau.getListOfRovers().size());
        assertThrows(IllegalArgumentException.class, () -> plateau.addRover(new Rover(new Position(0,1, CompassDirection.E))));
    }

    @Test
    @DisplayName("remove rover from listOfRover when given an existing rover")
    void testRemoveRover_WithExistingRover() {
        Plateau plateau = new Plateau(new PlateauSize(5,5));
        plateau.addRover(rover1);
        plateau.addRover(rover2);
        plateau.removeRover(rover1);

        ArrayList<Rover> expectedListOfRovers = new ArrayList<>();
        expectedListOfRovers.add(rover2);

        ArrayList<Rover> listOfRovers = plateau.getListOfRovers();

        assertThat(expectedListOfRovers).usingRecursiveComparison().isEqualTo(listOfRovers);
    }

    @Test
    @DisplayName("remove rover from listOfRover when given a non-existing rover")
    void testRemoveRover_WithNonExistingRover() {
        Plateau plateau = new Plateau(new PlateauSize(5,5));
        plateau.addRover(rover1);
        plateau.addRover(rover2);

        assertThrows(IllegalArgumentException.class, () -> plateau.removeRover(rover3));;
    }

    private <T> boolean compareRovers(T r1, T r2) {
        // This assumes the Rover class's equals() method correctly compares two Rovers
        return r1.equals(r2);
    }
}