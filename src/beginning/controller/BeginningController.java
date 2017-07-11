package beginning.controller;

import beginning.vue.BeginningVue;
import commun.Controller;
import game.connection.client.ClientCommunication;
import game.connection.request.player.GetGameState;
import game.connection.request.player.GetIDPlayer;
import game.mainWindow.MainWindowController;
import game.mainWindow.model.MainWindowModel;
import game.mainWindow.MainWindowVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BeginningController implements Controller {

    private BeginningVue vue;

    public BeginningController(BeginningVue vue) {
        this.vue = vue;
    }

    public Scene getScene() {
        return vue;
    }

    public void setBehaviourComponent() {
        vue.getLeave().setOnMouseClicked(event -> ((Stage) vue.getWindow()).close());
        vue.getNewGame().setOnMouseClicked(event -> {
            MainWindowModel model = new MainWindowModel();
            ClientCommunication clientCommunication = new ClientCommunication(model);
            clientCommunication.sendRequest(new GetIDPlayer());
            clientCommunication.sendRequest(new GetGameState());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainWindowController controller = new MainWindowController(model, new MainWindowVue(), clientCommunication);
            controller.setBehaviourMouse();
            controller.setBehaviourScreenElement();

            ((Stage) vue.getWindow()).setScene(controller.getScene());

        });
        vue.getLoadGame().setOnMouseClicked(event -> {
            //todo
        });
        vue.getParam().setOnMouseClicked(event -> {
            //todo create model/vu/controller
        });
    }
}
