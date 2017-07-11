package game.connection.request.player;

import game.connection.request.Request;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.model.MainWindowModel;
import game.mainWindow.model.Player;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 10/07/2017.
 */
public class InformNewPlayer implements Request {

    private int id;

    public InformNewPlayer(int id){
        this.id = id;
    }

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newComer",id);
        return jsonObject;
    }

    @Override
    public void actionOnServers(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        //nothing to do.
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) {
        //nothing to do.
    }

    @Override
    public void actionOnClient(MainWindowModel model) {
        model.addPlayer(id, new Player());
    }

    @Override
    public void actionEndingServers() {
        //nothing to do.
    }
}
