package game.menu.controller;

import game.connection.ServerCommunication;
import game.menu.model.MenuModel;
import game.menu.vue.MenuVue;

/**
 * Created by Desla on 27/06/2017.
 */
public class MenuController {

    private ServerCommunication serverCommunication;

    private MenuVue vue;

    private MenuModel model;

    public MenuController(MenuModel menuModel, MenuVue menuVue, ServerCommunication serverCommunication) {
        this.serverCommunication = serverCommunication;
        this.vue = menuVue;
        this.model = menuModel;
    }

    public void setBehaviourKeyBoard() {
    }

    public void setBehaviourScreenElement() {
    }

    public MenuVue getVue() {
        return vue;
    }

    public void setBehaviourMouse() {
        vue.getContenu().setOnMouseClicked(event -> System.out.println("Appuis sur le texte !!!"));
    }
}
