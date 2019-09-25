package checkers;

import java.awt.Color;

public class Player {

    private static Player currentTurn;
    private static Player players[] = new Player[2];
    public int changeCount;

    public static void Reset() {

        if (players[0] == null) {
            players[0] = new Player(0);
            players[1] = new Player(0);
        }
        currentTurn = players[0];
        getPlayer(0).changeCount = 0;
        getPlayer(1).changeCount = 0;
    }

    public static Player GetCurrentPlayer() {
        return (currentTurn);
    }

    public static void SwitchTurn() {
        currentTurn
                = (currentTurn == players[1])
                        ? players[0]
                        : players[1];
    }

    Player(int _changeCount) {
        changeCount = 0;
    }

//    public Color getColor() {
//        return (color);
//    }
    public static Player getPlayer(int p){
        return players[p];
    }

}
