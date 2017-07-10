package game.mainWindow;

import commun.Compas;

import java.awt.*;
import java.util.Observable;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowModel extends Observable {

    private Point rectanglePos;

    public MainWindowModel() {
        this.rectanglePos = new Point(0, 0);
    }

    public void move(Compas compas, int value) {
        compas.makeTranslate(rectanglePos, value);
        this.setChanged();
        this.notifyObservers();
    }

    int getRectangleX() {
        return rectanglePos.x;
    }

    int getRectangleY() {
        return rectanglePos.y;
    }
}
