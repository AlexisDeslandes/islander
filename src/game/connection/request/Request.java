package game.connection.request;

import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Desla on 28/06/2017.
 */
public interface Request extends Serializable {

    JSONObject getJSON() throws JSONException;

    void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) throws JSONException;

    void actionOnClient(MainWindowModel model);
}
