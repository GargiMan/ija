package ija.ija2022.homework1.common;

public interface Field {

    enum Direction {U, D, L, R}
    void setMaze(Maze maze);
    Field nextField(Direction dirs);
    boolean put(MazeObject obj);
    boolean remove(MazeObject obj);
    boolean isEmpty();
    MazeObject get();
    boolean canMove();
}
