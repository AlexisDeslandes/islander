package game.connection.request.move;

import commun.Compas;
import game.connection.request.Request;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 28/06/2017.
 */
public abstract class Movement implements Request {

    private int value;

    public Movement() {
        this.value = 10;
    }

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("direction", getCompas());
        object.put("deplacement", value);
        return object;
    }

    abstract Compas getCompas();

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        serverInstanceCommunication.modifyMovementThread(this);
    }

    @Override
    public void actionOnClient(MainWindowModel model) {
        model.move(getCompas(), value);
    }

}
