package game.mainWindow.controller;

import game.connection.ServerCommunication;
import game.mainWindow.model.MainWindowModel;
import game.mainWindow.vue.MainWindowVue;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowController {

    private MainWindowModel model;

    private MainWindowVue vue;

    private ServerCommunication serverCommunication;

    public MainWindowController(MainWindowModel model, MainWindowVue vue, ServerCommunication serverCommunication) {
        this.model = model;
        this.vue = vue;
        this.serverCommunication = serverCommunication;
        this.model.addObserver(vue);
    }


    public void setBehaviourKeyBoard() {

    }

    public void setBehaviourMouse() {
        vue.getContenu().getChildrenUnmodifiable().get(0).setOnMouseClicked(event -> {
            model.incrX(50);
        });
    }

    public void setBehaviourScreenElement() {

    }

    public MainWindowVue getVue() {
        return vue;
    }
}
