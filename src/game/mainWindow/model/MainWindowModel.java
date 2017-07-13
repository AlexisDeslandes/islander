package game.mainWindow.model;

import commun.Compas;
import game.mainWindow.keyGestion.KeyManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowModel extends Observable {

    private Map<Integer, Player> playerMap;

    private int playerID;

    public MainWindowModel() {
        this.playerMap = new HashMap<>();
    }

    public void move(int id, Compas compas, int value) {
        compas.makeTranslate(playerMap.get(id).getPosition(), value);
        this.setChanged();
        this.notifyObservers(new PositionIdentified(id, playerMap.get(id).getPosition()));
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void addPlayer(int id, Player player) {
        playerMap.put(id, player);
        if (playerMap.size() == 2) {
            launchGame();
        }
    }

    public int getPlayerId() {
        return playerID;
    }

    public void addPlayers(int nbPlayer) {
        int players = nbPlayer - 1;
        for (int i = 0; i < players; i++) {
            int x = i + 1;
            playerMap.put(x, new Player());
        }
        if (playerMap.size() == 2) {
            launchGame();
        }
    }

    private void launchGame() {
        this.setChanged();
        this.notifyObservers(playerMap);
        KeyManager.setAllowModification(true);
    }

}
