package game.connection.request.player;

import game.connection.request.Request;
import game.connection.server.ServerInstanceCommunication;
import game.mainWindow.model.MainWindowModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;

/**
 * Created by Desla on 13/07/2017.
 */
public class SendArrowRequest implements Request {

    private static int idCount = 0;

    private Point playerPosition;

    private double angle;

    private int id;

    public SendArrowRequest(Point playerPosition, double angle) {
        this.playerPosition = playerPosition;
        this.angle = angle;
        this.id = idCount++;
    }

    @Override
    public JSONObject getJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("arrowSend",playerPosition);
        jsonObject.put("angle",angle);
        return jsonObject;
    }

    @Override
    public void actionOnServers(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        serverInstanceCommunication.sendArrow(this);
    }

    @Override
    public void actionOnServer(ServerInstanceCommunication serverInstanceCommunication) throws JSONException {
        //nothing to do.
    }

    @Override
    public void actionOnClient(MainWindowModel model) {
        model.addArrow(this);
    }

    @Override
    public void actionEndingServers() {

    }

    public int getId() {
        return id;
    }

    public Point getPlayerPosition() {
        return playerPosition;
    }
}
