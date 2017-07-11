package game.connection.server;

/**
 * Created by Desla on 11/07/2017.
 */
public class MoveThreadIdentifier {

    private int idPlayerMoving, idPlayerListening;

    MoveThreadIdentifier(int idPlayerMoving, int idPlayerListening){
        this.idPlayerMoving = idPlayerMoving;
        this.idPlayerListening = idPlayerListening;
    }

    public int getIdPlayerMoving() {
        return idPlayerMoving;
    }

    public int getIdPlayerListening() {
        return idPlayerListening;
    }

    public String toString(){
        return "moving " + idPlayerMoving + " listening " + idPlayerListening;
    }
}
