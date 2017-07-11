package game.mainWindow;

import game.mainWindow.model.MainWindowModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowVue extends Scene implements Observer {

    private Label something, elsething;

    public MainWindowVue(){
        super(new AnchorPane());
        AnchorPane pane = (AnchorPane) this.getRoot();
        pane.setMinWidth(800);
        pane.setMinHeight(700);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#FD6C9E"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.something = new Label("This is a revolution");
        this.elsething = new Label("second revolution");
        pane.getChildren().add(something);
        pane.getChildren().add(elsething);
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
        if (o instanceof MainWindowModel){
            something.setLayoutX(((MainWindowModel) o).getRectangleX());
            something.setLayoutY(((MainWindowModel) o).getRectangleY());
            elsething.setLayoutX(((MainWindowModel) o).getRec());
            elsething.setLayoutY(((MainWindowModel) o).getRecY());
        }
    }
}
