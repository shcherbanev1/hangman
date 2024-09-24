package backend.academy.storage.word;

import backend.academy.entity.Word;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import java.util.ArrayList;
import java.util.EnumMap;

public class InCodeWordStorage extends WordStorage {

    public InCodeWordStorage() {
        wordMap = new EnumMap<>(Category.class);
        init();
    }

    private void addWord(Word word) {
        wordMap.putIfAbsent(word.category(), new EnumMap<>(Complexity.class));
        wordMap.get(word.category()).putIfAbsent(word.complexity(), new ArrayList<>());
        wordMap.get(word.category()).get(word.complexity()).add(word);
    }

    @Override
    protected final void init() {
        addWord(new Word(Complexity.EASY, Category.ANIMALS, "cat"));
        addWord(new Word(Complexity.EASY, Category.ANIMALS, "dog"));
        addWord(new Word(Complexity.MEDIUM, Category.ANIMALS, "elephant"));
        addWord(new Word(Complexity.HARD, Category.ANIMALS, "chameleon"));

        addWord(new Word(Complexity.EASY, Category.FOOD, "apple"));
        addWord(new Word(Complexity.MEDIUM, Category.FOOD, "sandwich"));
        addWord(new Word(Complexity.HARD, Category.FOOD, "croissant"));

        addWord(new Word(Complexity.EASY, Category.TECHNOLOGY, "mouse"));
        addWord(new Word(Complexity.MEDIUM, Category.TECHNOLOGY, "laptop"));
        addWord(new Word(Complexity.HARD, Category.TECHNOLOGY, "microprocessor"));

        addWord(new Word(Complexity.EASY, Category.SPORTS, "ball"));
        addWord(new Word(Complexity.MEDIUM, Category.SPORTS, "basketball"));
        addWord(new Word(Complexity.HARD, Category.SPORTS, "triathlon"));

        addWord(new Word(Complexity.EASY, Category.TRAVEL, "train"));
        addWord(new Word(Complexity.MEDIUM, Category.TRAVEL, "passport"));
        addWord(new Word(Complexity.HARD, Category.TRAVEL, "itinerary"));
    }
}
