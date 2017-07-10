package game.connection.request.move;

import commun.Compas;

/**
 * Created by Desla on 10/07/2017.
 */
public class NoMovement extends Movement {

    @Override
    Compas getCompas() {
        return Compas.NOTHING;
    }
}
