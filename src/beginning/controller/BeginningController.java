package beginning.controller;

import beginning.vue.BeginningVue;
import commun.Controller;
import game.mainWindow.MainWindowController;
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
            MainWindowController controller = new MainWindowController();
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
