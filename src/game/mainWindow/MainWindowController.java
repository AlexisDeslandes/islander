package game.mainWindow;

import commun.Controller;
import game.connection.client.ClientCommunication;
import game.connection.request.player.GetGameState;
import game.connection.request.player.GetIDPlayer;
import game.mainWindow.periphericGestion.KeyManager;
import game.mainWindow.model.MainWindowModel;
import game.mainWindow.periphericGestion.MouseManager;
import javafx.scene.Scene;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowController implements Controller {

    private MainWindowVue vue;

    public MainWindowController() {
        MainWindowModel model = new MainWindowModel();
        this.vue = new MainWindowVue();
        model.addObserver(vue);
        ClientCommunication clientCommunication = new ClientCommunication(model);
        clientCommunication.sendRequest(new GetIDPlayer());
        clientCommunication.sendRequest(new GetGameState());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new KeyManager(clientCommunication, vue, model.getPlayerId());
        new MouseManager(clientCommunication, vue, model);
    }


    @Override
    public Scene getScene() {
        return vue;
    }
}
