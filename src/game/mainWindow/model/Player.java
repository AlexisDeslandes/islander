package game.mainWindow.model;

import java.awt.*;

/**
 * Created by Desla on 10/07/2017.
 */
public class Player {

    private Point position;

    private int id;

    public Player(int id){
        this.position = new Point();
        this.id = id;
    }

    public Point getPosition() {
        return position;
    }

    public double getX(){
        return position.getX();
    }

    public double getY(){
        return position.getY();
    }

    public int getId() {
        return id;
    }
}
