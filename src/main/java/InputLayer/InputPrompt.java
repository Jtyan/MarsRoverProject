package InputLayer;

import LogicLayer.MissionControl;
import LogicLayer.Rover;

import java.util.Scanner;

public class InputPrompt {
    private static Scanner scanner = new Scanner(System.in);
    private static Plateau plateau  = Plateau.getInstance();
    private static Rover rover = new Rover();
    private static MissionControl missionControl = new MissionControl(plateau, rover);

    public static void getInputString() {

        createMapInput();
        deployRoverInput();
        moveRoverInput();

        System.out.println("Thank you for trying it out. Goodbye!");
        scanner.close();

    }

    public static void createMapInput() {

        System.out.println("Welcome to Joel's Mars Rover Simulator!");
        System.out.println("Let us create a map. It must be at least a 2x2 plateau.");
        System.out.print("First, enter the number of rows for the plateau: ");
        String rows = scanner.next();
        System.out.print("Enter the number of columns for the plateau: ");
        String columns = scanner.next();
        plateau.setPlateauSize(rows, columns);
        System.out.println("\nPlateau " + plateau.getRow() + "x" + plateau.getColumn() + " has been created.\n");
        plateau.generateObstaclesRandomPosition();
    }

    public static void deployRoverInput() {
        Boolean isRoverDeployed;

        do {
            System.out.println("\nNow, enter the x and y coordinates of the rover along with its initial facing direction(N,S,E,W). e.g: 2,4,N: ");
            String positionInput = scanner.next();
            isRoverDeployed = missionControl.deployRover(positionInput);
        } while (!isRoverDeployed);

    }

    public static void moveRoverInput() {
        do {
            System.out.println("Enter the instruction to move rover (L for rotate left, R for rotate right, M to move). e.g: LMLMLMLMM: ");
            String instructions = scanner.next();
            System.out.println("Rover initial position: " + rover.getPosition().getX() + "," + rover.getPosition().getY() + "," + rover.getPosition().getFacing() + ".");
            missionControl.moveRover(instructions);
            System.out.println("Would you like to continue? (Y/N): ");
        }while (scanner.next().equalsIgnoreCase("Y"));
    }
}
