import beginning.controller.BeginningController;
import beginning.vue.BeginningVue;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Principal class that launch the application.
 * @author Alexis Deslandes
 */
public class Islander extends Application{

    @Override
    public void start(Stage stage){
        int size = 800;

        BeginningVue scene = new BeginningVue();
        BeginningController controler = new BeginningController(scene);
        controler.setBehaviourComponent();

        stage.setScene(controler.getScene());
        stage.setWidth(size);
        stage.setHeight(size);
        stage.setTitle("Islander");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
