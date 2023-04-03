package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.Observable;

public class GhostObject implements CommonMazeObject, Observable.Observer {

    private CommonField field;

    public GhostObject(CommonField field) {
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
    public CommonField getField() {
        return field;
    }

    @Override
    public int getLives() {
        return 0;
    }


    @Override
    public void update(Observable observable) {
         CommonMazeObject mazeObject = ((PathField)observable).get();
         if (mazeObject instanceof PacmanObject) {
            ((PacmanObject) mazeObject).hit();
         }
    }
}
