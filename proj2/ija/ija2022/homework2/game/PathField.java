package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

public class PathField implements CommonField {

    private final int row;
    private final int col;
    private CommonMaze maze;
    private CommonMazeObject mazeObject = null;

    public PathField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    @Override
    public CommonField nextField(Direction dirs) {
        return maze.getField(row + dirs.deltaRow(), col + dirs.deltaCol());
    }

    public boolean put(CommonMazeObject object) {
        if (!isEmpty()) {
            return false;
        }

        mazeObject = object;
        return true;
    }

    public boolean remove(CommonMazeObject object) {

        if (isEmpty() || mazeObject != object) return false;

        mazeObject = null;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return mazeObject == null;
    }

    @Override
    public CommonMazeObject get() {
        return mazeObject;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean contains(CommonMazeObject commonMazeObject) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PathField) {
            return ((PathField) obj).row == this.row && ((PathField) obj).col == this.col;
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
