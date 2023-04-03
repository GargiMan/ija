package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.Observable;

public class GhostObject implements CommonMazeObject, Observable.Observer {

    private CommonField field;

    public GhostObject(CommonField field) {
        this.field = field;
        field.addObserver(this);
    }

    @Override
    public boolean canMove(CommonField.Direction dir) {
        return field.nextField(dir).canMove();
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        if (canMove(dir)) {
            ((PathField) field).remove(this);
            field = field.nextField(dir);
            ((PathField) field).put(this);
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
    public void update(Observable o) {
        if (field.get() == null) {
            // Field is empty, do something
        } else if (field.get() instanceof PacmanObject) {
            // Pacman is on this field, do something
            ((PacmanObject) field.get()).hit();
        } else if (field.get() instanceof GhostObject) {
            // Ghost is on this field, do something
        }
        // Update visualization

    }
}
