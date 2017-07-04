package game.connection.client;

import game.mainWindow.MainWindowModel;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Desla on 28/06/2017.
 */
class CommunicationReceiver extends Thread {

    private ObjectInputStream ois;

    private MainWindowModel model;

    CommunicationReceiver(ObjectInputStream ois, MainWindowModel model) {
        this.ois = ois;
        this.model = model;
    }

    public void run() {
        while (true) {
            try {
                Integer i = (Integer) ois.readObject();
                model.incrX(i);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
