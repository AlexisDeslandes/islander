package game.connection.server;

import game.connection.request.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Desla on 03/07/2017.
 */
public class MoveThread extends Thread {

    private boolean running;

    private ObjectOutputStream oos;

    private boolean readyToStart;

    private Request request;

    MoveThread(){
        this.readyToStart = false;
        this.running = false;
    }

    @Override
    public void run() {
        while (running){
            try {
                oos.writeObject(request);
                Thread.sleep(20);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void activate(Request request){
        this.request = request;
        this.running = true;
        this.readyToStart = true;
    }

    void disactivate(){
        this.running = false;
        this.request = null;
    }

    public boolean readyToStart(){
        return readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }
}
