package game.mainWindow.keyGestion;

import commun.Compas;
import game.connection.client.ClientCommunication;

/**
 * Created by Desla on 07/07/2017.
 */
class KeyRequestThread extends Thread {

    private ClientCommunication clientCommunication;

    private Compas keyPressed;

    private boolean finish;

    KeyRequestThread(Compas keyPressed, ClientCommunication clientCommunication) {
        this.clientCommunication = clientCommunication;
        this.keyPressed = keyPressed;
        this.finish = false;
    }

    @Override
    public void run() {
        while (!finish){
            System.out.println(keyPressed);
            clientCommunication.sendRequest(keyPressed.getRequest());
        }
    }

    void activate(Compas keyPressed){
        this.keyPressed = keyPressed;
        this.finish = false;
    }

    void disactivate(){
        this.keyPressed = null;
        this.finish = true;
    }

    public void setCompas(Compas compas){
        this.keyPressed = compas;
    }
}
