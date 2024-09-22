package backend.academy.game;

import backend.academy.entity.Word;
import backend.academy.service.GuessingService;
import backend.academy.service.MistakeService;
import lombok.Getter;
import java.util.HashSet;
import java.util.Set;

public class GameSession {

    @Getter private final Word word;
    @Getter private final char[] mask;
    @Getter private final MistakeService mistakeService;
    @Getter private final Set<Character> triedChars;
    private final GuessingService guessingService;

    public GameSession(Word word) {
        this.word = word;
        this.mask = new char[word.word().length()];
        this.mistakeService = new MistakeService(word);
        guessingService = new GuessingService(word, mask);
        triedChars = new HashSet<>();
    }

    public boolean isAlive() {
        return mistakeService.mistakeCount() < mistakeService.maxMistakes() && !isWordGuessed();
    }

    public boolean isWordGuessed() {
        return word.word().equals(new String(mask));
    }

    public boolean guess(char letter) {
        boolean guessed = guessingService.guessLetter(letter, mask);
        if (!guessed) {
            mistakeService.incrementMistakeCount();
        }
        triedChars.add(letter);
        return guessed;
    }



}
