package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;
import ija.ija2022.homework2.tool.common.Observable;

import java.util.List;

public class MazeConfigure {

    public static final int BORDER_SIZE = 1;
    private int current_row = BORDER_SIZE;
    private boolean valid = true;
    private boolean reading = false;
    private boolean spawn_set = false;
    private Maze maze;

    public void startReading(int rows, int cols) {

        if (reading) return;

        reading = true;

        maze = new Maze(rows, cols);
    }

    public boolean processLine(String line) {

        if (!valid || !reading) return false;

        if (line.length() != maze.numCols()-2*BORDER_SIZE || current_row == maze.numRows()-BORDER_SIZE) return (valid = false);

        char[] char_line = line.toCharArray();

        for (int current_col = BORDER_SIZE; current_col < maze.numCols()-BORDER_SIZE; current_col++) {

            CommonField current_field;

            switch (char_line[current_col-BORDER_SIZE]) {
                case 'S' -> {
                    if (spawn_set) {
                        return (valid = false);
                    } else {
                        spawn_set = true;
                    }
                    current_field = new PathField(current_row, current_col);
                    ((PathField)current_field).put(new PacmanObject(current_field));
                }
                case 'G' -> {
                    current_field = new PathField(current_row, current_col);
                    GhostObject ghost = new GhostObject(current_field);
                    ((PathField)current_field).put(ghost);
                    maze.addGhost(ghost);
                }
                case '.' -> {
                    current_field = new PathField(current_row, current_col);
                }
                case 'X' -> {
                    current_field = new WallField(current_row, current_col);
                }
                default -> {
                    return (valid = false);
                }
            }

            maze.setField(current_row, current_col, current_field);
        }

        current_row++;

        return true;
    }

    public boolean stopReading() {

        valid = valid && reading && spawn_set && current_row == maze.numRows()-BORDER_SIZE;

        reading = false;

        return valid;
    }

    public CommonMaze createMaze() {

        valid = valid && !reading && spawn_set && current_row == maze.numRows()-BORDER_SIZE;

        if (!valid)
            return null;

        //set borders
        for (int i = 0; i < BORDER_SIZE; i++) {

            int col_L = i;
            int col_R = maze.numCols() - BORDER_SIZE - i;
            int row_U = i;
            int row_D = maze.numRows() - BORDER_SIZE - i;

            for (int row = 0; row <= row_D; row++) {
                maze.setField(row, col_L, new WallField(row, col_L));
                maze.setField(row, col_R, new WallField(row, col_R));
            }

            for (int col = BORDER_SIZE; col < col_R; col++) {
                maze.setField(row_U, col, new WallField(row_U, col));
                maze.setField(row_D, col, new WallField(row_D, col));
            }
        }
        
        return maze;
    }
}
