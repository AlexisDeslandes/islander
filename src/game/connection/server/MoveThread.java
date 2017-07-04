package game.connection.server;

import game.connection.request.Request;
import org.json.JSONException;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Desla on 03/07/2017.
 */
class MoveThread extends Thread {

    private boolean running;

    private ObjectOutputStream oos;

    private Request request;

    MoveThread(ObjectOutputStream oos){
        this.running = false;
        this.oos = oos;
    }

    @Override
    public void run() {
        while (running){
            try {
                oos.writeObject(request.getJSON().get("deplacement"));
                Thread.sleep(20);
            } catch (IOException | JSONException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void activate(Request request){
        this.request = request;
        this.running = true;
    }

    void disactivate(){
        this.running = false;
        this.request = null;
    }

}
