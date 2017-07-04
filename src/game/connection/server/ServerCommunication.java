package game.connection.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Desla on 28/06/2017.
 */
public class ServerCommunication {

    static List<ServerInstanceCommunication> serverInstanceCommunicationList = new LinkedList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection acceptée avec " + socket.getInetAddress().getHostAddress());
                ServerInstanceCommunication serverInstanceCommunication = new ServerInstanceCommunication(socket);
                serverInstanceCommunicationList.add(serverInstanceCommunication);
                serverInstanceCommunication.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
