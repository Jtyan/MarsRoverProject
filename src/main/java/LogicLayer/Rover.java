package LogicLayer;

import InputLayer.CompassDirection;
import InputLayer.Instruction;
import InputLayer.Position;
import com.sun.source.tree.ReturnTree;

import java.util.ArrayList;

import static InputLayer.CompassDirection.*;

public class Rover {
    private Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void rotate(Instruction instruction) {
        CompassDirection facing = getPosition().getFacing();
        if (instruction == null) {
            throw new IllegalArgumentException("Instruction to rotate is not found!");
        }

        CompassDirection newFacing = switch (facing) {
            case N -> instruction.equals(Instruction.L) ? W : E;
            case W -> instruction.equals(Instruction.L) ? S : N;
            case S -> instruction.equals(Instruction.L) ? E : W;
            case E -> instruction.equals(Instruction.L) ? N : S;
        };
        setPosition(new Position(getPosition().getX(), getPosition().getY(), newFacing));
    }

    public void move(Instruction instruction) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        CompassDirection facing = getPosition().getFacing();

        if (instruction == null) {
            throw new IllegalArgumentException("Instruction to move is not found!");
        }

        switch (facing) {
            case N -> setPosition(new Position(x, y + 1, facing));
            case W -> setPosition(new Position(x - 1, y, facing));
            case S -> setPosition(new Position(x, y - 1, facing));
            case E -> setPosition(new Position(x + 1, y, facing));
        }
    }

    public Position setNewPosition(ArrayList<Instruction> ListOfInstructions) {
        int x = getPosition().getX();
        int y = getPosition().getY();

        for (Instruction instruction: ListOfInstructions) {
            if(instruction.equals(Instruction.L) || instruction.equals(Instruction.R)) {
                rotate(instruction);
            } else if (instruction.equals(Instruction.M)) {
                move(instruction);
            }
        }
        return getPosition();
    }
}
