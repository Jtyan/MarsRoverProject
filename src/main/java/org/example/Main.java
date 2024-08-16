package org.example;

import InputLayer.InputParser;
import InputLayer.PlateauSize;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlateauSize plateau = new PlateauSize(5, 5);
        InputParser inputParser = new InputParser();
        String input = " 1 3 N";
//
        var result = inputParser.parseInputToPosition(input);
        System.out.println(result);
//        System.out.println(IllegalArgumentException.class);
        System.out.println(inputParser.parseInputToPosition(input));

    }
}