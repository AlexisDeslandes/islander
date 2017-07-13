package game.mainWindow.model;

import java.awt.*;

/**
 * Created by Desla on 13/07/2017.
 */
public class Arrow {

    private Point position;

    private int id;

    Arrow(Point position, int id){
        this.position = position;
        this.id = id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
