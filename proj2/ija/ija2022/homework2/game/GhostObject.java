package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class GhostObject implements CommonMazeObject {

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
            ((PathField)field).remove(this);
            field = field.nextField(dir);
            ((PathField)field).put(this);
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
}
