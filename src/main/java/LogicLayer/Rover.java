package LogicLayer;

import InputLayer.CompassDirection;
import InputLayer.Instruction;
import InputLayer.Position;
import com.sun.source.tree.ReturnTree;

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

        if(instruction.equals(Instruction.L) || instruction.equals(Instruction.R)) {
            CompassDirection newFacing = switch (facing) {
                case N -> instruction.equals(Instruction.L) ? W : E;
                case W -> instruction.equals(Instruction.L) ? S : N;
                case S -> instruction.equals(Instruction.L) ? E : W;
                case E -> instruction.equals(Instruction.L) ? N : S;
            };
            getPosition().setFacing(newFacing);
        }
    }
}
