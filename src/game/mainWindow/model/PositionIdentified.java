package game.mainWindow.model;

import java.awt.*;

/**
 * Created by Desla on 12/07/2017.
 */
public class PositionIdentified {

    private int id;

    private Point position;

    PositionIdentified(int id, Point position) {
        this.id = id;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }
}
