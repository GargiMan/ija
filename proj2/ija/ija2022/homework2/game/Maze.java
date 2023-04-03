package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

import java.util.ArrayList;
import java.util.List;

public class Maze implements CommonMaze {

    private final int numRows;
    private final int numCols;
    private final CommonField[][] fields;
    private final List<CommonMazeObject> ghosts = new ArrayList<>();

    public Maze(int rows, int cols) {
        numRows = rows + 2 * MazeConfigure.BORDER_SIZE;
        numCols = cols + 2 * MazeConfigure.BORDER_SIZE;
        fields = new CommonField[numRows][numCols];
    }

    @Override
    public CommonField getField(int row, int col) {
        if (row < MazeConfigure.BORDER_SIZE - 1 || col < MazeConfigure.BORDER_SIZE - 1 || row > MazeConfigure.BORDER_SIZE + numRows || col > MazeConfigure.BORDER_SIZE + numCols)
            return null;

        return fields[row][col];
    }

    public void setField(int row, int col, CommonField field) {
        if (field instanceof PathField) {
            ((PathField)field).setMaze(this);
        }
        fields[row][col] = field;
    }

    @Override
    public int numRows() {
        return numRows;
    }

    @Override
    public int numCols() {
        return numCols;
    }

    @Override
    public List<CommonMazeObject> ghosts() {
        return new ArrayList<>(ghosts);
    }

    public void addGhost(GhostObject ghost) {
        ghosts.add(ghost);
    }
}
