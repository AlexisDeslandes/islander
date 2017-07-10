package game.mainWindow;

import commun.Controller;
import game.connection.client.ClientCommunication;
import game.mainWindow.keyGestion.KeyManager;
import javafx.scene.Scene;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowController implements Controller {

    private MainWindowModel model;

    private MainWindowVue vue;

    private ClientCommunication clientCommunication;

    private KeyManager keyManager;

    public MainWindowController(MainWindowModel model, MainWindowVue vue, ClientCommunication clientCommunication) {
        this.model = model;
        this.vue = vue;
        this.clientCommunication = clientCommunication;
        this.model.addObserver(vue);
        this.keyManager = new KeyManager(clientCommunication,vue);
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
