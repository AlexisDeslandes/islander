package game.mainWindow.model;

import java.util.Observable;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowModel extends Observable {

    private int rectangleX;

    public MainWindowModel() {
        this.rectangleX = 0;
    }

    public void incrX(int x) {
        this.rectangleX += x;
        this.setChanged();
        this.notifyObservers();
    }

    public int getRectangleX() {
        return rectangleX;
    }
}
