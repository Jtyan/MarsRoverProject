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
        ArrayList<Rover> listOfRovers = new ArrayList<>(){{
            add(new Rover(new Position(2,4, CompassDirection.N)));
            add(new Rover(new Position(0,1, CompassDirection.S)));
            add(new Rover(new Position(2,3, CompassDirection.E)));
            add(new Rover(new Position(3,1, CompassDirection.W)));
        }};

        plateau.addRover(new Rover(new Position(2,4,CompassDirection.N)));
        plateau.addRover(new Rover(new Position(0,1, CompassDirection.S)));
        plateau.addRover(new Rover(new Position(2,3, CompassDirection.E)));
        plateau.addRover(new Rover(new Position(3,1, CompassDirection.W)));


        System.out.println(plateau.getListOfRovers().toArray().toString());
        System.out.println(listOfRovers.toArray().toString());

//        assertEquals(plateau.getListOfRovers(), listOfRovers);

//        assertArrayEquals(plateau.getListOfRovers().toArray(), listOfRovers.toArray());
//        assertTrue(plateau.getListOfRovers().toArray().equals(listOfRovers.toArray()));
        assertThat(listOfRovers).usingRecursiveComparison().isEqualTo(plateau.getListOfRovers());
    }

    @Test
    @DisplayName("throw an exception when adding invalid position")
    public void testAddRover_WithInvalidPosition() {
        plateau.addRover(new Rover(new Position(2,4,CompassDirection.N)));
        plateau.addRover(new Rover(new Position(0,1, CompassDirection.S)));


        assertThrows(IllegalArgumentException.class, () -> plateau.addRover(new Rover(new Position(0,1, CompassDirection.S))));
    }

    @Test
    void testRemoveRover() {

    }
}