package checkers;

import java.awt.Color;

public class Player {

    private static Player currentTurn;
    private static Player players[] = new Player[2];
    private Color color;

    public static void Reset() {

        if (players[0] == null) {
            players[0] = new Player(Color.red);
            players[1] = new Player(Color.black);
        }
        currentTurn = players[0];

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

    Player(Color _color) {
        color = _color;

    }

    public Color getColor() {
        return (color);
    }

}
