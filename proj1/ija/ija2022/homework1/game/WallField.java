package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.Maze;
import ija.ija2022.homework1.common.MazeObject;
import java.lang.UnsupportedOperationException;

public class WallField implements Field {

    private final int row;
    private final int col;

    public WallField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void setMaze(Maze maze) {
    }

    @Override
    public Field nextField(Direction dirs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean put(MazeObject object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(MazeObject object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public MazeObject get() {
        return null;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WallField) {
            return ((WallField) obj).row == this.row && ((WallField) obj).col == this.col;
        }
        return false;
    }
}
