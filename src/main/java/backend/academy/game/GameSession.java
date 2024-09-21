package backend.academy.game;

import backend.academy.entity.Word;
import backend.academy.service.MistakeService;
import lombok.Getter;

@Getter
public class GameSession {

    private final Word word;
    private final char[] mask;
    private final MistakeService mistakeService;

    public GameSession(Word word) {
        this.word = word;
        this.mask = new char[word.word().length()];
        this.mistakeService = new MistakeService(word);
    }

    public boolean isAlive() {
        return mistakeService.mistakeCount() < mistakeService.maxMistakes() && !isWordGuessed();
    }

    public boolean isWordGuessed() {
        return word.word().equals(new String(mask));
    }

}
