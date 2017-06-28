package game.menu.vue;

import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * Created by Desla on 27/06/2017.
 */
public class MenuVue {

    private Label something;

    public MenuVue(){
        this.something = new Label("Bonjour je ne sert à rien !");
    }

    public Parent getContenu(){
        return something;
    }

}
