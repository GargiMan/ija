package ija.ija2022.homework1.game;

import ija.ija2022.homework1.common.Field;
import ija.ija2022.homework1.common.Maze;

public class MazeConfigure {

    private static final int BORDER_SIZE = 1;
    private int current_row = BORDER_SIZE;
    private boolean valid = true;
    private boolean reading = false;
    private boolean spawn_set = false;
    private Maze maze;

    public void startReading(int rows, int cols) {

        if (reading) return;

        reading = true;

        maze = new Maze() {
            private final int numRows = rows + 2 * BORDER_SIZE;
            private final int numCols = cols + 2 * BORDER_SIZE;
            private final Field[][] fields = new Field[numRows][numCols];

            @Override
            public int numRows() {
                return numRows;
            }

            @Override
            public int numCols() {
                return numCols;
            }

            @Override
            public Field getField(int row, int col) {

                if (row < BORDER_SIZE - 1 || col < BORDER_SIZE - 1 || row > BORDER_SIZE + rows || col > BORDER_SIZE + cols)
                    return null;

                return fields[row][col];
            }

            public void setField(int row, int col, Field field) {
                field.setMaze(this);
                fields[row][col] = field;
            }
        };
    }

    public boolean processLine(String line) {

        if (!valid || !reading) return false;

        if (line.length() != maze.numCols()-2*BORDER_SIZE || current_row == maze.numRows()-BORDER_SIZE) return (valid = false);

        char[] char_line = line.toCharArray();

        for (int current_col = BORDER_SIZE; current_col < maze.numCols()-BORDER_SIZE; current_col++) {

            Field current_field;

            switch (char_line[current_col-BORDER_SIZE]) {
                case 'S' -> {
                    if (spawn_set) {
                        return (valid = false);
                    } else {
                        spawn_set = true;
                    }
                    current_field = new PathField(current_row, current_col);
                    current_field.put(new PacmanObject(current_field));
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

    public Maze createMaze() {

        valid = valid && !reading && spawn_set && current_row == maze.numRows()-BORDER_SIZE;

        if (!valid) return null;

        int max_col = maze.numCols() - BORDER_SIZE;
        int max_row = maze.numRows() - BORDER_SIZE;

        for (int row = 0; row <= max_row; row++) {
            maze.setField(row, 0, new WallField(row, 0));
            maze.setField(row, max_col, new WallField(row, max_col));
        }
        for (int col = BORDER_SIZE; col < max_col; col++) {
            maze.setField(0, col, new WallField(0, col));
            maze.setField(max_row, col, new WallField(max_row, col));
        }

        return maze;
    }
}
