package org.example;

import InputLayer.InputParser;
import InputLayer.Instruction;
import InputLayer.PlateauSize;
import InputLayer.Position;
import LogicLayer.MissionControl;
import LogicLayer.Rover;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String mapInput = "5, 5";
        PlateauSize.getInstance().setColumn(5);
        PlateauSize.getInstance().setRow(5);
        MissionControl plateau = new MissionControl(PlateauSize.getInstance());

        InputParser inputParser = new InputParser();

        String positionInput = " 3 3, N";
        Position validStartingPosition = inputParser.parseInputToPosition(positionInput);
        Rover rover1 = new Rover(validStartingPosition);
        System.out.println("Initial Position where X: " + rover1.getPosition().getX() + ", Y: " + rover1.getPosition().getY() + ", Faces " + rover1.getPosition().getFacing());
        plateau.addRover(rover1);

        String movingInput = "LLLLLMMRMMLLLMR"; // Move the rover
        ArrayList<Instruction> validListOfInstructions = inputParser.parseInputToInstruction(movingInput);
        rover1.setNewPosition(validListOfInstructions);
        System.out.println("New Position where X: " + rover1.getPosition().getX() + ", Y: " + rover1.getPosition().getY() + ", Faces " + rover1.getPosition().getFacing());


    /*To-do
      add parseInputToMapGrid
      what to do with rover moving out of the map
      what to do with rover colliding with another rover or obstacles
      make sure if MissionControl class is not too big /complicated
      linking input layer to logic layer
      tests for everything!
    */

    }
}