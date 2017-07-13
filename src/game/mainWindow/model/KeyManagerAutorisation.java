package game.mainWindow.model;

/**
 * Created by Desla on 13/07/2017.
 */
public class KeyManagerAutorisation {

    private boolean allow;

    KeyManagerAutorisation(boolean b) {
        this.allow = b;
    }

    public boolean getBoolean(){
        return allow;
    }
}
