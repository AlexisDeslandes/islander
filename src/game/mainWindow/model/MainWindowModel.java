package game.mainWindow.model;

import commun.Compas;
import game.connection.request.player.SendArrowRequest;
import game.mainWindow.periphericGestion.KeyManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Desla on 27/06/2017.
 */
public class MainWindowModel extends Observable {

    private Map<Integer, Player> playerMap;

    private Map<Integer,Arrow> integerArrowMap;

    private int playerID;

    public MainWindowModel() {
        this.playerMap = new HashMap<>();
        this.integerArrowMap = new HashMap<>();
    }

    public void move(int id, Compas compas, int value) {
        Player player = playerMap.get(id);
        compas.makeTranslate(player.getPosition(), value);
        this.setChanged();
        this.notifyObservers(player);
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
            int id = i + 1;
            playerMap.put(id, new Player(id));
        }
        if (playerMap.size() == 2) {
            launchGame();
        }
    }

    public Player getPlayer(int id){
        return playerMap.get(id);
    }

    private void launchGame() {
        this.setChanged();
        this.notifyObservers(playerMap);
        KeyManager.setAllowModification(true);
    }

    public void addArrow(SendArrowRequest sendArrowRequest) {
        int arrowId = sendArrowRequest.getId();
        this.integerArrowMap.put(arrowId,new Arrow(sendArrowRequest.getPlayerPosition(),arrowId));
        this.setChanged();
        this.notifyObservers(this.integerArrowMap.get(arrowId));
    }
}
