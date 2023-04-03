package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class WallField implements CommonField {

    private final int row;
    private final int col;

    public WallField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public CommonField nextField(Direction dirs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public CommonMazeObject get() {
        return null;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean contains(CommonMazeObject commonMazeObject) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WallField) {
            return ((WallField) obj).row == this.row && ((WallField) obj).col == this.col;
        }
        return false;
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
