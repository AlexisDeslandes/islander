package beginning.controller;

import beginning.vue.BeginningVue;
import game.connection.ServerCommunication;
import game.mainWindow.controller.MainWindowController;
import game.mainWindow.model.MainWindowModel;
import game.mainWindow.vue.MainWindowVue;
import game.menu.controller.MenuController;
import game.menu.model.MenuModel;
import game.menu.vue.MenuVue;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BeginningController {

    private BeginningVue vue;

    public BeginningController(BeginningVue vue) {
        this.vue = vue;
    }

    public Scene getScene() {
        return new Scene(vue.getContenu());
    }

    public void setBehaviourComponent() {
        vue.getLeave().setOnMouseClicked(event -> ((Stage) vue.getContenu().getScene().getWindow()).close());
        vue.getNewGame().setOnMouseClicked(event -> {

            ServerCommunication serverCommunication = new ServerCommunication();

            MainWindowController controller = new MainWindowController(new MainWindowModel(), new MainWindowVue(), serverCommunication);
            controller.setBehaviourKeyBoard();
            controller.setBehaviourMouse();
            controller.setBehaviourScreenElement();

            MenuController menuController = new MenuController(new MenuModel(), new MenuVue(), serverCommunication);
            menuController.setBehaviourKeyBoard();
            menuController.setBehaviourMouse();
            menuController.setBehaviourScreenElement();

            VBox vBox = new VBox(controller.getVue().getContenu(), menuController.getVue().getContenu());
            ((Stage) vue.getContenu().getScene().getWindow()).setScene(new Scene(vBox));

        });
        vue.getLoadGame().setOnMouseClicked(event -> {
            //todo
        });
        vue.getParam().setOnMouseClicked(event -> {
            //todo create model/vu/controller
        });
    }
}
