package game.connection.request.player;

import game.connection.request.Request;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.model.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 13/07/2017.
 */
public class ArrowMovementRequest implements Request {


    @Override
    public JSONObject getJSON() throws JSONException {
        return null;
    }

    @Override
    public void actionOnServers(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {

    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {

    }

    @Override
    public void actionOnClient(MainWindowModel model) {

    }

    @Override
    public void actionEndingServers() {

    }
}
