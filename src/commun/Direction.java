package commun;

import java.awt.*;

/**
 * Created by Desla on 04/07/2017.
 */
public enum Direction {

    NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0);

    private int x;

    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void makeTranslate(Point rectanglePos, int value) {
        rectanglePos.translate(value * x, value * y);
    }
}
