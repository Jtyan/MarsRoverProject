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
        System.out.println("X: " + rover1.getPosition().getX() + ", Y: " + rover1.getPosition().getY() + ", Faces " + rover1.getPosition().getFacing());
        plateau.addRover(rover1);
        String movingInput = "LLLRMMRMLLLMR";
        ArrayList<Instruction> validListOfInstructions = inputParser.parseInputToInstruction(movingInput);
        rover1.setNewPosition(validListOfInstructions);
        System.out.println("X: " + rover1.getPosition().getX() + ", Y: " + rover1.getPosition().getY() + ", Faces " + rover1.getPosition().getFacing());




//        System.out.println(result);
////        System.out.println(IllegalArgumentException.class);
//        System.out.println(inputParser.parseInputToPosition(input));

    }
}