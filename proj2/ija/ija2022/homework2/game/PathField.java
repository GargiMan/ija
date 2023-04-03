package ija.ija2022.homework2.game;

import ija.ija2022.homework2.tool.common.CommonField;
import ija.ija2022.homework2.tool.common.CommonMaze;
import ija.ija2022.homework2.tool.common.CommonMazeObject;

import java.util.ArrayList;
import java.util.List;

public class PathField implements CommonField {

    private final int row;
    private final int col;
    private CommonMaze maze;
    private CommonMazeObject mazeObject = null;
    private final List<Observer> observers = new ArrayList<>();

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

    @Override
    public boolean isEmpty() {
        return mazeObject == null;
    }

    public boolean hasGhost() {
        for (Observer observer : observers) {
            if (observer instanceof GhostObject) return true;
        }
        return false;
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
        return observers.contains((Observer) commonMazeObject);
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
        if (observer instanceof PacmanObject) {
            mazeObject = (PacmanObject)observer;
        }
        observers.add(observer);
        notifyObservers(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!isEmpty() && observer instanceof PacmanObject && mazeObject.equals(observer)) {
            mazeObject = null;
        }
        observers.remove(observer);
        notifyObservers(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void notifyObservers(Observer ignore) {
        for (Observer observer : observers) {
            if (observer.equals(ignore)) continue;
            observer.update(this);
        }
    }
}
