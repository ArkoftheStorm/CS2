package checkers;

import java.awt.Color;

public class Player {

    private static Player currentTurn;
    private static Player players[] = new Player[2];
    private boolean extraTurn;
    private int changeCount;
    private Color color;
    public static void Reset() {

        if (players[0] == null) {
            players[0] = new Player(0);
            players[1] = new Player(0);
            
        }
        currentTurn = players[0];
        players[0].changeCount = 0;
        players[1].changeCount = 0;
        players[0].color = Color.red;
        players[1].color = Color.blue;
    }

    public static Player GetCurrentPlayer() {
        return (currentTurn);
    }

    public static void SwitchTurn() {
        if(getCurrentPlayer().extraTurn){
            getCurrentPlayer().extraTurn = false;
            return;
        }
            
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
    public Color getColor(){
        return color;
    }
    public static Player getPlayer(int p){
        return players[p];
    }
    public static Player getCurrentPlayer(){
        return(currentTurn);
    }
    public int getChangeCount(){
        return(changeCount);
        }
    public void incChangeCount(){
        changeCount++;
    }
    public void extraTurn(){
        extraTurn = true;
    }
    

}
