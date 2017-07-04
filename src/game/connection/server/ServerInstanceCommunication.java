package game.connection.server;

import commun.Direction;
import game.connection.request.Request;
import org.json.JSONException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Desla on 28/06/2017.
 */
public class ServerInstanceCommunication extends Thread {

    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    private Map<Direction,MoveThread> directionMoveThreadMap;

    private MoveThread moveThread;

    private Socket socket;

    ServerInstanceCommunication(Socket socket) {
        this.socket = socket;
        this.directionMoveThreadMap = new HashMap<>();
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.moveThread = new MoveThread(oos);
    }

    public void instantiatedDeplacementThread(Request request) throws JSONException {
        Direction direction = (Direction) request.getJSON().get("direction");
        if (!directionMoveThreadMap.containsKey(direction)) {
            MoveThread moveThread = new MoveThread(oos);
            directionMoveThreadMap.put(direction, moveThread);
            moveThread.activate(request);
            moveThread.start();
        }
        /*
        if (!this.moveThread.isAlive()) {
            this.moveThread = new MoveThread(oos);
            this.moveThread.activate(request);
            this.moveThread.start();
        }
        */
    }

    public void cancelDeplacementThread(Direction direction) {
        this.directionMoveThreadMap.get(direction).disactivate();
        this.directionMoveThreadMap.remove(direction);
        //this.moveThread.disactivate();
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
