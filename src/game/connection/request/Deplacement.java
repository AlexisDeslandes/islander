package game.connection.request;

import commun.Direction;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 28/06/2017.
 */
public class Deplacement implements Request {

    private int value;

    private Direction direction;

    public Deplacement(Direction direction, int value) {
        this.value = value;
        this.direction = direction;
    }

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("direction", direction);
        object.put("deplacement", value);
        return object;
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        serverInstanceCommunication.instantiatedDeplacementThread(this);
    }

    @Override
    public void actionOnClient(MainWindowModel model) {
        model.move(direction, value);
    }

}
