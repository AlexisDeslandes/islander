package game.connection.server;

import commun.Compas;
import game.connection.request.Request;
import org.json.JSONException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Desla on 28/06/2017.
 */
public class ServerInstanceCommunication extends Thread {

    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    private Compas currentCompas;

    private Socket socket;

    private MoveThread moveThread;

    ServerInstanceCommunication(Socket socket) {
        this.socket = socket;
        this.currentCompas = Compas.NOTHING;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.moveThread = new MoveThread(oos);
    }

    public void modifyMovementThread(Request request) throws JSONException {
        Compas direction = (Compas) request.getJSON().get("direction");
        if (!direction.equals(currentCompas) && !direction.equals(Compas.NOTHING)) {
            this.moveThread.disactivate();
            this.currentCompas = direction;
            moveThread = new MoveThread(oos);
            moveThread.activate(request);
            moveThread.start();
        }
        if (direction.equals(Compas.NOTHING)){
            this.moveThread.disactivate();
        }
    }

    public void run() {
        while (true) {
            try {
                Request request = (Request) ois.readObject();
                System.out.println("Requête de " + socket.getInetAddress().getHostAddress() + " : " + request.getJSON());
                ServerCommunication.serverInstanceCommunicationList.forEach(communication -> {
                    try {
                        request.actionOnServer(communication);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            } catch (SocketException e) {
                System.out.println("Connection perdue avec " + socket.getInetAddress().getHostAddress());
                ServerCommunication.serverInstanceCommunicationList.remove(this);
                break;
            } catch (IOException | JSONException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
