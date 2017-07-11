package game.connection.request.move;

import commun.Compas;
import game.connection.request.Request;
import game.connection.server.MoveThread;
import game.connection.server.MoveThreadIdentifier;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.model.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Desla on 28/06/2017.
 */
public abstract class Movement implements Request {

    private int value;

    private int id;

    public Movement() {
        this.value = 10;
    }

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("playerID", id);
        object.put("direction", getCompas());
        object.put("deplacement", value);
        return object;
    }

    abstract Compas getCompas();

    @Override
    public void actionOnServers(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        Map.Entry<MoveThreadIdentifier, MoveThread> entry = ServerInstanceCommunication.getMoveThreadMap().entrySet().stream().filter(
                entrySet -> entrySet.getKey().getIdPlayerListening() == serverInstanceCommunication.getID() && entrySet.getKey().getIdPlayerMoving() == id).findAny().get();
        MoveThread moveThread = entry.getValue();
        moveThread.setOos(serverInstanceCommunication.getOos());
        if (moveThread.readyToStart()){
            moveThread.start();
            moveThread.setReadyToStart(false);
        }
    }

    @Override
    public void actionOnClient(MainWindowModel model) {
        model.move(id, getCompas(), value);
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        serverInstanceCommunication.modifyMovementThread(this);
    }

    @Override
    public void actionEndingServers() {
        //ServerInstanceCommunication.getMoveThreadMap().values().forEach(moveThread -> moveThread.setReadyToStart(false));
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
