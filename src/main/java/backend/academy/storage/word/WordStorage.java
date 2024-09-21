package backend.academy.storage.word;

import backend.academy.entity.Word;
import backend.academy.types.Category;
import backend.academy.types.Complexity;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class WordStorage {

    protected Map<Category, Map<Complexity, List<Word>>> wordMap;

    protected abstract void init();

}
