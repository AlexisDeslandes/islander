package game.connection.request;

import commun.Direction;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 03/07/2017.
 */
public class StopDeplacement implements Request {

    private Direction direction;

    public StopDeplacement(Direction direction){
        this.direction = direction;
    }

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("direction",direction);
        json.put("deplacement","stop");
        return json;
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) {
        serverInstanceCommunication.cancelDeplacementThread(direction);
    }

    @Override
    public void actionOnClient(MainWindowModel model) {}
}
