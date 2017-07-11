package game.connection.request.player;

import game.connection.request.Request;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.model.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Desla on 11/07/2017.
 */
public class GetGameState implements Request {

    private int nbPlayer;

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("get","gameState");
        return jsonObject;
    }

    @Override
    public void actionOnServers(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        //nothing to do.
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        serverInstanceCommunication.askGameState(this);
    }

    @Override
    public void actionOnClient(MainWindowModel model) {
        model.addPlayers(nbPlayer);
    }

    @Override
    public void actionEndingServers() {
        //nothing to do.
    }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }
}
