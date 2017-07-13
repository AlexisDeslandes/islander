package game.mainWindow.keyGestion;

import commun.Compas;
import game.connection.client.ClientCommunication;
import game.mainWindow.MainWindowVue;

/**
 * Created by Desla on 07/07/2017.
 */
public class KeyManager {

    private Compas compas;

    private ClientCommunication clientCommunication;

    private int playerID;

    private static boolean allowModification = false;

    public KeyManager(ClientCommunication clientCommunication, MainWindowVue vue, int playerID) {
        this.playerID = playerID;
        this.clientCommunication = clientCommunication;
        this.compas = Compas.NOTHING;
        this.setBehaviourKeyBoard(vue);
    }

    private void setBehaviourKeyBoard(MainWindowVue vue) {
        vue.setOnKeyPressed(event -> {
            if (allowModification) {
                Compas precedentCompas = this.compas;
                switch (event.getCode()) {
                    case Q:
                        this.compas = compas.goToWest();
                        break;
                    case D:
                        this.compas = compas.goToEast();
                        break;
                    case Z:
                        this.compas = compas.goToNorth();
                        break;
                    case S:
                        this.compas = compas.goToSouth();
                    default:
                        break;
                }
                if (!precedentCompas.equals(compas)) clientCommunication.sendRequest(compas.getRequest(playerID));
            }
        });
        vue.setOnKeyReleased(event -> {
            if (allowModification) {
                switch (event.getCode()) {
                    case Q:
                        this.compas = compas.cancelWest();
                        break;
                    case D:
                        this.compas = compas.cancelEast();
                        break;
                    case Z:
                        this.compas = compas.cancelNorth();
                        break;
                    case S:
                        this.compas = compas.cancelSouth();
                        break;
                    default:
                        break;
                }
                clientCommunication.sendRequest(compas.getRequest(playerID));
            }
        });

    }

    public static void setAllowModification(boolean allowModification) {
        KeyManager.allowModification = allowModification;
    }

}
