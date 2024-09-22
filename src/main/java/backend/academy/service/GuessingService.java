package backend.academy.service;

import backend.academy.entity.Word;
import backend.academy.game.GameSession;

public class GuessingService {

    private final Word word;

    public GuessingService(GameSession gameSession) {
        this.word = gameSession.word();
        initMask(gameSession.mask());
    }

    private void initMask(char[] mask) {
        for (int i = 0; i < word.word().length(); i++) {
            mask[i] = '*';
        }
    }

    public boolean guessLetter(char letter, GameSession gameSession) {
        letter = Character.toLowerCase(letter);
        boolean guessed = false;
        for (int i = 0; i < word.word().length(); i++) {
            if (word.word().charAt(i) == letter) {
                gameSession.mask()[i] = word.word().charAt(i);
                guessed = true;
            }
        }
        return guessed;
    }

}
