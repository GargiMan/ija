package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.Observable;

public class PacmanObject implements CommonMazeObject, Observable.Observer  {

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
            field.removeObserver(this);
            field = field.nextField(dir);
            field.addObserver(this);
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

    @Override
    public void update(Observable observable) {
        if (((PathField)observable).hasGhost()) {
            hit();
        }
    }
}
