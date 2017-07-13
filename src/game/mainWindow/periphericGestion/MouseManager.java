package game.mainWindow.periphericGestion;

import game.connection.client.ClientCommunication;
import game.connection.request.player.SendArrowRequest;
import game.mainWindow.MainWindowVue;
import game.mainWindow.model.MainWindowModel;
import game.mainWindow.model.Player;

import java.awt.*;

/**
 * Created by Desla on 13/07/2017.
 */
public class MouseManager {

    private ClientCommunication clientCommunication;

    private MainWindowModel model;

    public MouseManager(ClientCommunication clientCommunication, MainWindowVue vue, MainWindowModel model) {
        this.clientCommunication = clientCommunication;
        this.model = model;
        this.setBehaviour(vue);
    }

    private void setBehaviour(MainWindowVue vue) {
        vue.setOnMousePressed(event -> {
            Player player = model.getPlayer(model.getPlayerId());
            Point playerPosition = new Point(player.getPosition());
            Point clickPosition = new Point((int) event.getX(), (int) event.getY());
            clientCommunication.sendRequest(new SendArrowRequest(playerPosition, getAngle(playerPosition, clickPosition)));
        });
    }

    private double getAngle(Point playerPosition, Point clickPosition) {
        double x = Math.abs(playerPosition.x - clickPosition.x);
        double y = Math.abs(playerPosition.y - clickPosition.y);
        double hypothenuse = Math.sqrt(x * x + y * y);
        return Math.acos(x / hypothenuse);
    }
}
