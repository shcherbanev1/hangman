package backend.academy;

import backend.academy.game.Game;
import backend.academy.service.WordService;
import backend.academy.storage.word.InCodeWordStorage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Game game = new Game(System.out, System.in, new WordService(new InCodeWordStorage()));
        game.start();
    }
}
