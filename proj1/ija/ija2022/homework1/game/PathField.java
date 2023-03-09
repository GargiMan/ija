package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.Maze;
import ija.ija2022.homework1.common.MazeObject;

public class PathField implements Field {

    private final int row;
    private final int col;
    private Maze maze;
    private MazeObject mazeObject = null;

    public PathField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public Field nextField(Direction dirs) {
        return switch (dirs) {
            case U -> maze.getField(row - 1, col);
            case D -> maze.getField(row + 1, col);
            case L -> maze.getField(row, col - 1);
            case R -> maze.getField(row, col + 1);
        };
    }

    @Override
    public boolean put(MazeObject object) {
        if (!isEmpty()) {
            return false;
        }

        mazeObject = object;
        return true;
    }

    @Override
    public boolean remove(MazeObject object) {

        if (isEmpty() || mazeObject != object) return false;

        mazeObject = null;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return mazeObject == null;
    }

    @Override
    public MazeObject get() {
        return mazeObject;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PathField) {
            return ((PathField) obj).row == this.row && ((PathField) obj).col == this.col;
        }
        return false;
    }
}
