package ija.ija2022.homework1.common;

public interface Maze {
    int numRows();

    int numCols();

    Field getField(int row, int col);

    void setField(int row, int col, Field field);

    void print();
}
