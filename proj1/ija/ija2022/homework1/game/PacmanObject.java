package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.MazeObject;

public class PacmanObject implements MazeObject {

    private Field field;

    public PacmanObject(Field field) {
        this.field = field;
    }

    @Override
    public boolean canMove(Field.Direction dir) {
        return field.nextField(dir).canMove();
    }

    @Override
    public boolean move(Field.Direction dir) {
        if (canMove(dir)) {
            field.remove(this);
            field = field.nextField(dir);
            field.put(this);
            return true;
        }
        return false;
    }
}
