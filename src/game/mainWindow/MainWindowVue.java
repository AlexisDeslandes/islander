package game.mainWindow;

import game.mainWindow.model.Player;
import game.mainWindow.model.PositionIdentified;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowVue extends Scene implements Observer {

    private Map<Integer, Label> characters;

    private AnchorPane root;

    MainWindowVue() {
        super(new AnchorPane());
        this.characters = new HashMap<>();
        AnchorPane pane = (AnchorPane) this.getRoot();
        this.root = pane;
        pane.setMinWidth(800);
        pane.setMinHeight(800);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label waitingMessage = new Label("Waiting for another player ...");
        waitingMessage.setLayoutX(400);
        waitingMessage.setLayoutY(400);
        pane.getChildren().add(waitingMessage);
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
    public void update(Observable o, Object arg) {

        if (arg instanceof PositionIdentified) {
            PositionIdentified positionIdentified = (PositionIdentified) arg;
            root.getChildren().get(positionIdentified.getId() - 1).setLayoutX(positionIdentified.getPosition().getX());
            root.getChildren().get(positionIdentified.getId() - 1).setLayoutY(positionIdentified.getPosition().getY());
        }
        if (arg instanceof Map) {
            Platform.runLater(() -> {
                root.getChildren().clear();
                Map<Integer, Player> map = (Map<Integer, Player>) arg;
                map.keySet().forEach(
                        integer -> {
                            Label label = new Label("" + integer);
                            characters.put(integer, label);
                            root.getChildren().add(label);
                        }
                );
            });

        }

    }
}
