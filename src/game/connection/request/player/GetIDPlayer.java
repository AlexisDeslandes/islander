package game.connection.request.player;

import game.connection.request.Request;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.model.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 10/07/2017.
 */
public class GetIDPlayer implements Request {

    private int idPlayer;

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ask","ID");
        return jsonObject;
    }

    @Override
    public void actionOnServers(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        serverInstanceCommunication.informNewPlayer(this);
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) {
        serverInstanceCommunication.attributeID(this);
    }

    @Override
    public void actionOnClient(MainWindowModel model) {
        model.setPlayerID(idPlayer);
    }

    @Override
    public void actionEndingServers() {
        //nothing to do.
    }

    public void setIdPlayer(int idPlayer){
        this.idPlayer = idPlayer;
    }

    public int getIdPlayer() {
        return idPlayer;
    }
}
