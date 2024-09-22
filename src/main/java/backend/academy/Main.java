package backend.academy;

import backend.academy.game.Game;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Game game = new Game(System.out, System.in);
        game.start();
    }
}
