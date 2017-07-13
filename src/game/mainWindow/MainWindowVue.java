package game.mainWindow;

import game.mainWindow.model.Arrow;
import game.mainWindow.model.Player;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowVue extends Scene implements Observer {

    private Map<Integer, Rectangle> characters;

    private Map<Integer, Rectangle> arrowMap;

    private AnchorPane root;

    MainWindowVue() {
        super(new AnchorPane());
        this.characters = new HashMap<>();
        this.arrowMap = new HashMap<>();
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
        if (arg instanceof Player) {
            Player player = (Player) arg;
            moveCharacter(player);
        }
        if (arg instanceof Map) {
            Map<Integer, Player> integerPlayerMap = (Map<Integer, Player>) arg;
            addPlayerInScene(integerPlayerMap);
        }

        if (arg instanceof Arrow) {
            Arrow arrow = (Arrow) arg;
            addArrow(arrow);
        }
    }

    private void addArrow(Arrow arrow) {
        Platform.runLater(() -> {
            Rectangle rectangle = new Rectangle(10, 10, Color.AQUA);
            rectangle.setLayoutX(arrow.getPosition().getX());
            rectangle.setLayoutY(arrow.getPosition().getY());
            this.root.getChildren().add(rectangle);
        });
    }

    private void addPlayerInScene(Map<Integer, Player> integerPlayerMap) {
        Platform.runLater(() -> {
            root.getChildren().clear();
            integerPlayerMap.keySet().forEach(
                    integer -> {
                        Rectangle rectangle = new Rectangle(50, 50);
                        characters.put(integer, rectangle);
                        root.getChildren().add(rectangle);
                    }
            );
        });
    }

    private void moveCharacter(Player newModificationPlayer) {
        Rectangle toChange = characters.get(newModificationPlayer.getId());
        toChange.setLayoutX(newModificationPlayer.getX());
        toChange.setLayoutY(newModificationPlayer.getY());
    }
}
