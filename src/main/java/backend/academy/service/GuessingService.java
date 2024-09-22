package backend.academy.service;

import backend.academy.entity.Word;

public class GuessingService {

    private final Word word;

    public GuessingService(Word word, char[] mask) {
        this.word = word;
        initMask(mask);
    }

    private void initMask(char[] mask) {
        for (int i = 0; i < word.word().length(); i++) {
            mask[i] = '*';
        }
    }

    public boolean guessLetter(char letter, char[] mask) {
        char ignoreCaseLetter = Character.toLowerCase(letter);
        boolean guessed = false;
        for (int i = 0; i < word.word().length(); i++) {
            if (word.word().charAt(i) == ignoreCaseLetter) {
                mask[i] = word.word().charAt(i);
                guessed = true;
            }
        }
        return guessed;
    }

}
