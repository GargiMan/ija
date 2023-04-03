package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class PacmanObject implements CommonMazeObject {

    private int lives = 3;
    private CommonField field;

    public PacmanObject(CommonField field) {
        this.field = field;
    }

    @Override
    public boolean canMove(CommonField.Direction dir) {
        return field.nextField(dir).canMove();
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        if (canMove(dir)) {
            ((PathField)field).remove(this);
            field = field.nextField(dir);
            ((PathField)field).put(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean isPacman() {
        return true;
    }

    @Override
    public CommonField getField() {
        return field;
    }

    @Override
    public int getLives() {
        return lives;
    }

    public void hit() {
        lives--;
    }

}
