package backend.academy.service;

import backend.academy.entity.Word;
import backend.academy.game.GameConfig;
import lombok.Getter;

@Getter
public class MistakeService {

    private int mistakeCount;
    private final int maxMistakes;

    private static final int MAX_MISTAKES_EASY = GameConfig.EASY_MAX_MISTAKES;
    private static final int MAX_MISTAKES_MEDIUM = GameConfig.MEDIUM_MAX_MISTAKES;
    private static final int MAX_MISTAKES_HARD = GameConfig.HARD_MAX_MISTAKES;

    public MistakeService(Word word) {
        if (word == null || word.complexity() == null) {
            maxMistakes = MAX_MISTAKES_MEDIUM;
        } else {
            switch (word.complexity()) {
                case EASY -> maxMistakes = MAX_MISTAKES_EASY;
                case HARD -> maxMistakes = MAX_MISTAKES_HARD;
                default -> maxMistakes = MAX_MISTAKES_MEDIUM;
            }
        }
    }

    public void incrementMistakeCount() {
        mistakeCount++;
    }

}
