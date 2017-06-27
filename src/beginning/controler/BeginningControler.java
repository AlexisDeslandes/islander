package beginning.controler;

import beginning.vue.BeginningVue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BeginningControler {

    private BeginningVue vue;

    public BeginningControler(BeginningVue vue) {
        this.vue = vue;
    }

    public Scene getScene() {
        return new Scene(vue.getContenu());
    }

    public void setBehaviourComponent() {
        vue.getNewGame().setOnMouseClicked(event -> {
            Stage s = (Stage) vue.getContenu().getScene().getWindow();
            s.setScene(new Scene(new Label("bla")));
        });
    }
}
