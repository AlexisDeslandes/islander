package game.connection.server;

import commun.Compas;
import game.connection.request.Request;
import game.connection.request.move.Movement;
import game.connection.request.player.GetGameState;
import game.connection.request.player.GetIDPlayer;
import game.connection.request.player.InformNewPlayer;
import game.connection.request.player.SendArrowRequest;
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

    private int id;

    private ObjectOutputStream oos;

    private ObjectInputStream ois;

    private Socket socket;

    private static Map<Integer, Compas> currentCompasMap = new HashMap<>();

    private static Map<MoveThreadIdentifier, MoveThread> moveThreadMap = new HashMap<>();

    private static Map<Integer,ArrowThread> integerArrowThreadMap = new HashMap<>();

    ServerInstanceCommunication(Socket socket) {
        this.socket = socket;
        int sizeServers = ServerCommunication.serverInstanceCommunicationList.size() + 1;
        currentCompasMap.put(sizeServers, Compas.NOTHING);
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < sizeServers + 1; i++) {
            moveThreadMap.put(new MoveThreadIdentifier(sizeServers, i), new MoveThread());
        }
        for (int i = 1; i < sizeServers; i++) {
            moveThreadMap.put(new MoveThreadIdentifier(i, sizeServers), new MoveThread());
        }
    }

    public void modifyMovementThread(Movement request) throws JSONException {
        Compas direction = (Compas) request.getJSON().get("direction");
        int id = request.getID();
        moveThreadMap.entrySet().stream().filter(
                entrySet -> entrySet.getKey().getIdPlayerMoving() == id).forEach(
                moveThreadIdentifierMoveThreadEntry -> {
                    if (!direction.equals(currentCompasMap.get(id)) && !direction.equals(Compas.NOTHING)) {
                        moveThreadIdentifierMoveThreadEntry.getValue().disactivate();
                        moveThreadMap.put(moveThreadIdentifierMoveThreadEntry.getKey(), new MoveThread());
                        moveThreadMap.get(moveThreadIdentifierMoveThreadEntry.getKey()).activate(request);
                    }
                    if (direction.equals(Compas.NOTHING)) {
                        moveThreadIdentifierMoveThreadEntry.getValue().disactivate();
                    }
                }
        );
        currentCompasMap.put(id, direction);
    }

    public void run() {
        while (true) {
            try {
                Request request = (Request) ois.readObject();
                System.out.println("Requête de " + socket.getInetAddress().getHostAddress() + " : " + request.getJSON());
                request.actionOnServer(this);
                ServerCommunication.serverInstanceCommunicationList.forEach(communication -> {
                    try {
                        request.actionOnServers(communication);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
                request.actionEndingServers();
            } catch (SocketException e) {
                System.out.println("Connection perdue avec " + socket.getInetAddress().getHostAddress());
                ServerCommunication.serverInstanceCommunicationList.remove(this);
                break;
            } catch (IOException | JSONException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void attributeID(GetIDPlayer getIDPlayer) {
        try {
            int id = ServerCommunication.serverInstanceCommunicationList.size();
            this.id = id;
            getIDPlayer.setIdPlayer(id);
            oos.writeObject(getIDPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void informNewPlayer(GetIDPlayer getIDPlayer) {
        try {
            oos.writeObject(new InformNewPlayer(getIDPlayer.getIdPlayer()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<MoveThreadIdentifier, MoveThread> getMoveThreadMap() {
        return moveThreadMap;
    }

    public void askGameState(GetGameState getGameState) {
        int nbPlayer = ServerCommunication.serverInstanceCommunicationList.size();
        getGameState.setNbPlayer(nbPlayer);
        try {
            oos.writeObject(getGameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOos() {
        return oos;
    }


    public int getID() {
        return id;
    }

    public void sendArrow(SendArrowRequest sendArrowRequest) {
        try {
            integerArrowThreadMap.put(id,new ArrowThread());
            oos.writeObject(sendArrowRequest);
            //Thread.sleep(50);   //see if it is necessary
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
