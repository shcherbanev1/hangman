package backend.academy.game;

import backend.academy.entity.Word;
import backend.academy.service.GuessingService;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

public class GameSession {

    @Getter private final Word word;
    @Getter private final char[] mask;
    @Getter private final Set<Character> triedChars;
    @Getter private int mistakeCount;
    @Getter private final int maxMistakes;
    private final GuessingService guessingService;

    public GameSession(Word word) {
        this.word = word;
        this.mask = new char[word.word().length()];
        guessingService = new GuessingService(word, mask);
        mistakeCount = 0;
        maxMistakes = word.complexity().maxMistakes();
        triedChars = new HashSet<>();
    }

    public boolean isAlive() {
        return mistakeCount < maxMistakes && !isWordGuessed();
    }

    public boolean isWordGuessed() {
        return word.word().equals(new String(mask));
    }

    public boolean guess(char letter) {
        boolean guessed = guessingService.guessLetter(letter, mask);
        if (!guessed) {
            mistakeCount++;
        }
        triedChars.add(letter);
        return guessed;
    }

}
