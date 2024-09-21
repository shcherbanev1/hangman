package backend.academy.service.render;

import static backend.academy.storage.render.HangmanRenderStorage.getState;
import static backend.academy.storage.render.HangmanRenderStorage.size;

public class ConsoleRender implements HangmanRender {

    @Override
    public String render(int mistakeCount, int maxMistakes) {
        int stateIndex;
        if (mistakeCount == 0) {
            stateIndex = 0;
        } else if (mistakeCount == maxMistakes) {
            stateIndex = size() - 1;
        } else {
            stateIndex = (int) Math.floor(((double) mistakeCount / maxMistakes) * (size() - 2));
            stateIndex = Math.min(stateIndex + 1, size() - 2);
        }
        return getState(stateIndex);
    }
}
