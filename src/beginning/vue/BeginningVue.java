package beginning.vue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BeginningVue extends Scene {

    private Button newGame;

    private Button leave;

    private Button param;

    private Button loadGame;

    public BeginningVue(){
        super(new VBox(new Button("New Game"),new Button("Leave"),new Button("Parameters"),new Button("Load Game")));
        VBox buttons = (VBox) this.getRoot();
        this.newGame = (Button) buttons.getChildren().get(0);
        this.leave = (Button) buttons.getChildren().get(1);
        this.param = (Button) buttons.getChildren().get(2);
        this.loadGame = (Button) buttons.getChildren().get(3);
        setButtonsPref();
    }

    private void setButtonsPref() {
        VBox buttons = (VBox) this.getRoot();
        buttons.setLayoutX(100);
        buttons.setLayoutY(100);
        buttons.setSpacing(25);
        buttons.setPrefWidth(600);
        buttons.setMinHeight(125);

        this.newGame.setPrefWidth(buttons.getPrefWidth());
        this.newGame.setPrefHeight(buttons.getMinHeight());

        this.leave.setPrefWidth(buttons.getPrefWidth());
        this.leave.setPrefHeight(buttons.getMinHeight());

        this.param.setPrefWidth(buttons.getPrefWidth());
        this.param.setPrefHeight(buttons.getMinHeight());

        this.loadGame.setPrefWidth(buttons.getPrefWidth());
        this.loadGame.setPrefHeight(buttons.getMinHeight());
    }

    public Button getNewGame() {
        return newGame;
    }

    public Button getLeave() {
        return leave;
    }

    public Button getParam() {
        return param;
    }

    public Button getLoadGame() {
        return loadGame;
    }
}
