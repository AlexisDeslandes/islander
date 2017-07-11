package game.mainWindow.model;

import commun.Compas;

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
        this.notifyObservers();
    }

    public int getRectangleX() {
        return playerMap.get(1).getPosition().x;
    }

    public int getRectangleY() {
        return playerMap.get(1).getPosition().y;
    }

    public int getRec() {
        return playerMap.get(2).getPosition().x;
    }

    public int getRecY() {
        return playerMap.get(2).getPosition().y;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void addPlayer(int id, Player player) {
        playerMap.put(id, player);
    }

    public int getPlayerId() {
        return playerID;
    }

    public void addPlayers(int nbPlayer) {
        int players = nbPlayer - 1;
        for (int i = 0; i < players; i++) {
            playerMap.put(i+1,new Player());
        }
    }
}
