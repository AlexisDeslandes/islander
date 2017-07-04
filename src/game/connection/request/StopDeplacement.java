package game.connection.request;

import game.connection.server.ServerInstanceCommunication;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 03/07/2017.
 */
public class StopDeplacement implements Request {
    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("stop","deplacement");
        return json;
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) {
        serverInstanceCommunication.cancelDeplacementThread();
    }
}
