package game.mainWindow;

import commun.Controller;
import commun.Direction;
import game.connection.client.ClientCommunication;
import game.connection.request.Deplacement;
import game.connection.request.StopDeplacement;
import javafx.scene.Scene;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowController implements Controller {

    private MainWindowModel model;

    private MainWindowVue vue;

    private ClientCommunication clientCommunication;

    public MainWindowController(MainWindowModel model, MainWindowVue vue, ClientCommunication clientCommunication) {
        this.model = model;
        this.vue = vue;
        this.clientCommunication = clientCommunication;
        this.model.addObserver(vue);
    }

    public void setBehaviourKeyBoard() {
        vue.setOnKeyPressed(event -> {
            switch (event.getCode().name()) {
                case "Q":
                    clientCommunication.sendRequest(new Deplacement(Direction.WEST, 10));
                    break;
                case "D":
                    clientCommunication.sendRequest(new Deplacement(Direction.EAST, 10));
                    break;
                default:
                    break;
            }
        });
        vue.setOnKeyReleased(event -> {
            switch (event.getCode().name()) {
                case "Q":
                    clientCommunication.sendRequest(new StopDeplacement(Direction.WEST));
                    break;
                case "D":
                    clientCommunication.sendRequest(new StopDeplacement(Direction.EAST));
                    break;
                default:
                    break;
            }
        });
    }

    public void setBehaviourMouse() {

    }

    public void setBehaviourScreenElement() {

    }

    @Override
    public Scene getScene() {
        return vue;
    }

    public ClientCommunication getClientCommunication() {
        return clientCommunication;
    }
}
