package game.connection.client;

import game.connection.request.Request;
import game.mainWindow.MainWindowModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Desla on 27/06/2017.
 */
public class ClientCommunication {

    private ObjectInputStream ois;

    private ObjectOutputStream oos;

    public ClientCommunication(MainWindowModel model) {
        try {
            Socket socket = new Socket("192.168.56.1", 1234);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CommunicationReceiver(ois, model).start();
    }

    public void sendRequest(Request request) {
        try {
            oos.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
