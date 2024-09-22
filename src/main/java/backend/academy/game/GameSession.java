package backend.academy.game;

import backend.academy.entity.Word;
import backend.academy.service.GuessingService;
import backend.academy.service.MistakeService;
import lombok.Getter;

public class GameSession {

    @Getter private final Word word;
    @Getter private final char[] mask;
    @Getter private final MistakeService mistakeService;
    private final GuessingService guessingService;

    public GameSession(Word word) {
        this.word = word;
        this.mask = new char[word.word().length()];
        this.mistakeService = new MistakeService(word);
        guessingService = new GuessingService(this);
    }

    public boolean isAlive() {
        return mistakeService.mistakeCount() < mistakeService.maxMistakes() && !isWordGuessed();
    }

    public boolean isWordGuessed() {
        return word.word().equals(new String(mask));
    }

    public boolean guess(char letter) {
        boolean guessed = guessingService.guessLetter(letter, this);
        if (!guessed) mistakeService.incrementMistakeCount();
        return guessed;
    }



}
