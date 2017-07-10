package commun;

import game.connection.request.Request;
import game.connection.request.move.*;

import java.awt.*;

/**
 * Created by Desla on 10/07/2017.
 */
public enum Compas {

    NOTHING(0, 0, "N", "S", "E", "W", "NOTHING", "NOTHING", "NOTHING", "NOTHING", new NoMovement()),
    N(0, -1, "N", "S", "NE", "NW", "NOTHING", "N", "N", "N", new NorthMovement()),
    S(0, 1, "N", "S", "SE", "SW", "S", "NOTHING", "S", "S", new SouthMovement()),
    E(1, 0, "NE", "SE", "E", "W", "E", "E", "NOTHING", "E", new EastMovement()),
    W(-1, 0, "NW", "SW", "E", "W", "W", "W", "W", "NOTHING", new WestMovement()),
    NE(1, -1, "NE", "SE", "NE", "NW", "E", "NE", "N", "NE", new NorthEastMovement()),
    NW(-1, -1, "NW", "SW", "NE", "NW", "W", "NW", "NW", "N", new NorthWestMovement()),
    SE(1, 1, "NE", "SE", "SE", "SW", "SE", "E", "S", "SE", new SouthEastMovement()),
    SW(-1, 1, "NW", "SW", "SE", "SW", "SW", "W", "SW", "S", new SouthWestMovement());

    private String northPress, southPress, eastPress, westPress, northReleas, southReleas, eastRelease, westRelease;

    private Request request;

    private int x, y;

    Compas(int x, int y, String northPress, String southPress, String eastPress, String westPress,
           String northReleas, String southRelease, String eastRelease, String westRelease, Request request) {
        this.x = x;
        this.y = y;
        this.northPress = northPress;
        this.southPress = southPress;
        this.eastPress = eastPress;
        this.westPress = westPress;
        this.northReleas = northReleas;
        this.southReleas = southRelease;
        this.eastRelease = eastRelease;
        this.westRelease = westRelease;
        this.request = request;
    }

    public Compas goToNorth(){
        return Compas.valueOf(this.northPress);
    }

    public Compas goToSouth(){
        return Compas.valueOf(this.southPress);
    }

    public Compas goToEast(){
        return Compas.valueOf(this.eastPress);
    }

    public Compas goToWest(){
        return Compas.valueOf(this.westPress);
    }

    public Compas cancelNorth(){
        return Compas.valueOf(this.northReleas);
    }

    public Compas cancelSouth(){
        return Compas.valueOf(this.southReleas);
    }

    public Compas cancelWest(){
        return Compas.valueOf(this.westRelease);
    }

    public Compas cancelEast(){
        return Compas.valueOf(this.eastRelease);
    }

    public void makeTranslate(Point rectanglePos, int value) {
        rectanglePos.translate(value * x, value * y);
    }

    public Request getRequest(){
        return this.request;
    }
}
