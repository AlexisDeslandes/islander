package game.connection.request.move;

import commun.Compas;

/**
 * Created by Desla on 10/07/2017.
 */
public class NorthWestMovement extends Movement {


    @Override
    Compas getCompas() {
        return Compas.NW;
    }
}
