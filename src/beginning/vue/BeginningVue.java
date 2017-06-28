package beginning.vue;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Observable;
import java.util.Observer;

public class BeginningVue implements Observer {

    private VBox buttons;

    private Button newGame;

    private Button leave;

    private Button param;

    private Button loadGame;

    public BeginningVue() {
        this.newGame = new Button("New Game");
        this.leave = new Button("Leave");
        this.param = new Button("Parameters");
        this.loadGame = new Button("Load Game");
        buttons = new VBox(newGame, loadGame, param, leave);
        setButtonsPref();
    }

    private void setButtonsPref() {
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

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {}

    public Parent getContenu() {
        return buttons;
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
